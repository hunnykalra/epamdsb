package com.epam.dsb.util;

public enum StatusCodeEnum {
	
	UNEXPECTED_ERROR("Invalid body format","001"),	
	ORDER_CREATED("Order Created Successfully","002");
	
	private String message;
	private String code;
	
	private StatusCodeEnum(String message,String code) {
		this.code=code;
		this.message=message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	
}
