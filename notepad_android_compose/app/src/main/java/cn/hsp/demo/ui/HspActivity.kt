package cn.hsp.demo.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import cn.hsp.demo.PAGE_LIST
import cn.hsp.demo.PAGE_NOTE
import cn.hsp.demo.ui.pages.NoteListPage
import cn.hsp.demo.ui.pages.NotePage
import cn.hsp.demo.ui.theme.HspDemoTheme

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：蓝不蓝编程
 */

@ExperimentalMaterialApi
class HspActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HspDemoTheme {
//                Surface(
//                    modifier = Modifier
//                        .fillMaxSize()
//                ) {
                HspNavigation()
//                }
            }
        }
    }

}

@ExperimentalMaterialApi
@Composable
fun HspNavigation() {
    val navController = rememberNavController()
    HspNavHost(
        navController = navController,
    )
}

@ExperimentalMaterialApi
@Composable
fun HspNavHost(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = PAGE_LIST
    ) {
        composable(PAGE_LIST) {
            NoteListPage(navController = navController)
        }
        composable(PAGE_NOTE) {
            NotePage(navController = navController)
        }
    }
}