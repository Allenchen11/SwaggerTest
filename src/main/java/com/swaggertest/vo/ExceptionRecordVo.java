package com.swaggertest.vo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "exception_record")
public class ExceptionRecordVo {

	@Id
	@Column(name="exception_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer exceptionId;
	
	@Column(name="session_id", length=1000)
    private String sessionId;
    
	@Column(name="stack_trace", length=8000)
    private String stackTrace;
 
	@Column(name="date")
    private Date date;

	public Integer getExceptionId() {
		return exceptionId;
	}

	public void setExceptionId(Integer exceptionId) {
		this.exceptionId = exceptionId;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getStackTrace() {
		return stackTrace;
	}

	public void setStackTrace(String stackTrace) {
		this.stackTrace = stackTrace;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
    
	
}
