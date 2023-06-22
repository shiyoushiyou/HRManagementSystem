package jp.co.hrms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import jp.co.hrms.model.SystemSeting;

@Mapper
public interface SystemMapper {

	List<SystemSeting> getListByCode(String code);

}
