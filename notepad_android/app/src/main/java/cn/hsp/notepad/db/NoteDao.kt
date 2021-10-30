package cn.hsp.notepad.db

import androidx.room.*

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 分享编程技术，没啥深度，但看得懂，适合初学者。
 * Java | 安卓 | 前端 | 小程序 | 鸿蒙
 * 公众号：花生皮编程
 */
@Dao
interface NoteDao {
    @Query("SELECT * FROM Note")
    fun query(): List<Note>

    @Query("SELECT * FROM Note WHERE id=:noteId")
    fun query(noteId: Int): Note

    @Insert
    fun add(note: Note)

    @Insert
    fun add(notes: List<Note>)

    @Delete
    fun del(note: Note)

    @Update
    fun update(note: Note)
}