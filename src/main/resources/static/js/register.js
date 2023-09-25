// 獲取彈窗和按鈕的引用
var openForm = document.querySelector(".changepwd");
var closeButton = document.getElementById("closePopup");
var popup = document.querySelector(".popup1");

// 點擊按鈕打開彈窗
openForm.addEventListener('click', function () {
  popup.style.display = "block";
});

// 點擊關閉按鈕或彈窗背景隱藏彈窗
closeButton.addEventListener("click", function () {
  popup.style.display = "none";
});
popup.addEventListener("click", function (event) {
  if (event.target === popup) {
    popup.style.display = "none";
  }
});


$("#popupFormCurrentPwd").submit(function (e) {
  e.preventDefault(); // 防止表單提交

  var currentPassword = $("#currentPwd").val(); // 獲取當前密碼

  $.ajax({
    type: "POST",
    url: "/hrms/registerCurrentPwd", // 請更改為你的URL
    data: { currentPassword: currentPassword },
    success: function (response) {
      if (response === "true") {
        // 當前密碼驗證成功，顯示第二個彈窗
        console.log("OK~~~~~~~~~~~~~~");
        document.getElementById("popup2").style.display = "block";
        document.getElementById("popup1").style.display = "none";
      } else {
        // 當前密碼驗證失敗，顯示錯誤訊息
        document.getElementById("msg").innerText = "當前密碼不正確。";
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
});