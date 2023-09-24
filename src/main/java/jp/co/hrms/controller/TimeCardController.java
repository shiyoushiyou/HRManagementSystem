package jp.co.hrms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;
import jp.co.hrms.service.AttendanceService;

@Controller
public class TimeCardController {
	@Autowired
	private AttendanceService attendanceService;

	@GetMapping("/timeCard")
	public String attendance() {
		//TODO 將登入員工姓名顯示在打卡頁面上
//		Employees EmpInfo  = (Employees) session.getAttribute("empInfo");
//		String name = EmpInfo.getName();
//		mav.addObject("empname", name);
		return "timeCard";
	}

	@PostMapping("/timeCard")
	public ModelAndView timeCard(
			HttpSession session,
			@RequestParam("action") String action,
	        @RequestParam("hiddenDayTime") String daytime) {
		ModelAndView mav = new ModelAndView("timeCard");
		String employeeId = (String) session.getAttribute("user");
		//打卡
		String status = attendanceService.timeCardRecord(employeeId, daytime,action);
		mav.addObject("error", status);
		return mav;
	}
}