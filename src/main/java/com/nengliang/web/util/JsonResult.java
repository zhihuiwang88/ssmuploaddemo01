package com.nengliang.web.util;

public class JsonResult {

	/**
	 * 对结果进行处理
	 * 
	 * @author Dell
	 * 
	 * @Param code:状态码，msg：提示信息，count：数据表总数，data：数据结果
	 */
	private Integer code;
	private String msg;
	private Integer count;
	private Object data;
	 private String url;
	 private String width;
	 private String height;


	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "JsonResult [code=" + code + ", msg=" + msg + ", count=" + count + ", data=" + data + ", url=" + url
				+ ", width=" + width + ", height=" + height + "]";
	}

	
}
