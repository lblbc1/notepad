//
//  NoteList.swift
//  SwiftUINote
//
//  Created by chanju Jeon on 05/06/2019.
//  Copyright © 2019 we'd. All rights reserved.
//

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
                .navigationBarTitle(Text("花生皮笔记"), displayMode: .large)
                .navigationBarItems(trailing: Button(action: self.createNote, label: { Text("新建") }))
        }
    }
    
    private func createNote() {
        let newNote = Note(text: "")
        self.userData.notes.insert(newNote, at: 0)
    }
}

#if DEBUG
struct NoteList_Previews : PreviewProvider {
    static var previews: some View {
        ForEach(["iPhone SE", "iPhone XS Max"], id: \.self) { deviceName in
            NoteList()
                .environmentObject(UserData())
                .previewDevice(PreviewDevice(rawValue: deviceName))
                .previewDisplayName(deviceName)
        }
    }
}
#endif
