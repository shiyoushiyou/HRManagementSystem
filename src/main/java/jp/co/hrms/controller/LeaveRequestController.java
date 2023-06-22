package jp.co.hrms.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;
import jp.co.hrms.model.Employees;
import jp.co.hrms.model.LeaveRequest;
import jp.co.hrms.model.LeaveType;
import jp.co.hrms.service.LeaveRequestService;
import jp.co.hrms.service.LeaveTypeService;

@Controller
public class LeaveRequestController {
    @Autowired
    private LeaveRequestService leaveRequestService;
    
    @Autowired
    private LeaveTypeService leaveTypeService;
    
    @GetMapping("/request")
    public ModelAndView request(HttpSession session) {
        ModelAndView mav = new ModelAndView("request");

        // 获取登录用户的 employee 对象
        String loginId = (String) session.getAttribute("user");
        Employees employee = leaveRequestService.getEmployeeByLoginId(loginId);

        // 将 employee 对象添加到mav
        mav.addObject("employee", employee);
       
     // 获取员工当月的休假申请记录
        int employeeId = Integer.parseInt(employee.getId());
        int year = LocalDate.now().getYear();
        int month = LocalDate.now().getMonthValue();
        List<LeaveRequest> employeeLeaveRequests = leaveRequestService.getEmployeeLeaveRequestsByMonth(employeeId, year, month);
        mav.addObject("employeeLeaveRequests", employeeLeaveRequests);

        mav.addObject("leaveRequest", new LeaveRequest());
        return mav;
    }

    @PostMapping("/request")
    public ModelAndView submitRequest(LeaveRequest leaveRequest, HttpSession session) {
        String loginId = (String) session.getAttribute("user");
        Employees employee = leaveRequestService.getEmployeeByLoginId(loginId);
        int positionId = Integer.parseInt(employee.getPositionId());
        System.out.println("Position ID: " + positionId);  // 添加这一行进行调试
       
        int id = Integer.parseInt(employee.getId());
        leaveRequest.setEmployeeId(id); // 保存员工ID

        if (positionId ==3) {
            leaveRequest.setStatus("未審査");
        } else if (positionId == 2) {
            leaveRequest.setStatus("部長承認済み");
        } else {
            leaveRequest.setStatus("社長承認済み");
        }

        leaveRequestService.addLeaveRequest(leaveRequest);
        ModelAndView mav = new ModelAndView("request");
        mav.addObject("msg", "申请成功！请等待审核。");
        mav.addObject("leaveRequest", new LeaveRequest());
      
        // 更新员工当月的休假申请记录
        int year = LocalDate.now().getYear();
        int month = LocalDate.now().getMonthValue();
        List<LeaveRequest> employeeLeaveRequests = leaveRequestService.getEmployeeLeaveRequestsByMonth(id, year, month);
        mav.addObject("employeeLeaveRequests", employeeLeaveRequests);
      
        return mav;
    }
    
    @GetMapping("/management")
    public ModelAndView management(HttpSession session) {
        ModelAndView mav = new ModelAndView("management");

        List<LeaveRequest> allLeaveRequests = leaveRequestService.getAllLeaveRequests();
        List<LeaveType> leaveTypes = leaveTypeService.getAllLeaveTypes();

        String loginId = (String) session.getAttribute("user");
        Employees employee = leaveRequestService.getEmployeeByLoginId(loginId);
        System.out.println("Employee: " + employee);

        int positionId = Integer.parseInt(employee.getPositionId());
        int departmentId = Integer.parseInt(employee.getDepartmentId());
        int id = Integer.parseInt(employee.getId());
        // 根据用户角色显示请假请求
        List<LeaveRequest> leaveRequests = new ArrayList<>();
        for (LeaveRequest leaveRequest : allLeaveRequests) {
            boolean showpage = false;

            // 判断当前用户是否部长,显示批准和拒绝按钮
            
            if ((positionId == 2 || positionId == 1) && leaveRequest.getStatus().equals("未審査")) {
                if (departmentId == leaveRequest.getDepartmentId() && leaveRequest.getEmployeeId() != id) {
                    showpage = true;
                
                } else {
                
                    System.out.println("Employee department: " + departmentId);
                    System.out.println("LeaveRequest department: " + leaveRequest.getDepartmentId());
                    System.out.println("Employee ID: " + employee.getId());
                    System.out.println("LeaveRequest employee ID: " + leaveRequest.getEmployeeId());
                }
            }

            // 社长可以看到所有请求
            if (positionId == 1) { 
                showpage = true;
            }
            
            System.out.println("leaveRequest: " + leaveRequest);
            System.out.println("showpage: " + showpage);

            if (showpage && !leaveRequest.getStatus().equals("社長承認済み") && !leaveRequest.getStatus().equals("拒否された")) {
                leaveRequests.add(leaveRequest);
            }
        }

        // 添加新变量 approvedLeaveRequests
        int year = LocalDate.now().getYear();
        int month = LocalDate.now().getMonthValue();
        List<LeaveRequest> approvedLeaveRequests = leaveRequestService.getApprovedLeaveRequestsByMonth(year, month);
    
        // 过滤一般社员不能查看审批记录
        if (positionId == 3) {
            approvedLeaveRequests.clear();
        }

        mav.addObject("leaveRequests", leaveRequests);
        mav.addObject("leaveTypes", leaveTypes);
        mav.addObject("positionId", positionId);
        mav.addObject("approvedLeaveRequests", approvedLeaveRequests); 

        return mav;
    }
    
    @PostMapping("/management")
    public ModelAndView managementPost(HttpSession session) {
        return management(session);
    }
  
    @GetMapping("/updateStatus")
    public ModelAndView updateStatus(int id, String status) {
        leaveRequestService.updateLeaveRequestStatus(id, status);
        ModelAndView mav = new ModelAndView("management");
        List<LeaveRequest> leaveRequests = leaveRequestService.getAllLeaveRequests();
        mav.addObject("leaveRequests", leaveRequests);
        return mav;
    }
    
    @PostMapping("/updateStatus")
    public ModelAndView updateStatus(int leaveId, String status, HttpSession session) {
        leaveRequestService.updateLeaveRequestStatus(leaveId, status);
        return management(session);
    }   
}