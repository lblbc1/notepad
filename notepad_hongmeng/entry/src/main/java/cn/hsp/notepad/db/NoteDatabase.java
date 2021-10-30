package cn.hsp.notepad.db;

import ohos.data.orm.OrmDatabase;
import ohos.data.orm.annotation.Database;
/**
 * 厦门大学计算机专业 | 前华为工程师
 * 分享编程技术，没啥深度，但看得懂，适合初学者。
 * Java | 安卓 | 前端 | 小程序 | 鸿蒙
 * 公众号：花生皮编程
 */
@Database(entities = {Note.class}, version = 1)
public abstract class NoteDatabase extends OrmDatabase {
}