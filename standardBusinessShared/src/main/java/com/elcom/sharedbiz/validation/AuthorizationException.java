package com.elcom.sharedbiz.validation;

public class AuthorizationException extends Exception {

    private static final long serialVersionUID = 176023227975132626L;
    private int HttpStatusCode = 401;
    private String code;

    public AuthorizationException(String message) {
        super(message);
    }

    public AuthorizationException(String code, String message) {
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
