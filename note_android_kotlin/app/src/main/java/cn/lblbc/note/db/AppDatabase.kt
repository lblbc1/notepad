package cn.lblbc.note.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import cn.lblbc.note.LblbcApp

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》  http://lblbc.cn/blog
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：蓝不蓝编程
 */
@Database(entities = [Note::class], version = 1, exportSchema = false)

abstract class AppDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao

    companion object {
        private const val DATABASE_NAME = "note"

        @Volatile
        private var instance = buildDatabase(LblbcApp.context)

        fun getInstance() = instance

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME).build()
    }
}
