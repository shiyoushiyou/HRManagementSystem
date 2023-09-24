package jp.co.hrms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;
import jp.co.hrms.model.Employees;
import jp.co.hrms.model.User;
import jp.co.hrms.service.EmployeesService;
import jp.co.hrms.service.LoginService;

@Controller
public class LoginController {
	@Autowired
	private LoginService Loginservice;
	@Autowired
	private EmployeesService empService;

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@PostMapping("/login")
	public ModelAndView login(User user, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		System.out.println(user);
		String msg = Loginservice.loginCheck(user);
		if ("success".equals(msg)) {
			List<Employees> EmpInfo =  empService.getEmployeesByUserid(user.getLoginId());
			
			session.setAttribute("user", user.getLoginId());
			session.setAttribute("empInfo", EmpInfo);
			session.setAttribute("positionId", user.getPositionId());
			
			mav.addObject("EmpInfo", EmpInfo);
			mav.addObject("positionId", user.getPositionId());
			mav.setViewName("index");
		} else {
			session.removeAttribute("userid");
			// ２）エラーメッセージを表示しつつ自画面遷移
			// リクエストスコープ
			mav.addObject("error", msg);
			mav.setViewName("login");
		}
		return mav;
	}

	@GetMapping("/index")
	public ModelAndView index(HttpSession session) {
	    ModelAndView mav = new ModelAndView();

	    // 获取positionId
	    String positionId = (String) session.getAttribute("positionId");

	    // 将positionId添加到ModelAndView对象中
	    mav.addObject("positionId", positionId);

	    mav.setViewName("index");
	    return mav;
	}


	@GetMapping("/index2")
	public String index2() {
		return "index2";
	}

	@GetMapping("/logout")
	public ModelAndView logout(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		// 清除session中的用户信息
		session.removeAttribute("user");
		// 跳转到登录页面
		mav.setViewName("login");
		return mav;
	}
}
