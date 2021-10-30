package cn.hsp.notepad.ui;

import cn.hsp.notepad.ResourceTable;
import cn.hsp.notepad.db.Note;
import ohos.aafwk.content.Intent;
import ohos.aafwk.content.Operation;
import ohos.agp.components.*;
import ohos.app.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 分享编程技术，没啥深度，但看得懂，适合初学者。
 * Java | 安卓 | 前端 | 小程序 | 鸿蒙
 * 公众号：花生皮编程
 */
public class NoteListItemProvider extends BaseItemProvider {
    private final List<Note> mDataList = new ArrayList<>();

    public void setData(List<Note> dataList) {
        mDataList.clear();
        mDataList.addAll(dataList);
        notifyDataChanged();
    }

    @Override
    public int getCount() {
        return mDataList.size();
    }

    @Override
    public Object getItem(int i) {
        if (i >= 0 && i < mDataList.size()) {
            return mDataList.get(i);
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
        Note note = this.mDataList.get(i);
        Text contentText = (Text) cpt.findComponentById(ResourceTable.Id_content_text);
        contentText.setText(note.getContent());
        cpt.setClickedListener(component1 -> gotoEditNote(componentContainer.getContext(), note.getId()));
        return cpt;
    }

    private void gotoEditNote(Context context, long noteId) {
        Intent newIntent = new Intent();
        Operation operation = new Intent.OperationBuilder()
                .withBundleName("cn.hsp.notepad")
                .withAbilityName(EditNoteAbility.class.getCanonicalName())
                .build();
        newIntent.setOperation(operation);
        newIntent.setParam("noteId", noteId);
        context.startAbility(newIntent, 0);
    }
}