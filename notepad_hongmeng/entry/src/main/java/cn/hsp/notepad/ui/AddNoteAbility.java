package cn.hsp.notepad.ui;

import cn.hsp.notepad.ResourceTable;
import cn.hsp.notepad.db.DbHelper;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;
import ohos.agp.components.TextField;

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 分享编程技术，没啥深度，但看得懂，适合初学者。
 * Java | 安卓 | 前端 | 小程序 | 鸿蒙
 * 公众号：花生皮编程
 */
public class AddNoteAbility extends Ability {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_add_note);
        initListeners();
    }

    protected void initListeners() {
        findComponentById(ResourceTable.Id_back_image).setClickedListener(component -> terminateAbility());
        findComponentById(ResourceTable.Id_save_image).setClickedListener(component -> saveNoteAndClose());
    }

    void saveNoteAndClose() {
        String content = ((TextField) findComponentById(ResourceTable.Id_content_text_field)).getText();
        if (!content.isEmpty()) {
            DbHelper.getInstance().add(content);
        }
        terminateAbility();
    }
}