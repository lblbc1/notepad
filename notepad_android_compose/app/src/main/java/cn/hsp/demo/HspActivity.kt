package cn.hsp.demo

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.IconButton
import androidx.compose.material.IconToggleButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cn.hsp.demo.ui.theme.HspDemoTheme

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：花生皮编程
 */

class MainActivity : ComponentActivity() {
    private val interval = 50L
    private var startTime = 0L
    private var lastTimeElapsed = 0L
    private var timeElapsed = mutableStateOf(0L)
    private var isStarted = mutableStateOf(false)
    private var handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HspDemoTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    HspPage(timeElapsed, isStarted) { cmd ->
                        when (cmd) {
                            CMD_START -> startTimer()
                            CMD_PAUSE -> pauseTimer()
                            else -> resetTimer()
                        }
                    }
                }
            }
        }
    }

    private fun changeText() {
        val offset = System.currentTimeMillis() - startTime + lastTimeElapsed
        timeElapsed.value = offset
        handler.postDelayed({ changeText() }, interval)
    }

    private fun startTimer() {
        startTime = System.currentTimeMillis()
        handler.postDelayed({ changeText() }, interval)
    }

    private fun pauseTimer() {
        handler.removeCallbacksAndMessages(null)
        lastTimeElapsed += System.currentTimeMillis() - startTime
    }

    private fun resetTimer() {
        handler.removeCallbacksAndMessages(null)
        lastTimeElapsed = 0
        timeElapsed.value = 0
        isStarted.value = false
    }

}

@Composable
fun HspPage(
    _timeElapsed: MutableState<Long>,
    _isStarted: MutableState<Boolean>,
    callBack: (Int) -> Unit
) {
    var timeElapsed by remember { _timeElapsed }
    var isStarted by remember { _isStarted }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize(),
    ) {
        Text(TimeUtil.formatTime(timeElapsed), fontSize = 40.sp)

        Row(
            modifier = Modifier.padding(top = 50.dp)
        ) {
            IconButton(onClick = { callBack(CMD_RESET) }) {
                Image(painterResource(R.drawable.ic_reset), "")
            }
            IconToggleButton(
                modifier = Modifier.padding(start = 20.dp),
                checked = isStarted,
                onCheckedChange = {
                    if (isStarted) {
                        callBack(CMD_PAUSE)
                    } else {
                        callBack(CMD_START)
                    }
                    isStarted = it
                },
            ) {
                Image(painterResource(getImageRes(isStarted)), "")
            }
        }
    }
}

@Composable
private fun getImageRes(isStarted: Boolean): Int {
    return if (isStarted) {
        R.drawable.ic_pause
    } else {
        R.drawable.ic_start
    }
}

val CMD_START = 1
val CMD_PAUSE = 2
val CMD_RESET = 3