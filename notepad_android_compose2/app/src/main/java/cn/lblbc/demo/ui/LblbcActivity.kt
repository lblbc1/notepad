package cn.lblbc.demo.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import cn.lblbc.demo.PAGE_LIST
import cn.lblbc.demo.PAGE_NOTE
import cn.lblbc.demo.ui.pages.NoteListPage
import cn.lblbc.demo.ui.pages.NotePage
import cn.lblbc.demo.ui.theme.LblbcDemoTheme

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》  http://lblbc.cn/blog
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：蓝不蓝编程
 */

@ExperimentalMaterialApi
class LblbcActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LblbcDemoTheme {
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