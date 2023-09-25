window.addEventListener('load', function() {
	// 獲取 iframe 和按鈕元素
	var iframe = document.querySelector('.ifr');
	var navLinks = document.querySelectorAll('.nav-link');
	
	// 監聽所有的nav-link的點擊事件
	navLinks.forEach(function(navLink) {
	    navLink.addEventListener('click', function() {
	        var dataValue = navLink.getAttribute('data-value');
	        iframe.contentDocument.location.href = dataValue;
	    });
	});
});

$(document).ready(function(){
    $('.sub-btn').click(function(){
        $(this).next('.sub-menu').slideToggle();
    });
});  