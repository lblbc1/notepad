package cn.lblbc.note.db

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》  http://lblbc.cn/blog
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：蓝不蓝编程
 */
class NoteRepo {
    private val noteDao = AppDatabase.getInstance().noteDao()
    suspend fun queryNoteList(noteId: Int): Note {
        return withContext(Dispatchers.IO) { noteDao.query(noteId) }
    }

    suspend fun queryNoteList(): List<Note> {
        return withContext(Dispatchers.IO) { noteDao.query() }
    }

    suspend fun addNote(name: String) {
        withContext(Dispatchers.IO) {
            val note = Note(0, name)
            noteDao.add(note)
        }
    }

    suspend fun modifyNote(note: Note) {
        withContext(Dispatchers.IO) { noteDao.update(note) }
    }

    suspend fun delNote(id: Int) {
        withContext(Dispatchers.IO) {
            val note = Note(id, "")
            noteDao.del(note)
        }
    }
}