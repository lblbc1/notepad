package cn.hsp.notepad.ui;

import cn.hsp.notepad.ResourceTable;
import cn.hsp.notepad.db.DbHelper;
import cn.hsp.notepad.db.Note;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;
import ohos.aafwk.content.Operation;
import ohos.agp.components.Component;
import ohos.agp.components.ListContainer;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;

import java.util.ArrayList;
import java.util.List;

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
        DbHelper dbHelper = new DbHelper();
        dbHelper.initDb(this);
        List<Note> dataList = dbHelper.query();
        NoteListItemProvider noteListItemProvider = new NoteListItemProvider(getData());
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
//                newIntent.setParam("name","花生皮编程");
//                newIntent.setParam("school","厦门大学");
//                newIntent.setParam("major","计算机科学与技术");
            startAbility(newIntent);
        });
    }

    private List<Note> getData() {
        List<Note> dataList = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            dataList.add(new Note("数据-" + (i)));
        }
        return dataList;
    }
}
