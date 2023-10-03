$(document).ready(function () {
    $('#searchResultTable').DataTable({
        "searching": false,
        "columns": [
            { "data": "name" },
            { "data": "status" },
            { "data": "gender" },
            { "data": "departmentName" },
            { "data": "positionName" },
            { "data": "details" }
        ],
        language: {
            "emptyTable": "テーブルにデータがありません",
            "info": " _TOTAL_ 件中 _START_ から _END_ まで表示",
            "infoEmpty": " 0 件中 0 から 0 まで表示",
            "infoFiltered": "（全 _MAX_ 件より抽出）",
            "infoThousands": ",",
            "lengthMenu": "_MENU_ 件表示",
            "loadingRecords": "読み込み中...",
            "processing": "処理中...",
            "search": "検索:",
            "zeroRecords": "一致するレコードがありません",
            "paginate": {
                "first": "先頭",
                "last": "最終",
                "next": "次",
                "previous": "前"
            },
            "aria": {
                "sortAscending": ": 列を昇順に並べ替えるにはアクティブにする",
                "sortDescending": ": 列を降順に並べ替えるにはアクティブにする"
            },
            "thousands": ",",
            "buttons": {
                "colvis": "項目の表示\/非表示",
                "csv": "CSVをダウンロード",
                "collection": "コレクション"
            },
            "searchBuilder": {
                "add": "条件を追加",
                "button": {
                    "0": "カスタムサーチ",
                    "_": "カスタムサーチ (%d)"
                },
                "clearAll": "すべての条件をクリア",
                "condition": "条件",
                "conditions": {
                    "date": {
                        "after": "次の日付以降",
                        "before": "次の日付以前",
                        "between": "次の期間に含まれる",
                        "empty": "空白",
                        "equals": "次の日付と等しい",
                        "not": "次の日付と等しくない",
                        "notBetween": "次の期間に含まれない",
                        "notEmpty": "空白ではない"
                    },
                    "number": {
                        "between": "次の値の間に含まれる",
                        "empty": "空白",
                        "equals": "次の値と等しい",
                        "gt": "次の値よりも大きい",
                        "gte": "次の値以上",
                        "lt": "次の値未満",
                        "lte": "次の値以下",
                        "not": "次の値と等しくない",
                        "notBetween": "次の値の間に含まれない",
                        "notEmpty": "空白ではない"
                    },
                    "string": {
                        "contains": "次の文字を含む",
                        "empty": "空白",
                        "endsWith": "次の文字で終わる",
                        "equals": "次の文字と等しい",
                        "not": "次の文字と等しくない",
                        "notEmpty": "空白ではない",
                        "startsWith": "次の文字から始まる",
                        "notContains": "次の文字を含まない",
                        "notStartsWith": "次の文字で始まらない",
                        "notEndsWith": "次の文字で終わらない"
                    }
                },
                "data": "項目",
                "title": {
                    "0": "カスタムサーチ",
                    "_": "カスタムサーチ (%d)"
                },
                "value": "値"
            },
            "autoFill": {
                "cancel": "キャンセル",
                "fillHorizontal": "横でセルを書き込む",
                "fillVertical": "縦でセルを書き込む"
            }
        }

    });

});


document.addEventListener("DOMContentLoaded", function () {
    var profile = document.getElementById("profile");
    var closePopup = document.getElementById("closePopup");
    var tableBody = document.getElementById("searchResultTable").getElementsByTagName("tbody")[0];
	
	
    tableBody.addEventListener("click", function (event) {
        var target = event.target;
		var button = target.closest("button");
        // 检查点击的元素是否是具有id="detail"的按钮
        if (button) {
            var empId = button.getAttribute("data-parameter1"); 
            fetchEmployeeInfo(empId) ;
            profile.style.display = "block";
        }
    });
    //TODO 或許無法解決分頁後按鈕無法點擊，須測試。
    var detail = document.getElementById("myTable");
    $('#searchResult tbody').on('click', 'button.detail', function () {
	    var empId = $(this).data("emp-id"); // 获取 data-emp-id 属性的值
        console.log("Employee ID: " + empId);
        console.log(detail);
        profile.style.display = "block";
    });

    closePopup.addEventListener("click", function () {
        profile.style.display = "none";
    });
});

function fetchEmployeeInfo(empId) {
    $.ajax({
        type: "POST",
        url: "/hrms/empInfo",
        data: { empId: empId },
        success: function (employeeData) {
            // 请求成功，处理返回的数据
            console.log(employeeData);
            $(".empId").text(employeeData.id);
		    $(".empbirth").text(employeeData.birth);
		    $(".empName").text(employeeData.name);
		    $(".departmentName").text(employeeData.departmentName);
		    $(".status").text(employeeData.status);
		    $(".hireDate").text(employeeData.hireDate);
		    $(".phoneNumber").text(employeeData.phoneNumber);
		    $(".address").text(employeeData.address);
        },
        error: function (xhr, status, error) {
            // 请求失败，处理错误
            console.error("请求失败：" + error+"status：" +status);
        }
    });
}

$(document).ready(function () {
    var optionsButton = $("#optionsButton");
    var optionsMenu = $("#optionsMenu");

    optionsButton.click(function (event) {
        event.stopPropagation();

        if (optionsMenu.is(":visible")) {
            optionsMenu.hide();
        } else {
            optionsMenu.show();
        }
    });

    $(document).click(function () {
        optionsMenu.hide();
    });

    optionsMenu.click(function (event) {
        event.stopPropagation();
    });

    $("#editInfo").click(function () {
        // 处理情報編集的逻辑
        // 在这里执行你的操作
        optionsMenu.hide();
    });

    $("#deleteInfo").click(function () {
        // 处理情報削除的逻辑
        // 在这里执行你的操作
        optionsMenu.hide();
    });

    $("#resetPassword").click(function () {
        var currentPassword = prompt("请输入密码以继续：");
        cheackpwd(currentPassword);
        optionsMenu.hide();
    });
});


function resetPassword(defultPwd) {
	    $.ajax({
        type: "POST",
        url: "/hrms/resetPwd", // 請更改為你的URL
        data: { defultPwd: defultPwd },
        success: function (response) {
          if (response === "true") {
			    alert("密码已重置");
            // 當前密碼驗證成功，重設密碼

          } else {
            // 當前密碼驗證失敗，顯示錯誤訊息
            alert("密码不正确");
          }
        },
        error: function (xhr, status, error) {
          // 錯誤處理
          if (xhr.status === 404) {
            console.log("頁面不存在");
          } else if (xhr.status === 500) {
            console.log("伺服器錯誤");
          } else {
            console.log("未知錯誤");
          }
        }
      });

}

function cheackpwd(currentPassword) {
    $.ajax({
        type: "POST",
        url: "/hrms/registerCurrentPwd", // 請更改為你的URL
        data: { currentPassword: currentPassword },
        success: function (response) {
          if (response === "true") {
            // 當前密碼驗證成功，重設密碼
            var defultPwd = "demo123"; 
            resetPassword(defultPwd);
          } else {
            // 當前密碼驗證失敗，顯示錯誤訊息
            alert("密码不正确");
          }
        },
        error: function (xhr, status, error) {
          // 錯誤處理
          if (xhr.status === 404) {
            console.log("頁面不存在");
          } else if (xhr.status === 500) {
            console.log("伺服器錯誤");
          } else {
            console.log("未知錯誤");
          }
        }
      });
}