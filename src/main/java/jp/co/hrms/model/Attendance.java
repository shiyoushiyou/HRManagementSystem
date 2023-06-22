package jp.co.hrms.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import lombok.Data;

@Data
public class Attendance {
	private String id;
	private String employeeId;
	private LocalDateTime date;
	private LocalDateTime checkIn;
	private LocalDateTime checkOut;
	private LocalDateTime restIn;
	private LocalDateTime restOut;
	private String status;

	private List<AttendanceRecord> records;

	public String getDate1() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		return this.date != null ? this.date.format(formatter) : "";
	}

	public String getCheckIn1() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
		return this.checkIn != null ? this.checkIn.format(formatter) : "";
	}

	public String getCheckOut1() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
		return this.checkOut != null ? this.checkOut.format(formatter) : "";
	}

	public String getRestIn1() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
		return this.restIn != null ? this.restIn.format(formatter) : "";
	}

	public String getRestOut1() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
		return this.restOut != null ? this.restOut.format(formatter) : "";
	}

}
