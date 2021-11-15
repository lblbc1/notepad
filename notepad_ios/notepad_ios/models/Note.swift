import Foundation
struct Note: Hashable,Codable,Identifiable{
    let id = UUID()
    var text:String
    var date = Date()
}
