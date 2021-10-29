package cn.hsp.notepad.db;

import ohos.app.Context;
import ohos.data.DatabaseHelper;
import ohos.data.orm.OrmContext;
import ohos.data.orm.OrmPredicates;

import java.util.List;

public class DbHelper {
    private OrmContext ormContext;

    public void initDb(Context context) {
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        ormContext = databaseHelper.getOrmContext("hspDb", "hspDb.db", NoteDatabase.class);
        write();
    }

    private void write() {
        Note note = new Note();
        note.setContent("花生皮编程");
        ormContext.insert(note);
        ormContext.flush();
    }

    public List<Note> query() {
        OrmPredicates ormPredicates = ormContext.where(Note.class);
        return ormContext.query(ormPredicates);
    }

    private void modify() {
        //将查询出来的数据值修改后更新到数据库
        Note note = query().get(0);
        if (note == null) {
            return;
        }
        note.setContent("花生皮編程2");
        ormContext.update(note);
        ormContext.flush();
    }

    private void del() {
        //将查询出来的数据值从数据库中删除
        Note note = query().get(0);
        if (note == null) {
            return;
        }
        ormContext.delete(note);
        ormContext.flush();
    }
}
