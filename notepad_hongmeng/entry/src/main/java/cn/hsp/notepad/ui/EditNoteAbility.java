package cn.hsp.notepad.ui;

import cn.hsp.notepad.ResourceTable;
import cn.hsp.notepad.db.DbHelper;
import cn.hsp.notepad.db.Note;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;
import ohos.agp.components.TextField;

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 分享编程技术，没啥深度，但看得懂，适合初学者。
 * Java | 安卓 | 前端 | 小程序 | 鸿蒙
 * 公众号：花生皮编程
 */
public class EditNoteAbility extends Ability {
    private Long noteId;
    private Note note;
    private TextField contentTextField;

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_edit_note);
        noteId = intent.getLongParam("noteId", 0);
        contentTextField = (TextField) findComponentById(ResourceTable.Id_content_text_field);
        initListeners();
        queryData();
    }

    protected void initListeners() {
        findComponentById(ResourceTable.Id_back_image).setClickedListener(component -> terminateAbility());
        findComponentById(ResourceTable.Id_save_image).setClickedListener(component -> saveNoteAndClose());
        findComponentById(ResourceTable.Id_del_image).setClickedListener(component -> delNote());
    }

    private void delNote() {
        DbHelper.getInstance().delete(noteId);
        terminateAbility();
    }

    void saveNoteAndClose() {
        String content = contentTextField.getText();
        if (!content.isEmpty() && note != null) {
            note.setContent(content);
            DbHelper.getInstance().update(note);
        }
        terminateAbility();
    }

    private void queryData() {
        note = DbHelper.getInstance().query(noteId);
        if (note != null) {
            contentTextField.setText(note.getContent());
        }
    }
}
