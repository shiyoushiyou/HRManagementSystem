package jp.co.hrms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;
import jp.co.hrms.model.User;
import jp.co.hrms.service.LoginService;

@Controller
public class LoginController {
	@Autowired
	private LoginService service;

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@PostMapping("/login")
	public ModelAndView login(User user, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		System.out.println(user);
		String msg = service.loginCheck(user);
		if ("success".equals(msg)) {
			// 帳密符合時
			// 1.ユーザ情報保持用セッション情報をクリアする
			session.setAttribute("user", user.getLoginId());
			
			session.setAttribute("positionId", user.getPositionId());
			mav.addObject("positionId", user.getPositionId());
			
			// ３）次の画面へ遷移
			// 調用mav對象轉發，這邊成功轉發後畫面會顯示success
			// ViewName相當於轉發功能
			mav.setViewName("index");
		} else {
			// 登錄的帳密不符合時
			// １）ユーザ情報保持用セッション情報をクリアする
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
