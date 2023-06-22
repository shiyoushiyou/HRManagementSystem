package jp.co.hrms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.hrms.mapper.SystemMapper;
import jp.co.hrms.model.SystemSeting;

@Service
public class SystemService {
	@Autowired
	private SystemMapper mapper;
	public List<SystemSeting> getListByCode(String code){
		
		return mapper.getListByCode(code);
	}
	
}
