package com.dtyunxi.dtplatform.common;

/**
 * @author 作者 E-mail: tan.jie@dtyunxi.com
 * @version 创建时间：2017年8月11日 下午12:28:02 类说明
 */
public class RestResponse {

	private static final String OK = "ok";
	private static final String ERROR = "error";

	private Meta meta;
	private Object data;

	public RestResponse success() {
		this.meta = new Meta(true, OK);
		return this;
	}

	public RestResponse success(Object data) {
		this.meta = new Meta(true, OK);
		this.data = data;
		return this;
	}

	public RestResponse failure() {
		this.meta = new Meta(false, ERROR);
		return this;
	}

	public RestResponse failure(String message) {
		this.meta = new Meta(false, message);
		return this;
	}

	public Meta getMeta() {
		return meta;
	}

	public Object getData() {
		return data;
	}

	public class Meta {

		private boolean success;
		private String message;

		public Meta(boolean success) {
			this.success = success;
		}

		public Meta(boolean success, String message) {
			this.success = success;
			this.message = message;
		}

		public boolean isSuccess() {
			return success;
		}

		public String getMessage() {
			return message;
		}
	}
}