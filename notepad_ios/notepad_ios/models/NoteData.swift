import Foundation
class NoteData{
    static let shared = NoteData()
    
    var notes:[Note] = [
        Note(text: "花生皮编程1"),
        Note(text: "花生皮编程2")
    ]
    
    private init() { load() }
    
    func load(){
        if let data = UserDefaults.standard.object(forKey: "notes") as? Data{
            self.notes = try! PropertyListDecoder().decode([Note].self, from: data)
        }
    }
    
    func save(){
        UserDefaults.standard.set(try? PropertyListEncoder().encode(notes),forKey: "notes")
    }
    
    static func dateToString(date:Date) -> String {
        let formatter = DateFormatter()
        formatter.dateStyle = .medium
        return formatter.string(from: date)
    }
}
