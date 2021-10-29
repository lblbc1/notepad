package cn.hsp.notepad.ui;

import cn.hsp.notepad.ResourceTable;
import cn.hsp.notepad.db.DbHelper;
import cn.hsp.notepad.db.Note;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Component;
import ohos.agp.components.ListContainer;

import java.util.ArrayList;
import java.util.List;

public class BaseNoteAbility extends Ability {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_note_list);
        initListContainer();
    }

    private void initListContainer() {
        ListContainer listContainer = (ListContainer) findComponentById(ResourceTable.Id_list_container);
        DbHelper dbHelper = new DbHelper();
        dbHelper.initDb(this);
        List<Note> dataList = dbHelper.query();
        NoteListItemProvider noteListItemProvider = new NoteListItemProvider(getData());
        listContainer.setItemProvider(noteListItemProvider);
    }
    private void initAddBtn()
    {
        findComponentById(ResourceTable.Id_add_image).setClickedListener(new Component.ClickedListener() {
            @Override
            public void onClick(Component component) {

            }
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
