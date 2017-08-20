// Decompiled by DJ v3.7.7.81 Copyright 2004 Atanas Neshkov  Date: 2010-11-23 16:54:54
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   DbAccessException.java

package com.common;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;

public class DbAccessException extends RuntimeException {

	public DbAccessException(Throwable e) {
		larvaException = null;
		larvaMsg = "->IF";
		larvaException = e;
		e.printStackTrace();
	}

	public DbAccessException(String msg) {
		super(msg);
		larvaException = null;
		larvaMsg = "->IF";
		larvaMsg = msg;
	}

	public String getThrowableStackMsg(Throwable e) {
		if (e == null)
			return "";
		StringWriter stringWriter;
		stringWriter = new StringWriter();
		PrintWriter print = new PrintWriter(stringWriter);
		e.printStackTrace(print);
		return new String(stringWriter.getBuffer());
	}

	public String getMessage() {
		if (larvaException == null)
			return larvaMsg;
		else
			return getThrowableStackMsg(larvaException);
	}

	public String getMessageTitle() {
		return larvaMsg;
	}

	public String getLarvaMsg() {
		return larvaMsg;
	}

	public void setLarvaMsg(String larvaMsg) {
		this.larvaMsg = larvaMsg;
	}

	public int getSQLErrorCode() {
		return (larvaException instanceof SQLException) ? ((SQLException) larvaException)
				.getErrorCode()
				: 0;
	}

	private Throwable larvaException;
	private String larvaMsg;
}