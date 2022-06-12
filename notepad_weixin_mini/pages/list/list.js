/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》  http://lblbc.cn/blog
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：蓝不蓝编程
 **/
var listUtil = require('../../utils/list.js')

Page({
  data: {
    dataList: [],
  },
  onLoad: function () {
    // this.loadTodoList()
  },
  onShow: function () {
    this.loadTodoList()
  },
  methods: {
  },
  loadTodoList() {
    var dataList = wx.getStorageSync('list').list
    this.setData({
      dataList: dataList
    })
  },
  addData() {
    wx.navigateTo({
      url: '/pages/add/add'
    })
  },
  viewTodo(e) {
    let dataId = e.currentTarget.dataset['id'];
    wx.navigateTo({
      url: '/pages/edit/edit?id=' + dataId
    })
  },
  longPressed(e) {
    let _this = this
    let dataId = e.currentTarget.dataset['id'];
    wx.showActionSheet({
      itemList: ['删除'],
      success: function (res) {
        _this.deleteTodo(dataId)
      },
      fail: function (res) { }
    })
  },
  deleteTodo(dataId) {
    var newDataList = {};
    newDataList.list = listUtil.delDataById(this.data.dataList, dataId);
    wx.setStorageSync('list', newDataList);
    this.loadTodoList()
  },
})