package com.elcom.sharedbiz.validation;

public class InternalException extends Exception {

    private static final long serialVersionUID = 176023227975132626L;
    private int HttpStatusCode = 400;
    private String code;

    public InternalException(String message) {
        super(message);
    }

    public InternalException(String code, String message) {
        super(message);
        this.code = code;
    }

    public int getHttpStatusCode() {
        return HttpStatusCode;
    }

    public String getCode() {
        return code;
    }

}
