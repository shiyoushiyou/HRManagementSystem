$(document).ready(function() {
  var time = $("#time");
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
    time.html(year + "年" + month + "月" + day + "日" + "" + "(" + week + ")" + "<br>" + hour + ":" + minute + ":" + second);
  }

  // 绑定事件监听器
  $("button").click(function() {
    var btnType = $(this).text();
    var url = "";
    switch (btnType) {
      case "出勤":
        url = "/hrms/checkin";
        break;
      case "退勤":
        url = "/hrms/checkout";
        break;
      case "休憩":
        url = "/hrms/restin";
        break;
      case "戻り":
        url = "/hrms/restout";
        break;
      default:
        break;
    }
    if (url) {
      $.ajax({
        url: url,
        type: "POST",
        data: {btnType: btnType},
         dataType: "json",
        success: function(data) {
          alert(data.message);
        },
        error: function() {
          alert(btnType + "失敗");
        }  
      });
    }
  });
});

