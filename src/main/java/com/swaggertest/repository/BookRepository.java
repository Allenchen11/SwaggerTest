package com.swaggertest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.swaggertest.vo.BookVo;

public interface BookRepository extends JpaRepository<BookVo, Integer>{

}
