//
//  NoteRow.swift
//  notepad_ios
//
//  Created by jerry on 2021/11/15.
//

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
            NoteRow(note: Note(text: "花生皮编程33"))
            NoteRow(note: Note(text: "花生皮编程44"))
        }
        .previewLayout(.fixed(width: 300, height: 50))
    }
}
