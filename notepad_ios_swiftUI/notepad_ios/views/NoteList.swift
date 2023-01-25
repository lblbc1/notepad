// 厦门大学计算机专业 | 前华为工程师
// 专注《零基础学编程系列》  http://lblbc.cn/blog
// 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
// 公众号：蓝不蓝编程

import SwiftUI

struct NoteList : View {
    @EnvironmentObject var userData: UserData
    
    var body: some View {
        NavigationView {
            List(userData.notes) { note in
                NavigationLink(destination: NoteDetail(note: note)
                    .environmentObject(self.userData)) {
                    NoteRow(note: note)
                }
            }
                .navigationBarTitle(Text("记事本-蓝不蓝编程"), displayMode: .inline)
                .navigationBarItems(trailing: Button(action: self.createNote, label: { Text("新建") }))
        }
    }
    
    private func createNote() {
        let randomId: Int = Int(arc4random_uniform(100))
        let newNote = Note(text: String(randomId))
        self.userData.notes.insert(newNote, at: 0)
    }
}

#if DEBUG
struct NoteList_Previews : PreviewProvider {
    static var previews: some View {
        NoteList().environmentObject(UserData())
    }
}
#endif

