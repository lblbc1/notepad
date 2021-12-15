package cn.hsp.demo.ui.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import cn.hsp.demo.NOTE_CONTENT
import cn.hsp.demo.NOTE_ID
import cn.hsp.demo.db.Note
import cn.hsp.demo.ui.vm.NoteListModel
import cn.hsp.demo.views.TitleBar

@Composable
fun NotePage(navController: NavController) {
    val noteId = navController.previousBackStackEntry?.arguments?.getInt(NOTE_ID)
    val noteContent = navController.previousBackStackEntry?.arguments?.getString(NOTE_CONTENT)
    val viewModel: NoteListModel = viewModel()
    var content by remember { mutableStateOf(noteContent ?: "") }

    Column {
        TitleBar(
            title = "笔记",
            onBack = {
                saveData(noteId, viewModel, content)
                navController.navigateUp()
            },
            imageVector = if(noteId!=null) Icons.Default.Delete else null,
            onRightClick = {
                deleteData(noteId, viewModel)
                navController.navigateUp()
            }
        )
        TextField(value = content,
            placeholder = {
                Text("请输入内容")
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
            modifier = Modifier
                .padding(5.dp)
                .fillMaxSize(),
            onValueChange = {
                content = it
            })
    }
}

private fun saveData(
    noteId: Int?,
    viewModel: NoteListModel,
    content: String
) {
    if (noteId != null) {
        viewModel.update(Note(noteId, content))
    } else {
        viewModel.add(content)
    }
}

private fun deleteData(
    noteId: Int?,
    viewModel: NoteListModel,
) {
    noteId?.let {
        viewModel.delete(it)
    }
}