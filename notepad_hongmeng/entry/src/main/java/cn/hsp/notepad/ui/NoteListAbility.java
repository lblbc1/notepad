package cn.hsp.notepad.ui;

import cn.hsp.notepad.ResourceTable;
import cn.hsp.notepad.db.DbHelper;
import cn.hsp.notepad.db.Note;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;
import ohos.aafwk.content.Operation;
import ohos.agp.components.ListContainer;

import java.util.List;

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 分享编程技术，没啥深度，但看得懂，适合初学者。
 * Java | 安卓 | 前端 | 小程序 | 鸿蒙
 * 公众号：花生皮编程
 */
public class NoteListAbility extends Ability {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_note_list);
        initListContainer();
        initAddBtn();
    }

    private void initListContainer() {
        ListContainer listContainer = (ListContainer) findComponentById(ResourceTable.Id_list_container);
        DbHelper dbHelper = DbHelper.getInstance();
        List<Note> dataList = dbHelper.query();
        NoteListItemProvider noteListItemProvider = new NoteListItemProvider(dataList);
        listContainer.setItemProvider(noteListItemProvider);
    }

    private void initAddBtn() {
        findComponentById(ResourceTable.Id_add_image).setClickedListener(component -> {
            Intent newIntent = new Intent();
            Operation operation = new Intent.OperationBuilder()
                    .withBundleName("cn.hsp.notepad")
                    .withAbilityName(AddNoteAbility.class.getCanonicalName())
                    .build();
            newIntent.setOperation(operation);
            startAbility(newIntent);
        });
    }
}