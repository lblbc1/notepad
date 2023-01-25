package cn.lblbc.note.db

import androidx.room.*
import cn.lblbc.note.db.Note

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》  http://lblbc.cn/blog
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：蓝不蓝编程
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