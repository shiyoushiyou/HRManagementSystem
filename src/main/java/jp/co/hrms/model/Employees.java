package jp.co.hrms.model;

import lombok.Data;

@Data
public class Employees {
	private String id;//社員編號
	private String name;//姓名
	private String gender;//性別
	private String birth;//生年月日
	private String address;//地址
	private String phoneNumber;//連絡電話
	private String status;//在職狀態
	private String departmentId;//部門ID
	private String departmentName;//部門
	private String positionId;//職位ID
	private String positionName;//職位
	private String hireDate;//入社日
	private String password;

}
