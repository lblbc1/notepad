package cn.hsp.notepad.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import cn.hsp.notepad.R
import cn.hsp.notepad.db.NoteRepository
import kotlinx.android.synthetic.main.activity_note_list.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：花生皮编程
 */
class NoteListActivity : AppCompatActivity() {
    private var noteRepository = NoteRepository()

    private lateinit var adapter: NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_list)
        initRecyclerView()
        initAddButton()
        refreshData()
    }

    private fun initAddButton() {
        addButton.setOnClickListener {
            startActivity(Intent(this, AddNoteActivity::class.java))
        }
    }

    private fun initRecyclerView() {
        adapter = NoteAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        //添加安卓自带的分割线
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
    }

    private fun refreshData() {
        GlobalScope.launch(Dispatchers.Main) {
            adapter.setData(noteRepository.query())
        }
    }

    override fun onResume() {
        super.onResume()
        refreshData()
    }
}