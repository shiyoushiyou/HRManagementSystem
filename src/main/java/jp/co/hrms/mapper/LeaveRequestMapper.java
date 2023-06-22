package jp.co.hrms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import jp.co.hrms.model.Employees;
import jp.co.hrms.model.LeaveRequest;

@Mapper
public interface LeaveRequestMapper {
	List<LeaveRequest> getAllLeaveRequests();

	LeaveRequest getLeaveRequestById(int id);

	void addLeaveRequest(LeaveRequest leaveRequest);

	void updateLeaveRequestStatus(@Param("id") int id, @Param("status") String status);

	List<LeaveRequest> getLeaveRequestsByStatus(String status);

	List<LeaveRequest> getLeaveRequestsByEmployeeId(int employeeId);

	List<LeaveRequest> getLeaveRequestsByPositionId(int positionId);

	public Employees getEmployeeByLoginId(String loginId);

	List<LeaveRequest> getEmployeeLeaveRequestsByMonth(@Param("employeeId") int employeeId, @Param("year") int year,
			@Param("month") int month);

	List<LeaveRequest> getApprovedLeaveRequestsByMonth(@Param("year") int year, @Param("month") int month);

}
