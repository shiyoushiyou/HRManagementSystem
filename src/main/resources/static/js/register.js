// 獲取彈窗和按鈕的引用
var openForm = document.querySelector(".changepwd");
var closeButton = document.querySelector(".close");
var closeButton2 = document.getElementById("closePopup2");
var popup1 = document.querySelector(".popup1");
var popup2 = document.querySelector(".popup2");

// 點擊按鈕打開彈窗
openForm.addEventListener('click', function () {
  popup1.style.display = "block";
});

// 點擊關閉按鈕或彈窗背景隱藏彈窗
closeButton.addEventListener("click", function () {
  popup1.style.display = "none";
});

closeButton2.addEventListener("click", function () {
  popup2.style.display = "none";
});

popup1.addEventListener("click", function (event) {
  if (event.target === popup1) {
    popup1.style.display = "none";
  }
});

/**
 * 變更密碼前的當前密碼驗證
 * @returns true or false
 */
$("#popupFormCurrentPwd").submit(function (e) {
  e.preventDefault(); // 防止表單提交

  var currentPassword = $("#currentPwd").val(); 

  $.ajax({
    type: "POST",
    url: "/hrms/registerCurrentPwd",
    data: { currentPassword: currentPassword },
    success: function (response) {
      if (response === "true") {
        // 當前密碼驗證成功，顯示第二個彈窗
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



/**
 * 驗證欲變更密碼是否符合規範
 */
$("#changePwd").submit(function (e){
  var newPwd = document.querySelector(".newPwd").value;
  var pwdregister = document.querySelector(".pwdregister").value;
  
  // 英文字母開頭
  const startsWithLetterRegex = /^[A-Za-z]/;

  // 不允許連續3 次或更多相同的字母或数字
  const repeatingCharsRegex = /(.)\1\1/;

  if (!startsWithLetterRegex.test(newPwd)) {
    alert("パスワードは英文字母で始まる必要があります。");
    return false;
  }else if (repeatingCharsRegex.test(newPwd)) {
    alert("パスワードには連続して 3 回以上同じ文字が使用できません。");
    return false;
  }else if (newPwd !== pwdregister) {
    alert("入力したパスワードが一致しません。");
    return false;
  }else if (newPwd.length < 6 || newPwd.length > 20) {
    alert("パスワードは6文字から20文字までです。");
    return false;
  }else{
    // 彈窗確認
    return confirm("申請は確認しますか？確認後、申請は変更できません。");
  }

});
