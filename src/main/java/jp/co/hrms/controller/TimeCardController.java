package jp.co.hrms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;
import jp.co.hrms.model.Employees;
import jp.co.hrms.service.AttendanceService;
import jp.co.hrms.service.EmployeesService;

@Controller
public class TimeCardController {
	
	@Autowired
	private AttendanceService attendanceService;
	@Autowired
	private EmployeesService employeesService;

	/**
	 * 打卡頁面
	 * 
	 * @param HttpSession session
     * @return ModelAndView，將登入員工資訊加載到前端
	 *
	 */
	@GetMapping("/timeCard")
	public ModelAndView attendance(HttpSession session) {
		ModelAndView mav = new ModelAndView("timeCard");
		String employee = (String) session.getAttribute("user");
		Employees EmpInfo = employeesService.getEmployeesByUserid(employee);
		mav.addObject("empName", EmpInfo.getName());
		return mav;
	}
	
	
	
	/**
	 * 打卡功能
	 * 
	 * @param HttpSession session,String action,String daytime
     * @return ModelAndView，將msg資訊加載到前端
	 *
	 */
	@PostMapping("/timeCard")
	public ModelAndView timeCard(
			HttpSession session,
			@RequestParam("action") String action,
	        @RequestParam("hiddenDayTime") String daytime) {
		ModelAndView mav = new ModelAndView("timeCard");
		String employeeId = (String) session.getAttribute("user");
		String status = attendanceService.timeCardRecord(employeeId, daytime,action);
		mav.addObject("error", status);
		return mav;
	}
}