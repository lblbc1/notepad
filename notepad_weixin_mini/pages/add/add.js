/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：花生皮编程
 **/
var util = require('../../utils/util.js')

Page({
  data: {
    content: "",
    date: "2022-01-01"
  },
  bindDateChange: function (e) {
    console.log('picker发送选择改变，携带值为', e.detail.value)
    this.setData({
      date: e.detail.value
    })
  },
  addData(e) {
    var dataList = wx.getStorageSync('list') || { index: 0, list: [], time: 0 };
    var value = e.detail.value;
    if (value.content == '' || value.content == null) {
      wx.showToast({
        title: '内容不能为空',
        icon: 'error'
      })
      return
    }
    value.id = util.wxuuid();
    dataList.list.unshift(value);
    wx.setStorageSync('list', dataList);
    wx.navigateBack()
  }
})