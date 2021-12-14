package cn.hsp.demo.ui.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：花生皮编程
 */

open class BaseViewModel : ViewModel() {

    val stateLiveData = MutableLiveData<PageState>().apply {
        value = PageState.Loading
    }

    fun launch(block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch {
            kotlin.runCatching {
                block()
            }.onSuccess {
                stateLiveData.value = PageState.Success
            }.onFailure {
                stateLiveData.value = PageState.Error(it.message)
            }
        }
    }
}

sealed class PageState {
    object Loading : PageState()
    object Success : PageState()
    class Error(val errorMsg: String?) : PageState()

    fun isLoading() = this is Loading

    fun isError() = this is Error
}