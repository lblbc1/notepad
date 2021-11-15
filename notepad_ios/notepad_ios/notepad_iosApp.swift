//
//  notepad_iosApp.swift
//  notepad_ios
//
//  Created by jerry on 2021/11/15.
//

import SwiftUI

@main
struct notepad_iosApp: App {
    var body: some Scene {
        WindowGroup {
            NoteList().environmentObject(UserData())
        }
    }
}
