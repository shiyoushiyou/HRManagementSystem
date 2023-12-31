package jp.co.hrms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import jp.co.hrms.model.Attendance;
import jp.co.hrms.model.Condition;
import jp.co.hrms.model.Employees;
import jp.co.hrms.model.SalaryRules;
import jp.co.hrms.model.Search;

@Mapper
public interface EmployeesMapper {
	
	//查詢員工資訊(個人)
	Employees getEmployeesByUserid(String loginId);
	
	//驗證舊密碼
	String registerCurrentPwd(String password, String loginId);
	
	//變更密碼
	void changePwd(String newPassword,String loginId);

	//取得員工基礎訊息
	List<Employees> searchEmp(Search search);

	//刪除員工資料
	void deleteById(String id);
	
	//更新員工資料
	void updateEmpinfo(Employees employee);

	String getPositionIdByName(String position);//獲取職位ID

	String getDepartmentIdByName(String department);//獲取部門ID

	List<SalaryRules>getEmployeeIdBySalaryRules(String salaryRules);//獲取薪資規則ID

	
	public List<Employees> getEmployeeByLoginId(String loginId);

	public List<Employees> selectEmps(String departmentId);

	public List<Attendance> getAttendenceByCondition(Condition condition);

	void insertEmp(Employees employee);//新增員工

	int getLastId();//獲取最新員工的id

	public String getPositionIdById(String loginId);
	
	public void setRulesData(SalaryRules salaryRules);

}

