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
	public void changePwd(String newPassword,String loginId) {
		mapper.changePwd(newPassword,loginId);
	}
	
	//刪除員工
	public boolean deleteById(String id) {
		Employees emp = mapper.getEmployeesByUserid(id);
		if(emp.getStatus().equals("在籍中") && emp!=null ) {
			mapper.deleteById(id);	
			return true;
		}else{
			return false;
		}
	}

	//依照檢索條件查找符合條件之員工資訊
	public List<Employees> searchEmp(Search search) {
		return mapper.searchEmp(search);
	}

	//更新員工資訊
	public void updateEmpinfo(Employees employee) {
		System.out.println("修改数据：" + employee);
		mapper.updateEmpinfo(employee);
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
