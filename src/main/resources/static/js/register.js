const pwdChangeBtn = document.getElementById('pwdChange');
const pwdChangeText1 = document.getElementById('pwdChangeText1');
const pwdChangeText2 = document.getElementById('pwdChangeText2');
const pwdChangeText3 = document.getElementById('pwdChangeText3');
const pwdBefore = document.getElementById('pwdBefore');
var passwordRegExp = /^[a-zA-Z0-9]*$/;
// $("#loginForm").submit();
pwdChangeBtn.addEventListener('click', function() {
  pwdChangeText1.style.display = 'table-row';
});

pwdBefore.addEventListener('blur', function() {
  if(pwdBefore.value !== ' '){
    pwdChangeText2.style.display = 'table-row';
    pwdChangeText3.style.display = 'table-row';
    pwd.style.display = 'none';

  } else {
    pwdChangeText2.style.display = 'none';
    pwdChangeText3.style.display = 'none';
  }
});

   function pwdCheck(){
    var userPwd = document.getElementById("pwdAfter").value;
    var msg = document.querySelector("#msg");
    var msgOK = document.querySelector("#msgOK2");

    if(userPwd==null||userPwd.length==0||userPwd.includes(" ")){
        msg.innerText ="輸入的密碼不符合格式!"; 
        msg.style.color = "red";
    }else if(userPwd.length<6){
        msg.innerText ="輸入密碼小於6位"
        msg.style.color = "red";
    }else if(!passwordRegExp.test(userPwd)){
        msg.innerText ="輸入密碼包含不可用文字"
        msg.style.color = "red";
    }else{
        msg.innerText = null;
        msgOK.innerText ="✓";
        msgOK.style.color = "green";
        return  true;
    }
    }
   function samePwd(){
    var userPwd = document.getElementById("pwdComfirm").value;
    var confirmPwd = document.getElementById("pwdAfter").value;
    var msgOK = document.querySelector("#msgOK3");
    var msg = document.querySelector("#msg");
    if(userPwd==confirmPwd){
        msg.innerText = null;
        msgOK.innerText ="✓";
        msgOK.style.color = "green";
        return true;
        
        }else{
            msg.innerText ="兩次密碼不一致"; 
            msg.style.color = "red"; 
        }
    }
    
    function addressRegister(address) {
	  var geocoder = new google.maps.Geocoder();
	  geocoder.geocode({ address: address }, function (results, status) {
	    if (status !== "OK") {
		document.getElementById("addressInput").setCustomValidity("地址不存在");
	    }else{
		 document.getElementById("addressInput").setCustomValidity("");
		}
	  });
		  
}
function checkForm() {
    	var name = $("#name").val();
    	// 确保输入不包含奇怪的符号
   		if (/^[^@_\-.[\]{}()<>*+?|\\/^$]$/.test(name)) {
        alert("特殊な文字を入力しないでください。");
        return false;
    	}    	
    	// 确保日期输入不为空
		var now = new Date();
		// 获取输入的出生日期
		var birthDate = new Date($("#birth").val());
		// 比较输入的出生日期与当前日期
		if (birthDate >= now) {
			alert("出生日期不能晚于今天！");
		return false;
		} 
		// 检查输入的密碼是否相同
    	var onePwd= document.getElementById("pwd").value;
    	var twoPwd = document.getElementById("password").value;
    	if(onePwd != twoPwd){
       		alert("兩次密碼不相同");
        return false;
    	// 检查输入的内容是否符合要求
    	// 提交表单确认弹窗
    	return confirm("申請は確認しますか？確認後、申請は変更できません。");
	}
}
