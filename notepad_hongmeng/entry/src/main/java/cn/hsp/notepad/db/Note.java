package cn.hsp.notepad.db;

import ohos.data.orm.OrmObject;
import ohos.data.orm.annotation.Entity;
import ohos.data.orm.annotation.PrimaryKey;
/**
 * 厦门大学计算机专业 | 前华为工程师
 * 分享编程技术，没啥深度，但看得懂，适合初学者。
 * Java | 安卓 | 前端 | 小程序 | 鸿蒙
 * 公众号：花生皮编程
 */
@Entity(tableName = "Note")
public class Note extends OrmObject {
    @PrimaryKey(autoGenerate = true)
    private long id;
    private String content;

    public Note() {
    }

    public Note(String content) {
        this.content = content;
    }

    public Note(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
