import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

import 'add_note.dart';
import 'db_helper.dart';
import 'edit_note.dart';

/// 厦门大学计算机专业 | 前华为工程师
/// 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
/// 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
/// 公众号：花生皮编程
void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return const MaterialApp(
      debugShowCheckedModeBanner: false,
      title: '花生皮编程',
      home: NoteListPage(),
    );
  }
}

class NoteListPage extends StatelessWidget {
  const NoteListPage();

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: '花生皮编程',
      home: NoteListWidget(),
    );
  }
}

class NoteListWidget extends StatefulWidget {
  NoteListWidget();

  @override
  createState() => _NoteListState();
}

class _NoteListState extends State<NoteListWidget> {
  List _notes = [];

  @override
  void initState() {
    super.initState();
    queryData();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("花生皮笔记"),
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: gotoAddNotePage,
        child: Icon(Icons.add),
      ),
      body: Center(
        child: getBody(),
      ),
    );
  }

  gotoAddNotePage() {
    Navigator.push(context, MaterialPageRoute(builder: (context) => AddNotePage())).then((value) => queryData());
  }

  queryData() async {
    NoteDatabase noteDataBase = NoteDatabase();
    await noteDataBase.openSqlite();
    List<Note> notes = await noteDataBase.queryAll();
    await noteDataBase.close();
    setState(() {
      _notes = notes;
    });
  }

  getItem(note) {
    var row = Container(
      margin: EdgeInsets.all(4.0),
      child: InkWell(
        onTap: () {
          onRowClick(note);
        },
        child: buildRow(note),
      ),
    );
    return Card(
      child: row,
    );
  }

  Row buildRow(Note note) {
    return Row(
      children: <Widget>[
        Expanded(
            child: Container(
          margin: EdgeInsets.only(left: 8.0),
          height: 40.0,
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            mainAxisAlignment: MainAxisAlignment.center,
            children: <Widget>[
              Text(
                note.content,
                style: TextStyle(
                  fontSize: 18.0,
                ),
                maxLines: 1,
              ),
            ],
          ),
        ))
      ],
    );
  }

  getBody() {
    if (_notes.isNotEmpty) {
      return ListView.builder(
          itemCount: _notes.length,
          itemBuilder: (BuildContext context, int position) {
            return getItem(_notes[position]);
          });
    }
  }

  onRowClick(Note onte) {
    Navigator.push(context, MaterialPageRoute(builder: (context) => EditNotePage(noteId: onte.id)))
        .then((value) => queryData());
  }
}
