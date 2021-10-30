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
        super.setUIContent(ResourceTable.Layout_ability_add_edit_note);
    }

}
