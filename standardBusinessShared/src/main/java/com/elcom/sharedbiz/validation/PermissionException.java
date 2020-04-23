package com.elcom.sharedbiz.validation;

public class PermissionException extends Exception {

    private static final long serialVersionUID = 176023227975132626L;
    private int HttpStatusCode = 403;
    private String code;

    public PermissionException(String message) {
        super(message);
    }

    public PermissionException(String code, String message) {
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
