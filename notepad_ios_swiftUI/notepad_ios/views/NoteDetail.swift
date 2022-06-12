// 厦门大学计算机专业 | 前华为工程师
// 专注《零基础学编程系列》  http://lblbc.cn/blog
// 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
// 公众号：蓝不蓝编程

import SwiftUI

struct NoteDetail: View {
    @EnvironmentObject var userData: UserData
    var note: Note
    private var text: State<String>
    
    init(note: Note) {
        self.note = note
        self.text = .init(initialValue: note.text)
    }
    
    var noteIndex: Int {
        userData.notes.firstIndex(where: { $0.id == note.id})!
    }
    
    var body: some View {
        VStack {
            TextField("", text: self.text.projectedValue,
                      onEditingChanged: {_ in
                      self.updateNote()},
                      onCommit:{})
            Spacer()
        }
        .padding()
        .navigationBarTitle(Text("编辑"),displayMode: .inline)
    }
    
    private func updateNote()
    {
        self.userData.notes[noteIndex].text = self.text.wrappedValue
    }
}

struct NoteDetail_Previews: PreviewProvider {
    static var previews: some View {
        NoteDetail(note: NoteData.shared.notes[0])
            .environmentObject(UserData())
    }
}
