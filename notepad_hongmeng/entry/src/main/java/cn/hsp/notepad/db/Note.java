package cn.hsp.notepad.db;

import ohos.data.orm.OrmObject;
import ohos.data.orm.annotation.Entity;
import ohos.data.orm.annotation.PrimaryKey;

@Entity(tableName = "Note")
public class Note extends OrmObject {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String content;

    public Note() {
    }

    public Note(String content) {
        this.content = content;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
}
