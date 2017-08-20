package com.cms.base.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.cms.base.model.Role;

/**
 * @author 高鹤
 * 
 * @2017年8月13日
 *
 * 作用:
 *
 */
 
public interface RoleRepository extends JpaRepository<Role, Integer>{
    @Query(value="select id,roleName,status from t_cms_role where  id=?1",nativeQuery = true)
	List<Role> findAll(String roleName);
  

}
