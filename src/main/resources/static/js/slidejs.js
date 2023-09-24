window.addEventListener('load', function() {
// 獲取 iframe 和按鈕元素
var iframe = document.querySelector('.ifr');
var indexEmployees = document.querySelector('#indexEmployees');
var salary = document.querySelector('#salary');
var request = document.querySelector('#request');
var selectEmployees = document.querySelector('#selectEmployees');
var management = document.querySelector('#management');
var timeCard = document.querySelector('#timeCard');
var insertEmp = document.querySelector('#insertEmp');
var attendanceManagement = document.querySelector('#attendanceManagement');

	// 監聽按鈕的點擊事件
	//切換indexAttendance
	timeCard.addEventListener('click', function() {
		iframe.contentDocument.location.href = 'timeCard'; 
	});

	//切換 salary
	salary.addEventListener('click', function() {
		iframe.contentDocument.location.href = 'salary'; 
	});

	//切換request
	request.addEventListener('click', function() {
		iframe.contentDocument.location.href = 'request'; 
	});
	
	//切換selectEmployees
	selectEmployees.addEventListener('click', function() {
		iframe.contentDocument.location.href = 'selectEmployees'; 
	});
	
	//切換management
	management.addEventListener('click', function() {
		iframe.contentDocument.location.href = 'management'; 
	});
	
		//切換insert
	insertEmp.addEventListener('click', function() {
		iframe.contentDocument.location.href = 'insertEmp'; 
	});
	
	attendanceManagement.addEventListener('click', function() {
		iframe.contentDocument.location.href = 'attendanceManagement'; 
	});
	
		 //切換indexEmployees
	indexEmployees.addEventListener('click', function() {
		iframe.contentDocument.location.href = 'indexEmployees';
	});
	
});