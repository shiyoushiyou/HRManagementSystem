package jp.co.hrms.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.Data;

@Data
public class Attendance {
	private String id;
	private String employeeId;
	private Date date;
	private Date checkIn;
	private Date checkOut;
	private Date restIn;
	private Date restOut;
	private String status;

	public String getDate1() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		return dateFormat.format(date);
	}
}
