package com.xmhx.cnlife.buzz.exception;

import com.xmhx.cnlife.core.model.PushMsg;

/**
 * 运行时异常
 * 
 * @author wujin
 * @since 2015-01-01
 */
public class ResponseException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private PushMsg pushMsg;

	public ResponseException() {
		super();
	}

	public ResponseException(String msg, int code) {
		super();
		this.pushMsg = new PushMsg(Boolean.FALSE, msg, code);
	}

	public ResponseException(PushMsg pushMsg) {
		super();
		this.pushMsg = pushMsg;
	}

	public ResponseException(String message, Throwable ex) {
		super(message, ex);
	}

	public ResponseException(String message) {
		super(message);
	}

	public ResponseException(Throwable ex) {
		super(ex);
	}

	public PushMsg getPushMsg() {
		return pushMsg;
	}

	public void setPushMsg(PushMsg pushMsg) {
		this.pushMsg = pushMsg;
	}
}
