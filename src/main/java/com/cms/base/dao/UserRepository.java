package com.cms.base.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cms.base.model.User;

/**
 * @author 高鹤
 * 
 * @2017年8月13日
 *
 * 作用:
 *
 */
public interface UserRepository  extends JpaRepository<User, Long>{
  
 

}
