package jp.co.hrms.mapper;

import java.util.Date;

import org.apache.ibatis.annotations.Mapper;

import jp.co.hrms.model.Attendance;

@Mapper
public interface AttendanceMapper {
	//查詢最新一筆打卡資訊
	Attendance getLatestTimeCard(String employeeId ,Date date);
	//新增打卡資訊
	void setTimeCardRecord(Attendance attendance);
}
