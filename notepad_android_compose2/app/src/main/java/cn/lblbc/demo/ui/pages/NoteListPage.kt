package cn.lblbc.demo.ui.pages

import android.os.Bundle
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import cn.lblbc.demo.NOTE_CONTENT
import cn.lblbc.demo.NOTE_ID
import cn.lblbc.demo.PAGE_NOTE
import cn.lblbc.demo.db.Note
import cn.lblbc.demo.ui.theme.MainBgColor
import cn.lblbc.demo.ui.theme.TextColor
import cn.lblbc.demo.ui.vm.NoteListModel

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》  http://lblbc.cn/blog
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：蓝不蓝编程
 */

@ExperimentalMaterialApi
@Composable
fun NoteListPage(navController: NavController) {
    val viewModel: NoteListModel = viewModel()
    val state by viewModel.stateLiveData.observeAsState()
    val noteList by viewModel.noteListLiveData.observeAsState(listOf())

    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(key1 = Unit) {
        val observer = object : LifecycleObserver {
            @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
            fun onResume() {
                viewModel.query()
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose { lifecycleOwner.lifecycle.removeObserver(observer) }
    }

    LoadingPage(
        pageState = state!!
    ) {
        ConstraintLayout() {
            val (addIcon) = createRefs()
            Column(Modifier.fillMaxSize()) {
                BuildTitle()
                BuildNoteList(noteList, navController)
            }
            Icon(
                Icons.Default.AddCircle,
                null,
                Modifier
                    .constrainAs(addIcon) {
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    }
                    .width(100.dp)
                    .height(100.dp)
                    .clickable(onClick = {
                        gotoNotePage(null, navController)
                    })
                    .padding(20.dp),
                tint = MainBgColor
            )
        }

    }
}

@ExperimentalMaterialApi
@Composable
private fun BuildNoteList(
    noteList: List<Note>,
    navController: NavController
) {
    LazyColumn(
        modifier = Modifier.padding(10.dp)
    ) {
        items(noteList.size)
        { index ->
            val note = noteList[index]
            Card(
                backgroundColor = Color.Transparent,
                elevation = 0.dp,
                modifier = Modifier
                    .padding(bottom = 10.dp)
                    .fillMaxWidth(),
                onClick = {
                    gotoNotePage(note, navController)
                }) {
                Text(text = noteList[index].content, maxLines = 1)
            }
        }
    }
}

private fun gotoNotePage(
    note: Note?,
    navController: NavController
) {
    note?.let {
        val bundle = Bundle().apply {
            putInt(NOTE_ID, note.id)
            putString(NOTE_CONTENT, note.content)
        }
        navController.currentBackStackEntry?.replaceArguments(bundle)
    }
    navController.navigate(PAGE_NOTE)
}

@Composable
private fun BuildTitle() {
    Text(
        text = "记事本-蓝不蓝编程",
        modifier = Modifier
            .background(MainBgColor)
            .padding(10.dp)
            .fillMaxWidth(),
        color = TextColor,
        fontSize = 18.sp,
        fontWeight = FontWeight.W500,
    )
}