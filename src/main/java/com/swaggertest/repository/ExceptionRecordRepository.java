package com.swaggertest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.swaggertest.vo.ExceptionRecordVo;

public interface ExceptionRecordRepository extends JpaRepository<ExceptionRecordVo, Integer>{

}
