//
//  ContentView.swift
//  notepad_ios
//
//  Created by jerry on 2021/11/14.
//

import SwiftUI

struct ContentView: View {
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

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
