package com.swaggertest.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.InternalResourceView;

import com.swaggertest.repository.ExceptionRecordRepository;
import com.swaggertest.vo.ExceptionRecordVo;

@RestController
@ControllerAdvice
public class ExceptionController {

	@Autowired
	protected ExceptionRecordRepository exceptionRecordRepository;
	
	@Autowired
	protected HttpSession session;
	
	@Autowired
	protected HttpServletRequest request;

	@ExceptionHandler(Exception.class)
	public ModelAndView handleException(Exception exception) {

		System.out.println("Handle Exception!!!");

		request.setAttribute("exception", exception);

		String forwardUrl = "/getExceptionInfo";
		 
		return new ModelAndView(new InternalResourceView(forwardUrl));
	}

	@GetMapping(value = "/getExceptionInfo", produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> getExceptionInfo() {
		
		Exception exception = (Exception) request.getAttribute("exception");	

		Map<String, Object> map = new HashMap<String, Object>();

		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		exception.printStackTrace(pw);
		String stackTrace = sw.toString();
		
		map.put("status", "error");
		map.put("stackTrace", stackTrace);
		
		ExceptionRecordVo exceptionRecordVo = new ExceptionRecordVo();
		exceptionRecordVo.setDate(new Date());
		exceptionRecordVo.setSessionId(session.getId());
		exceptionRecordVo.setStackTrace(stackTrace);
		exceptionRecordRepository.save(exceptionRecordVo);
		
		return map;
	}

}
