import 'package:flutter/material.dart';
import 'db_helper.dart';

/// 厦门大学计算机专业 | 前华为工程师
/// 分享编程技术，没啥深度，但看得懂，适合初学者。
/// Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
/// 公众号：花生皮编程
void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return const MaterialApp(
      debugShowCheckedModeBanner: false,
      title: '花生皮编程',
      home: MyHomePage(title: '花生皮编程'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({Key? key, required this.title}) : super(key: key);
  final String title;

  @override
  _MyHomePageState createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  BookDatabase bookSqlite = BookDatabase();
  var name = "";

  @override
  void initState() {
    super.initState();
    addData();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.title),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            Text(
              name,
            ),
            ElevatedButton(
              child: Text('写数据'),
              onPressed: () {
                addData();
              },
            ),
            ElevatedButton(
              child: Text('读数据'),
              onPressed: () {
                queryData();
              },
            ),
            ElevatedButton(
              child: Text('改数据'),
              onPressed: () {
                modifyData();
              },
            ),
            ElevatedButton(
              child: Text('删数据'),
              onPressed: () {
                deleteData();
              },
            )
          ],
        ),
      ),
    );
  }

  void addData() async {
    await bookSqlite.openSqlite();
    await bookSqlite.insert(Book(0, "花生皮编程"));
    await bookSqlite.close();
    setState(() {
      name = "已成功写入数据";
    });
  }

  void queryData() async {
    await bookSqlite.openSqlite();
    Book? book = await bookSqlite.query(0);
    await bookSqlite.close();
    setState(() {
      if (book == null) {
        name = "数据不存在";
      } else {
        name = book.name;
      }
    });
  }

  void modifyData() async {
    await bookSqlite.openSqlite();
    Book? book = await bookSqlite.query(0);
    if (book != null) {
      book.name = "花生皮编程2";
      await bookSqlite.update(book);
      await bookSqlite.close();
      setState(() {
        name = book.name;
      });
    }
  }

  void deleteData() async {
    await bookSqlite.openSqlite();
    await bookSqlite.delete(0);
    await bookSqlite.close();
    setState(() {
      name = "数据已删除";
    });
  }
}
