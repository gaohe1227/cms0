package com.cms.base.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.cms.base.model.CmsLog;

public interface CmsLogMapper {
	 @Select("select * from t_cms_log")
	  List<CmsLog> lists();

}
