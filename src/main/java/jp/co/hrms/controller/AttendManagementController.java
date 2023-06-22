package jp.co.hrms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import jp.co.hrms.model.Attendance;
import jp.co.hrms.model.Condition;
import jp.co.hrms.model.Employees;
import jp.co.hrms.model.SystemSeting;
import jp.co.hrms.service.EmployeesService;
import jp.co.hrms.service.SystemService;


@Controller
public class AttendManagementController {
	@Autowired
	SystemService sysService;
	@Autowired
	private EmployeesService service;
	
	@GetMapping("/attendanceManagement")
	public ModelAndView emp(String departmentId) {
		ModelAndView mav = new ModelAndView("attendanceManagement");
		List<SystemSeting>departmentList = sysService.getListByCode("departmentId");
		mav.addObject("departmentList",departmentList);
		//要寫return
		return mav;
	}
	@GetMapping("/attendanceAjax")
	@ResponseBody
	public List<Employees> attendanceAjax(String departmentId) {
		List<Employees>Emp =service.selectEmps(departmentId);	

		return Emp;
	}
	
	@PostMapping("/selectEmpAttendence")
	public ModelAndView EmpAttendence(Condition condition){
		ModelAndView mav = new ModelAndView("attendanceManagement");		
		List<Attendance> empAttendence = service.getAttendenceByCondition(condition);
		List<Employees> EmpsInfo =service.getEmployeesByUserid(condition.getId());
		System.out.println(empAttendence);
		mav.addObject("EmpsInfo",EmpsInfo);
	    mav.addObject("empAttendence", empAttendence);
		return mav;
	}
}

