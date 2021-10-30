package cn.hsp.notepad;

import cn.hsp.notepad.db.DbHelper;
import ohos.aafwk.ability.AbilityPackage;

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 分享编程技术，没啥深度，但看得懂，适合初学者。
 * Java | 安卓 | 前端 | 小程序 | 鸿蒙
 * 公众号：花生皮编程
 */
public class MyApplication extends AbilityPackage {
    @Override
    public void onInitialize() {
        super.onInitialize();
        DbHelper.getInstance().initDb(this);
    }
}
