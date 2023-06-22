package jp.co.hrms.model;

import lombok.Data;
@Data
public class attendanceManagement {
	private String id;
	private String employeeId;
	private String date;
	private String checkIn;
	private String checkOut;
	private String restIn;
	private String restOut;
	private String status;

}
