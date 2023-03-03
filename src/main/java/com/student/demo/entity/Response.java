package com.student.demo.entity;

public class Response {
	public Response(int code, String message) {
		super();
		this.code = code;
		this.message = message;
	}
	public Response() {
		super();
		// TODO Auto-generated constructor stub
	}
	private int code;
	private String message;
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}
