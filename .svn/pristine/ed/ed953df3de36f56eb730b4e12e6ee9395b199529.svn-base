package jp.co.hrms.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.hrms.mapper.LeaveTypeMapper;
import jp.co.hrms.model.LeaveType;

@Service
public class LeaveTypeService {

    @Autowired
    private LeaveTypeMapper leaveTypeMapper;

    public List<LeaveType> getAllLeaveTypes() {
        return leaveTypeMapper.getAllLeaveTypes();
    }
}
