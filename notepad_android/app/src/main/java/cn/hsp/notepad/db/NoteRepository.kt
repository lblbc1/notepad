package cn.hsp.notepad.db

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 分享编程技术，没啥深度，但看得懂，适合初学者。
 * Java | 安卓 | 前端 | 小程序 | 鸿蒙
 * 公众号：花生皮编程
 */
class NoteRepository {
    private val noteDao = AppDatabase.getInstance().noteDao()
    suspend fun query(noteId: Int): Note {
        return withContext(Dispatchers.IO) { noteDao.query(noteId) }
    }

    suspend fun query(): List<Note> {
        return withContext(Dispatchers.IO) { noteDao.query() }
    }

    suspend fun add(name: String) {
        withContext(Dispatchers.IO) {
            val note = Note(0, name)
            noteDao.add(note)
        }
    }

    suspend fun update(note: Note) {
        withContext(Dispatchers.IO) { noteDao.update(note) }
    }

    suspend fun del(id: Int) {
        withContext(Dispatchers.IO) {
            val note = Note(id, "")
            noteDao.del(note)
        }
    }
}