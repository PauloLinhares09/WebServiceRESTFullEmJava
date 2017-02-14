package br.com.packapps.util;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Response {
	private int code;
	private String message;
	private Object data;

	public Response() {
	}

	public static Response Ok(String message, int code, Object object) {
		Response r = new Response();
		r.setCode(code);
		r.setMessage(message);
		r.setData(object);
		
		return r;
	}

	public static Response Error(String message, int code) {
		Response r = new Response();
		r.setCode(code);
		r.setMessage(message);
		return r;
	}

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

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	
	
	
}
