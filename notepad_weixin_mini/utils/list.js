/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》  http://lblbc.cn/blog
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：蓝不蓝编程
 **/
function topData(arr,id){
  var temp = [];
  var tempdata = [];
  for(var i = 0;i<arr.length;i++){
    if(id!=arr[i].id){
      temp.push(arr[i]);
    }else{
      tempdata = arr[i];
    }
  }
  temp.unshift(tempdata);
  return temp;
}

function finishData(arr,id){
  var temp = [];
  var tempdata = [];
  for(var i = 0;i<arr.length;i++){
    if(id!=arr[i].id){
      temp.push(arr[i]);
    }else{
      tempdata = arr[i];
    }
  }
  if(tempdata.finish == 'fn'){
    tempdata.finish = null;
    temp.unshift(tempdata);
  }else{
    tempdata.finish = 'fn';
    temp.push(tempdata);
  }
  
  return temp;
}

function delDataById(arr,id){
  var temp = [];
  for(var i = 0;i<arr.length;i++){
    if(id!=arr[i].id){
      temp.push(arr[i]);
    }
  }
  return temp;
}

module.exports = {
  delDataById:delDataById,
  finishData:finishData,
  topData:topData
}
