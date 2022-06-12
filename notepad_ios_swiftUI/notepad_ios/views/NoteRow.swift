// 厦门大学计算机专业 | 前华为工程师
// 专注《零基础学编程系列》  http://lblbc.cn/blog
// 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
// 公众号：蓝不蓝编程

import SwiftUI

struct NoteRow: View {
    var note: Note
    var body: some View {
        HStack {
            Text(note.text)
        }
    }
}

struct NoteRow_Previews: PreviewProvider {
    static var previews: some View {
        Group {
            NoteRow(note: Note(text: "蓝不蓝编程33"))
            NoteRow(note: Note(text: "蓝不蓝编程44"))
        }
        .previewLayout(.fixed(width: 300, height: 50))
    }
}
