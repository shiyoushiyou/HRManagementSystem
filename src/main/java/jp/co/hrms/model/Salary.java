package jp.co.hrms.model;
import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.Data;
@Data
public class Salary {
    //工资编号
    private String id;
    //员工编号
    private String employeeId;
    // 月份
    private String month;
    //工资结算日期
    private Date salaryDate;
    //基本工资
    private String basicSalary;
    //奖金
    private String bonus;
    //津贴
    private String allowance;
    //社保
    private String socialInsurance;
    //公积金
    private String housingFund;
    //个人所得税
    private String tax;
    //总工资
    private String totalSalary;
    
    public String getSalaryDate1() {
		// Date👉SimpleDateFormat👉String：sdf.format()
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		return  sdf.format(salaryDate);
	}

    
}
