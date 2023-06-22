package jp.co.hrms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import jp.co.hrms.model.Attendance;

@Mapper
public interface AttendanceMapper {
	List<Attendance> getAttendanceRecordByEmployeeId(String employeeId);

	void checkIn(Attendance attendance);

	void checkOut(Attendance attendance);

	void restIn(Attendance attendance);

	void restOut(Attendance attendance);

	Attendance getAttendanceByEmployeeId(String employeeId);

	Attendance getLatestAttendanceByEmployeeId(String employeeId);
}
