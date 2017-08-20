package com.cms.files.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cms.files.model.Files;

public interface FilesRespository extends JpaRepository<Files, Long>{

}
