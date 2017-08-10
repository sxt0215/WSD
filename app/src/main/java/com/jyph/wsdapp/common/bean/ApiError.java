package com.jyph.wsdapp.common.bean;

/**
 * 返回错误信息
 * @author mengxc
 *
 */
public class ApiError {

    /**
     * 错误消息码
     */
	public String message;
	
	/**
	 * 错误字段
	 */
	public String type;
	
	/**
	 * 错误字段值
	 */
	public String value;
	
	public int code;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
	
	
}
