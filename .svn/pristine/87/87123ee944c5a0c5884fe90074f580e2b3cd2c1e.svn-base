package jp.co.hrms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;
import jp.co.hrms.model.Salary;
import jp.co.hrms.service.SalaryService;

@Controller
public class SalaryController {
	@Autowired
	private SalaryService service;

	@GetMapping("/salary")
	public String salary() {
		return "salary";
	}

	@PostMapping("/getMonthData")
	@ResponseBody
	public List<Salary> selectSalary(String month, HttpSession session) {

		String number = (String) session.getAttribute("user");
		System.out.println(month);

		List<Salary> list = service.selectSalary(month, number);
		System.out.println(number);

		return list;
	}
}