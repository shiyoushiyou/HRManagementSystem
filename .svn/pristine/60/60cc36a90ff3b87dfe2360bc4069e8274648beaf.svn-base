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
	List<Employees> getEmployeesByUserid(String loginId);//驗證用戶帳密

	String getPwdByPwd(String password);//驗證舊密碼

	void changePwd(String pwd, String newPassword);//便更新密碼

	List<Employees> selectEmp(Search search);//取得員工基礎訊息

	void setData(Employees employee);//更新員工資料

	String getPositionIdByName(String position);//獲取職位ID

	String getDepartmentIdByName(String department);//獲取部門ID

	List<SalaryRules>getEmployeeIdBySalaryRules(String salaryRules);//獲取薪資規則ID

	public List<Employees> getEmployeeByLoginId(String loginId);

	void deleteById(String id);//刪除員工資料

	public List<Employees> selectEmps(String departmentId);

	public List<Attendance> getAttendenceByCondition(Condition condition);

	void insertEmp(Employees employee);//新增員工

	int getLastId();//獲取最新員工的id

	public String getPositionIdById(String loginId);
	public void setRulesData(SalaryRules salaryRules);
}

