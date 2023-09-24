$(document).ready(function() {
  var dayBlock = $("#day");
  var timeBlock = $("#time");
  var hiddenDayTimeInput = $("#hiddenDayTime");

  
  getTime();
  setInterval(getTime, 1000);

  function getWeek(day) {
    var weekArr = ["日", "月", "火", "水", "木", "金", "土"];
    return weekArr[day];
  }

  function getTime() {
    var d = new Date();
    var year = d.getFullYear();
    var month = d.getMonth() + 1;
    var day = d.getDate();
    var week = getWeek(d.getDay());
    var hour = d.getHours().toString().padStart(2, "0");
    var minute = d.getMinutes().toString().padStart(2, "0");
    var second = d.getSeconds().toString().padStart(2, "0");
    dayBlock.html(year + "年" + month + "月" + day + "日" + "" + "(" + week + ")" );
    timeBlock.html(hour + ":" + minute + ":" + second);
    
    hiddenDayTimeInput.val(year + "/" + month + "/" + day+" "+hour + ":" + minute + ":" + second);
  }

});

