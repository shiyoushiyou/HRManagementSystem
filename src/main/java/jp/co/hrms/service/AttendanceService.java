package jp.co.hrms.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.hrms.mapper.AttendanceMapper;
import jp.co.hrms.model.Attendance;

@Service
public class AttendanceService {
	
	@Autowired
	private AttendanceMapper Mapper;
	
	
	/**
	 * 打卡功能
	 * 
	 * @param String employeeId,dayTime, action
     * @return 將msg資訊加載到前端
	 *
	 */
	public String timeCardRecord(String employeeId, String dayTime, String action) {
		String errorMsg="";
		try {
		Date date = dateFormat(dayTime);
		Date daytime = daytimeFormat(dayTime);
		Attendance latestRecord = Mapper.getLatestTimeCard(employeeId,date);
		//上班操作	
		if (("checkin").equals(action)){
			if(latestRecord==null) {
				Attendance updateRecord = setEmpIdAndDate(employeeId,date);
				updateRecord.setCheckIn(daytime);
				updateRecord.setStatus("出勤");
				Mapper.setTimeCardRecord(updateRecord);
			}else {
				errorMsg = "今日既に出勤しております。二重打刻は出来ません。";
			}
			//下班操作
			}else if(("checkout").equals(action)) {	
				if(latestRecord==null) {
					errorMsg = "まだ出勤してないため、打刻処理不可。";
				}else if(latestRecord!=null&&latestRecord.getCheckIn()!=null
						&&latestRecord.getCheckOut()==null){
					Attendance updateRecord = setEmpIdAndDate(employeeId,date);
					updateRecord.setCheckOut(daytime);
					updateRecord.setStatus("退勤");
					Mapper.setTimeCardRecord(updateRecord);
					errorMsg = "お疲れ様でした。";			
				}else {
					errorMsg ="退勤打刻は既に済みです。";
				}
			}
			else if(("restin").equals(action)) {
				if(latestRecord.getCheckIn()!=null&&latestRecord.getRestIn()==null
					&&latestRecord.getCheckOut()==null) {
					Attendance updateRecord = setEmpIdAndDate(employeeId,date);
					updateRecord.setRestIn(daytime);
					updateRecord.setStatus("休憩開始");
					Mapper.setTimeCardRecord(updateRecord);
					errorMsg ="休憩開始";
				}else if (latestRecord.getCheckOut()!=null){
					errorMsg ="退勤後の休憩処理は不可となります。";
				}else {
					errorMsg ="休憩打刻は既に存在してます。";
				}
			}
			else{
				if(latestRecord.getRestIn()!=null&&latestRecord.getRestOut()==null
						&&latestRecord.getCheckOut()==null) {
					Attendance updateRecord = setEmpIdAndDate(employeeId,date);
					updateRecord.setRestOut(daytime);
					updateRecord.setStatus("休憩終了");
					Mapper.setTimeCardRecord(updateRecord);
					errorMsg ="休憩終了";
				}else if (latestRecord.getCheckOut()!=null){
					errorMsg ="退勤後の休憩処理は不可となります。";
				}else {
					errorMsg ="休憩終了打刻は既に済みです。";
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return errorMsg;
	}
	
	
	/**
	 * 打卡功能
	 * 日期的format以及set員工打卡資訊時處理相同資訊時的方法
	 *
	 */
	public Date dateFormat (String dayTime) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		return dateFormat.parse(dayTime);
	}
	public Date daytimeFormat (String dayTime) throws ParseException {
		SimpleDateFormat dayTimeFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		return dayTimeFormat.parse(dayTime);
	}
	public Attendance setEmpIdAndDate(String employeeId,Date date) {
			Attendance attendance =new Attendance();
			attendance.setDate(date);
			attendance.setEmployeeId(employeeId);
			return attendance;	
	}
	
}

	