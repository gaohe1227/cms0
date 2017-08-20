package com.util;

public class Result<T> {
	@Override
	public String toString() {
		return "Result [state=" + state + ", message=" + message + ", t=" + t
				+ "]";
	}
	private int state;
	private String message;
	private T t;
	public Result() {
		
	}
	public Result(int state, String message, T t) {
		super();
		this.state = state;
		this.message = message;
		this.t = t;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public T getT() {
		return t;
	}
	public void setT(T t) {
		this.t = t;
	}
}
