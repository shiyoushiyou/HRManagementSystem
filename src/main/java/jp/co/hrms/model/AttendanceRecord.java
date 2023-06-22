package jp.co.hrms.model;

import java.util.Date;

import lombok.Data;

@Data
//打卡记录
public class AttendanceRecord {

	private Date checkInTime;
	private Date checkOutTime;
	private Date restInTime;
	private Date restOutTime;
	private int workTime;

}
