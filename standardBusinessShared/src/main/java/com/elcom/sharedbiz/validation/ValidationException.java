package com.elcom.sharedbiz.validation;

import org.apache.http.HttpStatus;

public class ValidationException extends Exception {

    private static final long serialVersionUID = 176023227975132626L;

    private int httpStatusCode = HttpStatus.SC_BAD_REQUEST;
    private String code;

    //private String status;
    //private String reason;
    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(String code, String message) {
        super(message);
        this.code = code;
    }

    public ValidationException(int httpStatusCode, String message) {
        super(message);
        this.httpStatusCode = httpStatusCode;
    }

    /*public ValidationException(String status, String reason, String dateTime) {
		this.status = status;
		this.reason = reason;
	}*/
    public int getHttpStatusCode() {
        return httpStatusCode;
    }

    public String getCode() {
        return code;
    }

    /*public String getStatus() {
		return status;
	}

	public String getReason() {
		return reason;
	}*/
}
