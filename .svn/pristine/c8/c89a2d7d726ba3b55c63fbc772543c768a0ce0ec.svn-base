const dateInput = document.getElementById("dateInput");
const yesterdayBtn = document.getElementById("yesterdayBtn");
const tomorrowBtn = document.getElementById("tomorrowBtn");

// 添加“前日”按钮点击事件监听器
yesterdayBtn.addEventListener("click", () => {
	const currentDate = new Date(dateInput.value);
	currentDate.setDate(currentDate.getDate() - 1);
	dateInput.value = currentDate.toISOString().slice(0, 10);
});

// 添加“翌日”按钮点击事件监听器
tomorrowBtn.addEventListener("click", () => {
	const currentDate = new Date(dateInput.value);
	currentDate.setDate(currentDate.getDate() + 1);
	dateInput.value = currentDate.toISOString().slice(0, 10);
});


//ajax的過濾器，實現選擇部門欄位時篩選出該部門的員工
$(function() {
	// 當部門欄位改變時
	$('#departmentId').change(function() {
		var departmentId = $(this).val();
		console.log(departmentId);
		if (departmentId != null) {
			// 使用Ajax請求後端，獲取符合該部門的員工列表
			$.ajax({
				type: 'GET',
				url: '/hrms/attendanceAjax',
				data: { departmentId },
				dataType: 'json',
				success: function(response) {
					// 當 Ajax 請求成功時，會調用該函數
					// response 是服務器端返回的 JSON 格式數據，即員工列表

					var employeeSelect = $('#empSelect');
					employeeSelect.empty();
					employeeSelect.append($('<option>', {
						value: '',
						text: ''
					}));
					// 遍歷員工列表，將每個員工的 id 和名字添加到新的下拉選項中
					$.each(response, function(i, employees) {
						employeeSelect.append($('<option>', {
							value: employees.id,
							text: employees.name
						}));
					});

				},
			});
		}
	});
});


  function showDialog(button) {
  var dialog = document.getElementById("dialog");
  dialog.style.display = "block";
  var input = document.getElementById("timeInput");
  input.focus(); // 設置焦點
  dialog.dataset.buttonId = button;

}

function updateTime() {
  var input = document.getElementById("timeInput");
  var newTime = input.value;
  var dialog = document.getElementById("dialog");
  dialog.style.display = "none";
  var buttonId = dialog.dataset.buttonId; // 獲取要更新的按鈕的 ID
  var button = document.getElementById(buttonId);
  button.innerText = newTime;
}
function cancel() {
	var dialog = document.getElementById("dialog");
	dialog.style.display = "none";
}
  
  
  