package cn.hsp.demo.ui.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import cn.hsp.demo.R
import cn.hsp.demo.ui.vm.PageState

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：花生皮编程
 */

@Composable
fun LoadingPage(
    pageState: PageState,
    loadInit: (() -> Unit)? = null,
    contentView: @Composable BoxScope.() -> Unit
) {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        when {
            pageState.isLoading() -> {
                loadInit?.invoke()
                CircularProgressIndicator()
            }
            pageState.isError() -> {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(20.dp)
                        .clickable {
                            loadInit?.invoke()
                        }
                ) {
                    Image(painterResource(R.drawable.ic_error), null, Modifier.size(80.dp))
                    Text((pageState as PageState.Error).errorMsg.toString())
                }
            }
            else -> {
                contentView()
            }
        }
    }
}
