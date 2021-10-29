package cn.hsp.notepad.db;

import ohos.data.orm.OrmDatabase;
import ohos.data.orm.annotation.Database;


@Database(entities = {Note.class}, version = 1)
public abstract class NoteDatabase extends OrmDatabase {
}