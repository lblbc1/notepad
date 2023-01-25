package cn.lblbc.demo.ui.vm

import androidx.lifecycle.MutableLiveData
import cn.lblbc.demo.db.Note
import cn.lblbc.demo.db.NoteRepository

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》  http://lblbc.cn/blog
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：蓝不蓝编程
 */
class NoteListModel : BaseViewModel() {
    val noteListLiveData = MutableLiveData<List<Note>>()

    fun query() {
        launch {
            noteListLiveData.value = NoteRepository.query()
        }
    }

    fun add(content: String) {
        launch {
            NoteRepository.add(content)
        }
    }

    fun update(note: Note) {
        launch {
            NoteRepository.update(note)
        }
    }

    fun delete(noteId: Int) {
        launch {
            NoteRepository.delete(noteId)
        }
    }

}