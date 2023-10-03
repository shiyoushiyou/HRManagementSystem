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

	public Employees getEmployeesByUserid(String loginId) {
		//抓取員工個人資訊
		return mapper.getEmployeesByUserid(loginId);
	}
	
	//驗證當前密碼
	public String registerCurrentPwd(String password, String loginId) {
		return mapper.registerCurrentPwd(password,loginId)!= null ? "true":"false";
	}

	//變更密碼
	public void pwdChange(String newPassword,String loginId) {
		mapper.changePwd(newPassword,loginId);
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

	public List<Employees> searchEmp(Search search) {
		List<Employees> EMP = mapper.searchEmp(search);
		System.out.println("service = "+EMP);
		return mapper.searchEmp(search);

	}

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
