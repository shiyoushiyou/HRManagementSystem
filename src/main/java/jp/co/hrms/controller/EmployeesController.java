package jp.co.hrms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jp.co.hrms.model.Employees;
import jp.co.hrms.model.SalaryRules;
import jp.co.hrms.model.Search;
import jp.co.hrms.model.SystemSeting;
import jp.co.hrms.service.EmployeesService;
import jp.co.hrms.service.SystemService;



/**
 * 這是社員管理功能相關的類
 * 包含:社員的增刪改查，個人資訊查詢功能，變更員工密碼
 * @author shiyou
 */

@Controller
public class EmployeesController {
	
	@Autowired
	private EmployeesService service;
	@Autowired
	private SystemService sysService;

	
	/**
	 * 此方法會從session中獲取登入ID，
	 * 查詢當前登入頁面之員工訊息
	 * 
	 * @param HttpSession session
     * @return Employees類，並將資訊加載到前端
	 *
	 */
	@RequestMapping("/empInfo")
	public ModelAndView searchLoginEmp(HttpSession session) {
		ModelAndView mav = new ModelAndView("empInfo");
		String employee = (String) session.getAttribute("user");
		Employees EmpsInfo = service.getEmployeesByUserid(employee);
		mav.addObject("EmpsInfo", EmpsInfo);
		return mav;
	}
	
	
	
	/**
	 * 此方法會從session中獲取登入ID，
	 * 查詢當前登入頁面之員工密碼是否符合輸入的密碼
	 * 
	 * @param HttpSession session
     * @return result
	 *
	 */
	@PostMapping("/registerCurrentPwd")
	@ResponseBody
	public String registerCurrentPwd(HttpServletRequest request, HttpSession session) {
		String result = service.registerCurrentPwd
				(request.getParameter("currentPassword"),(String) session.getAttribute("user"));
		return result;
	}
	
	
	
	/**
	 * 此方法會從session中獲取登入ID，
	 * 查詢當前登入頁面之員工資訊，並更改密碼資訊。
	 * 
	 * @param HttpServletRequest request, HttpSession session
     * @return Employees類，並將資訊加載到前端
	 *
	 */
	@PostMapping("/changePwd")
	public ModelAndView changePwd(HttpServletRequest request, HttpSession session) {
		String employee = (String) session.getAttribute("user");
		service.pwdChange(request.getParameter("pwdregister"),employee);
		Employees EmpsInfo = service.getEmployeesByUserid(employee);
		ModelAndView mav = new ModelAndView("empInfo");
		mav.addObject("EmpsInfo", EmpsInfo);
		return mav;
	}
	
	
	
	@GetMapping("/selectEmployees")
	public ModelAndView selectEmployees() {
		//獲取員工基礎訊息
		ModelAndView mav = new ModelAndView("selectEmployees");
		List<SystemSeting> statusList = sysService.getListByCode("status");
		List<SystemSeting> departmentList = sysService.getListByCode("departmentId");
		List<SystemSeting> positionList = sysService.getListByCode("positionId");

		mav.addObject("statusList", statusList);
		mav.addObject("departmentList", departmentList);
		mav.addObject("positionList", positionList);
		return mav;
	}
	
	
	
	@PostMapping("/selectEmployees")
	public ModelAndView select(Search search) {
		//查詢員工基本訊息，包括查詢條件的判斷
		ModelAndView mav = new ModelAndView("selectEmployees");
		List<Employees> Emp = service.selectEmp(search);
		List<SystemSeting> statusList = sysService.getListByCode("status");
		List<SystemSeting> departmentList = sysService.getListByCode("departmentId");
		List<SystemSeting> positionList = sysService.getListByCode("positionId");
		mav.addObject("statusList", statusList);
		mav.addObject("Emp", Emp);
		mav.addObject("search", search);
		mav.addObject("departmentList", departmentList);
		mav.addObject("positionList", positionList);
		System.out.println(Emp);
		System.out.println(search);
		return mav;
	}
	
	
	
	@GetMapping("/detail")
	public ModelAndView detail(String id, HttpSession session) {
		//顯示各員工詳細
		ModelAndView mav = new ModelAndView("detail");
		String loginId = (String) session.getAttribute("user");
		Employees EmpsInfo = service.getEmployeesByUserid(id);
		String pId = service.getPositionIdById(loginId);
		List<SalaryRules> salaryRules = service.getEmployeeIdBySalaryRules(id);
		mav.addObject("salaryRules", salaryRules);

		List<SystemSeting> departmentList = sysService.getListByCode("departmentId");
		//showButton是用來顯示detail的變更跟刪除鈕用的，只在第一次點擊detail時出現，
		//變更完後的跳轉不會再次顯示變更刪除鈕
		boolean showButton = true;
		mav.addObject("showButton", showButton);
		mav.addObject("EmpsInfo", EmpsInfo);
		mav.addObject("departmentList", departmentList);
		mav.addObject("pId", pId);

		return mav;
	}
	
	
	
	@PostMapping("/detailChange")
	public ModelAndView detailChange(Employees employee) {
		//變更員工資訊
		ModelAndView mav = new ModelAndView("detail");
		System.out.println(employee);
		service.setData(employee);
		String loginId = employee.getId();
		Employees EmpsInfo = service.getEmployeesByUserid(loginId);
		//showButton2是用來顯示detail的p標籤用的，當資料更改完成時會顯示
		boolean showButton2 = true;
		mav.addObject("showButton2", showButton2);
		mav.addObject("EmpsInfo", EmpsInfo);

		return mav;
	}
	
	
	
	@PostMapping("/detailDelete")
	public ModelAndView detailDelete(String id) {
		//刪除員工
		ModelAndView mav = new ModelAndView("detail");
		service.deleteById(id);
		boolean delete = true;
		mav.addObject("delete", delete);
		return mav;
	}
	
	
	
	@GetMapping("/insertEmp")
	public ModelAndView insertEmp() {
		//新增員工頁面跳轉
		ModelAndView mav = new ModelAndView("insertEmp");
		List<SystemSeting> departmentList = sysService.getListByCode("departmentId");
		List<SystemSeting> positionList = sysService.getListByCode("positionId");
		mav.addObject("departmentList", departmentList);
		mav.addObject("positionList", positionList);
		return mav;
	}
	
	
	
	@PostMapping("/insertEmp")
	public ModelAndView insert(Employees employee) {
		//提交新增員工資訊
		ModelAndView mav = new ModelAndView("insertEmp");
		boolean insertEmp = true;
		mav.addObject("insertEmp", insertEmp);
		String id = service.insertEmp(employee);
		Employees empInsert = service.getEmployeesByUserid(id);
		mav.addObject("empInsert", empInsert);
		return mav;
	}
	
	
	
	@PostMapping("/changeSalaryRules")
	public ModelAndView changeSalaryRules(SalaryRules salaryRules) {
		ModelAndView mav = new ModelAndView("detail");
		service.setRulesData(salaryRules);
		System.out.println(salaryRules);
		return mav;
	}
}
