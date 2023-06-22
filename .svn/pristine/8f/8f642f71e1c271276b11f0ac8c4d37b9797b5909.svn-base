package jp.co.hrms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.hrms.mapper.LeaveRequestMapper;
import jp.co.hrms.model.Employees;
import jp.co.hrms.model.LeaveRequest;
import jp.co.hrms.model.LeaveType;

@Service
public class LeaveRequestService {
    @Autowired
    private LeaveRequestMapper leaveRequestMapper;
    
    @Autowired
    private LeaveTypeService leaveTypeService; 
    
    public List<LeaveRequest> getAllLeaveRequests() {
        return leaveRequestMapper.getAllLeaveRequests();
    }

    public LeaveRequest getLeaveRequestById(int id) {
        return leaveRequestMapper.getLeaveRequestById(id);
    }

    public void addLeaveRequest(LeaveRequest leaveRequest) {
        leaveRequestMapper.addLeaveRequest(leaveRequest);
        
    }
    
    public List<LeaveRequest> getLeaveRequestsByEmployeeId(int employeeId) {
        return leaveRequestMapper.getLeaveRequestsByEmployeeId(employeeId);
    }

    public List<LeaveRequest> getLeaveRequestsByPositionId(int positionId) {
        return leaveRequestMapper.getLeaveRequestsByPositionId(positionId);
    }
    
    public List<LeaveRequest> getEmployeeLeaveRequestsByMonth(int employeeId, int year, int month) {
        List<LeaveRequest> leaveRequests = leaveRequestMapper.getEmployeeLeaveRequestsByMonth(employeeId, year, month);
        List<LeaveType> leaveTypes = leaveTypeService.getAllLeaveTypes();

        for (LeaveRequest leaveRequest : leaveRequests) {
            leaveRequest.setLeaveTypeName(leaveTypes.get(leaveRequest.getLeaveTypeId() - 1).getName());
        }

        return leaveRequests;
    }
    public void updateLeaveRequestStatus(int id, String status) {
        leaveRequestMapper.updateLeaveRequestStatus(id, status);
    }

    public List<LeaveRequest> getLeaveRequestsByStatus(String status) {
        return leaveRequestMapper.getLeaveRequestsByStatus(status);
    }

    public Employees getEmployeeByLoginId(String loginId) {
    	return leaveRequestMapper.getEmployeeByLoginId(loginId);
    }
    
    public List<LeaveRequest> getApprovedLeaveRequestsByMonth(int year, int month) {
        List<LeaveRequest> approvedLeaveRequests = leaveRequestMapper.getApprovedLeaveRequestsByMonth(year, month);
        List<LeaveType> leaveTypes = leaveTypeService.getAllLeaveTypes();

        for (LeaveRequest leaveRequest : approvedLeaveRequests) {
            leaveRequest.setLeaveTypeName(leaveTypes.get(leaveRequest.getLeaveTypeId() - 1).getName());
        }
        return approvedLeaveRequests;
    }
}