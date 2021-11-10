import 'package:path/path.dart';
import 'package:sqflite/sqflite.dart';

/// 厦门大学计算机专业 | 前华为工程师
/// 分享编程技术，没啥深度，但看得懂，适合初学者。
/// Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
/// 公众号：花生皮编程

const String tableBook = 'book';
const String columnId = '_id';
const String columnName = 'name';

class Book {
  int id = 0;
  String name = "";

  Map<String, dynamic> toMap() {
    var map = <String, dynamic>{
      columnName: name,
    };
    map[columnId] = id;
    return map;
  }
}

  Book(this.id, this.name);

  Book.fromMap(Map<String, dynamic> map) {
    id = map[columnId];
    name = map[columnName];
  }
}

class BookDatabase {
  late Database db;

  openSqlite() async {
    var databasesPath = await getDatabasesPath();
    String path = join(databasesPath, 'book.db');

    db = await openDatabase(path, version: 1,
        onCreate: (Database db, int version) async {
      await db.execute('''
          CREATE TABLE $tableBook (
            $columnId INTEGER PRIMARY KEY, 
            $columnName TEXT)
          ''');
    });
  }

  Future<Book> insert(Book book) async {
    book.id = await db.insert(tableBook, book.toMap());
    return book;
  }

  Future<Book?> query(int id) async {
    List<Map<String,dynamic>> maps = await db.query(tableBook,
        columns: [columnId, columnName],
        where: '$columnId = ?',
        whereArgs: [id]);
    if (maps.isNotEmpty) {
      return Book.fromMap(maps.first);
    }
    return null;
  }

  Future<List<Book>> queryAll() async {
    List<Map<String,dynamic>> maps = await db.query(tableBook, columns: [columnId, columnName]);
    List<Book> books = [];

    if (maps.isEmpty) {
      return books;
    }

    for (int i = 0; i < maps.length; i++) {
      books.add(Book.fromMap(maps[i]));
    }
    return books;
  }

  Future<int> delete(int id) async {
    return await db.delete(tableBook, where: '$columnId = ?', whereArgs: [id]);
  }

  Future<int> update(Book book) async {
    return await db.update(tableBook, book.toMap(),
        where: '$columnId = ?', whereArgs: [book.id]);
  }

  close() async {
    await db.close();
  }
}
