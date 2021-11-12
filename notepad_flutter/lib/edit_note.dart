import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

import 'db_helper.dart';

/// 厦门大学计算机专业 | 前华为工程师
/// 分享编程技术，没啥深度，但看得懂，适合初学者。
/// Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
/// 公众号：花生皮编程
class EditNotePage extends StatefulWidget {
  final int noteId;

  EditNotePage({Key? key, required this.noteId}) : super(key: key);

  @override
  createState() => _EditNotePageState(noteId);
}

class _EditNotePageState extends State<EditNotePage> {
  int _noteId = 0;
  final NoteDatabase _noteDataBase = NoteDatabase();
  TextEditingController _contentController = TextEditingController();

  _EditNotePageState(int noteId) {
    _noteId = noteId;
  }

  @override
  void initState() {
    super.initState();
    queryData();
  }

  queryData() async {
    NoteDatabase noteDataBase = NoteDatabase();
    await noteDataBase.openSqlite();
    Note? note = await noteDataBase.query(_noteId);
    await noteDataBase.close();
    if (note != null) {
      setState(() {
        _contentController.text = note.content;
      });
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        leading: IconButton(
          icon: Icon(Icons.arrow_back),
          onPressed: () {
            Navigator.pop(context);
          },
        ),
        actions: [
          IconButton(
            icon: const Icon(Icons.done),
            onPressed: () {
              modifyNote();
            },
          ),
          IconButton(
            icon: const Icon(Icons.delete),
            onPressed: () {
              deleteNote();
            },
          )
        ],
      ),
      body: Center(
        child: Container(
            margin: EdgeInsets.fromLTRB(20, 20, 20, 20), child: buildColumn()),
      ),
    );
  }

  Column buildColumn() {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.center,
      mainAxisAlignment: MainAxisAlignment.center,
      children: <Widget>[
        Expanded(
            child: TextField(
          maxLines: null,
          decoration: const InputDecoration(
              hintText: "请输入笔记", border: InputBorder.none),
          controller: _contentController,
        )),
      ],
    );
  }

  modifyNote() async {
    String content = _contentController.text;
    await _noteDataBase.openSqlite();
    Note? note = await _noteDataBase.query(_noteId);
    if (note != null) {
      note.content = content;
      await _noteDataBase.update(note);
      await _noteDataBase.close();
    }
    Navigator.of(context).pop("");
  }

  deleteNote() async {
    await _noteDataBase.openSqlite();
    await _noteDataBase.delete(_noteId);
    await _noteDataBase.close();
    Navigator.of(context).pop("");
    // Navigator.of(context).popUntil((route) => route.isFirst); //回到首页
  }
}
