package cn.hsp.notepad.ui;

import cn.hsp.notepad.ResourceTable;
import cn.hsp.notepad.db.Note;
import ohos.agp.components.*;

import java.util.List;

public class NoteListItemProvider extends BaseItemProvider {
    private final List<Note> dataList;

    public NoteListItemProvider(List<Note> dataList) {
        this.dataList = dataList;
    }

    @Override
    public int getCount() {
        return dataList == null ? 0 : dataList.size();
    }

    @Override
    public Object getItem(int i) {
        if (dataList != null && i >= 0 && i < dataList.size()) {
            return dataList.get(i);
        }
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public Component getComponent(int i, Component component, ComponentContainer componentContainer) {
        final Component cpt;
        if (component == null) {
            cpt = LayoutScatter.getInstance(componentContainer.getContext()).parse(ResourceTable.Layout_list_container_item, null, false);
        } else {
            cpt = component;
        }
        Note data = this.dataList.get(i);
        Text titleText = (Text) cpt.findComponentById(ResourceTable.Id_titleText);
        titleText.setText(data.getContent());
        return cpt;
    }
}