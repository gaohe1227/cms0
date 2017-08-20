package com.cms.base.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cms.base.model.CmsLog;

public interface CmsLogRepository extends JpaRepository<CmsLog, Long>{

}
