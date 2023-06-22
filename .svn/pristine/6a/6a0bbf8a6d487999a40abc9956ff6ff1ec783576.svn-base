package jp.co.hrms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.hrms.mapper.AttendanceMapper;
import jp.co.hrms.model.Attendance;

@Service
public class AttendanceService {
	@Autowired
	private AttendanceMapper attendanceMapper;

	public List<Attendance> attendanceRecord(String employeeId) {
		return attendanceMapper.getAttendanceRecordByEmployeeId(employeeId);
	}

	public void checkIn(Attendance attendance) {
		attendanceMapper.checkIn(attendance);
	}

	public void checkOut(Attendance attendance) {
		attendanceMapper.checkOut(attendance);
	}

	public void restIn(Attendance attendance) {
		attendanceMapper.restIn(attendance);
	}

	public void restOut(Attendance attendance) {
		attendanceMapper.restOut(attendance);
	}

	public Attendance getAttendanceByEmployeeId(String employeeId) {
		return attendanceMapper.getAttendanceByEmployeeId(employeeId);
	}

	public Attendance getLatestAttendanceByEmployeeId(String employeeId) {
		return attendanceMapper.getLatestAttendanceByEmployeeId(employeeId);
	}
}