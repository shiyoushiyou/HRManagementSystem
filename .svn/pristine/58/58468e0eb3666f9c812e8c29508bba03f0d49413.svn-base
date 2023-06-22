package jp.co.hrms.mapper;



import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import jp.co.hrms.model.LeaveType;

@Mapper
public interface LeaveTypeMapper {

    @Select("SELECT * FROM leave_types")
    List<LeaveType> getAllLeaveTypes();
}
