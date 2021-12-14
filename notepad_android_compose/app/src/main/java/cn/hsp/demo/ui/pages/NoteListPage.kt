package cn.hsp.demo.ui.pages

import android.os.Bundle
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import cn.hsp.demo.NOTE_ID
import cn.hsp.demo.PAGE_NOTE
import cn.hsp.demo.ui.vm.NoteListModel

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：花生皮编程
 */

@ExperimentalMaterialApi
@Composable
fun NoteListPage(navController: NavController) {
    val viewModel: NoteListModel = viewModel()
    val state by viewModel.stateLiveData.observeAsState()
    val noteList by viewModel.noteListLiveData.observeAsState(listOf())

    LoadingPage(
        pageState = state!!,
        loadInit = { viewModel.query() }) {
        Box(Modifier.fillMaxSize()) {
            LazyColumn {
                items(noteList.size)
                { index ->
                    val note = noteList[index]
                    Card(onClick = {
                        val bundle = Bundle().apply { putInt(NOTE_ID, note.id) }
                        navController.currentBackStackEntry?.replaceArguments(bundle)
                        navController.navigate(PAGE_NOTE)
                    }) {
                        Text(text = noteList[index].content)
                    }
                }
            }
        }
    }
}