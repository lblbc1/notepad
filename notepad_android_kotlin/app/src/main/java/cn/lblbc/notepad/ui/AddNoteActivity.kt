package cn.lblbc.notepad.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cn.hsp.notepad.R
import cn.lblbc.notepad.db.NoteRepository
import kotlinx.android.synthetic.main.activity_edit_note.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：蓝不蓝编程
 */
class AddNoteActivity : AppCompatActivity() {
    private var noteRepository = NoteRepository()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_note)
        initViews()
        initListeners()
    }

    private fun initViews() {
        listNoteToolbar.inflateMenu(R.menu.edit_note_menu)
        listNoteToolbar.setNavigationOnClickListener { saveNoteAndFinish() }
        initMenu()
    }

    private fun initListeners() {
        listNoteToolbar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.submitNoteMenuItem -> saveNoteAndFinish()
            }
            false
        }
    }

    private fun saveNoteAndFinish() {
        if (contentET.text.toString().isNotBlank()) {
            GlobalScope.launch { noteRepository.add(contentET.text.toString()) }
        }
        finish()
    }

    private fun initMenu() {
        listNoteToolbar.menu.findItem(R.id.delNoteMenuItem).isVisible = false
    }
}