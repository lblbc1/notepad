package cn.cxy.notepad.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cn.cxy.notepad.R
import cn.cxy.notepad.db.NoteRepository
import kotlinx.android.synthetic.main.activity_edit_note.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 分享编程技术，没啥深度，但看得懂，适合初学者。
 * Java | 安卓 | 前端 | 小程序 | 鸿蒙
 * 公众号：花生皮编程
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