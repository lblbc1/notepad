import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

import 'db_helper.dart';

/// 厦门大学计算机专业 | 前华为工程师
/// 分享编程技术，没啥深度，但看得懂，适合初学者。
/// Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
/// 公众号：花生皮编程
class AddNotePage extends StatefulWidget {
  AddNotePage({Key? key}) : super(key: key);

  @override
  createState() => _AddNotePageState();
}

class _AddNotePageState extends State<AddNotePage> {
  final NoteDatabase _noteDataBase = NoteDatabase();
  final TextEditingController _contentController = TextEditingController();
  String content = "";

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text("新建笔记"),
        leading: IconButton(
          icon: const Icon(Icons.arrow_back),
          onPressed: () {
            Navigator.pop(context);
          },
        ),
        actions: [
          IconButton(
            icon: const Icon(Icons.done),
            onPressed: () {
              addNote();
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
          decoration: const InputDecoration.collapsed(
              hintText: "请输入笔记", border: InputBorder.none),
          controller: _contentController,
        )),
      ],
    );
  }

  addNote() async {
    String content = _contentController.text;
    await _noteDataBase.openSqlite();
    await _noteDataBase.insert(Note(content));
    await _noteDataBase.close();
    Navigator.of(context).pop("");
  }
}
