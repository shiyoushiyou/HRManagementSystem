package jp.co.hrms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.hrms.mapper.EmployeesMapper;
import jp.co.hrms.model.Attendance;
import jp.co.hrms.model.Condition;
import jp.co.hrms.model.Employees;
import jp.co.hrms.model.SalaryRules;
import jp.co.hrms.model.Search;

@Service
public class EmployeesService {
	@Autowired
	private EmployeesMapper mapper;

	public List<Employees> getEmployeesByUserid(String loginId) {
		//抓取員工個人資訊
		return mapper.getEmployeesByUserid(loginId);

	}

	public boolean pwdChange(String pwd, String newPassword) {
		//變更密碼時的驗證及密碼UPDATE操作
		String password = mapper.getPwdByPwd(pwd);
		if (password.equals(pwd)) {
			mapper.changePwd(pwd, newPassword);
			return true;
		} else {
			return false;
		}
	}

	public void deleteById(String id) {
		mapper.deleteById(id);
	}

	public String insertEmp(Employees employee) {
		employee.setPositionId(mapper.getPositionIdByName("一般員工"));
		employee.setStatus("在籍中");
		System.out.println("service的Emp" + employee);
		mapper.insertEmp(employee);
		String id = String.valueOf(mapper.getLastId());
		return id;
	}

	public String getPositionIdById(String loginId) {
		return mapper.getPositionIdById(loginId);
	}

	public List<Employees> selectEmp(Search search) {
		List<Employees> EMP = mapper.selectEmp(search);
		System.out.println(EMP);
		return mapper.selectEmp(search);

	}

	//	public List<Employees> getEmployeeByLoginId(String loginId) {
	//
	//		return mapper.getEmployeeByLoginId(loginId);
	//	}

	public void setData(Employees employee) {
		String department = employee.getDepartmentName();
		String position = employee.getPositionName();
		employee.setPositionId(mapper.getPositionIdByName(position));
		employee.setDepartmentId(mapper.getDepartmentIdByName(department));

		System.out.println("修改数据：" + employee);
		mapper.setData(employee);

	}

	public List<Employees> selectEmps(String departmentId) {
		List<Employees> Emp = mapper.selectEmps(departmentId);
		return Emp;
	}

	public List<Attendance> getAttendenceByCondition(Condition condition) {
		List<Attendance> result =mapper.getAttendenceByCondition(condition);
		return result ;
	}

	
	public void setRulesData(SalaryRules salaryRules) {

		mapper.setRulesData(salaryRules);
	}
	public List<SalaryRules> getEmployeeIdBySalaryRules(String id) {

		return mapper.getEmployeeIdBySalaryRules(id);
	}
}
