package jp.co.hrms.model;

import lombok.Data;

@Data
public class SalaryRules {
    //工资规则编号
    private String id;
    //员工编号
    private int employeeId;
    //工资规则名称
    private String ruleName;
    //基本工资
    private String baseSalary;
    //奖金比例
    private String bonusRate;
    //津贴比例
    private String allowanceRate;
    //社保比例
    private String socailInsuranceRate;
    //公积金比例   
    private String housingFundRate;
    //个人所得税比例
    private String taxRate;

    
}
