// 厦门大学计算机专业 | 前华为工程师
// 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
// 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
// 公众号：花生皮编程

import SwiftUI

@main
struct notepad_iosApp: App {
    var body: some Scene {
        WindowGroup {
            NoteList().environmentObject(UserData())
        }
    }
}
