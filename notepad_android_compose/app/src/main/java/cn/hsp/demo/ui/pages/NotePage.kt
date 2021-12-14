package cn.hsp.demo.ui.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import cn.hsp.demo.NOTE_ID
import cn.hsp.demo.views.TitleBar

@Composable
fun NotePage(navController: NavController) {
    val noteId = navController.previousBackStackEntry?.arguments?.getInt(NOTE_ID)
    Column {
        TitleBar(
            title = "笔记",
            onBack = { navController.navigateUp() },
            onRightClick = {
//                keyboardController?.hide()
//                if (title.isNullOrEmpty()) {
//                    snackLabel = SNACK_WARN
//                    errorMsg = "标题不能为空"
//                    return@HamToolBar
//                }
//                if (linkUrl.isNullOrEmpty()) {
//                    snackLabel = SNACK_WARN
//                    errorMsg = "链接不能为空"
//                    return@HamToolBar
//                }
//                viewModel.addShareArticle()
            }
        )
        Button(
            onClick = {},
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            Text(noteId.toString())
        }
    }

}