/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》  http://lblbc.cn/blog
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：蓝不蓝编程
 **/
var listUtil = require('../../utils/list.js')

Page({
  data: {
    dataId: 0,
    content: ""
  },
  onLoad: function (option) {
    this.loadData(option.id)
  },
  methods: {
  },
  loadData(dataId) {
    var dataList = wx.getStorageSync('list') || []
    var arr = dataList.list.filter(function (item) { return (item.id == dataId) })
    var dataItem = arr[0]
    this.setData({
      dataId: dataId,
      content: dataItem.content
    })

  },
  modifyData(e) {
    var dataList = wx.getStorageSync('list')
    var value = e.detail.value
    if (value.content == '' || value.content == null) {
      wx.showToast({
        title: '内容不能为空',
        icon: 'error'
      })
      return
    }
    value.id = this.data.dataId
    dataList.list = listUtil.delDataById(dataList.list, this.data.dataId)
    dataList.list.unshift(value)
    wx.setStorageSync('list', dataList)
    wx.navigateBack()
  }

})