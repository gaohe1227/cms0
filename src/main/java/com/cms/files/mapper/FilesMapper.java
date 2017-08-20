package com.cms.files.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.cms.base.model.User;
import com.cms.files.model.Files;

public interface  FilesMapper {
 
	 @Update("update t_cms_user set status=1   where id =#{id}")
	Integer deleteById(Long id);
 
	@Select("select * from t_cms_files")
	List<Files> findAll();
	@Select("select * from t_cms_files where name like #{filesName} or origname like #{filesName}")
	User findUser(User user);
	List<Files> findAllByFilesName(String filesName); 
	  
	 
}
