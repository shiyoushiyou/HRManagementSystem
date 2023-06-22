package jp.co.hrms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import jp.co.hrms.model.Salary;

@Mapper
public interface SalaryMapper {
	List<Salary> selectSalary(String month, String number);

	@Select("SELECT ID FROM employees")
	List<String> getAllEmployeeIds();

	void generateSalary(String id);

	default void executeSalaryGeneration() {
		// 查询所有员工ID
		List<String> ids = getAllEmployeeIds();
		// 循环遍历员工ID，并执行插入操作
		ids.forEach(this::generateSalary);
	}

}
