package com.elcom.service;

import java.util.List;

import javax.ws.rs.core.HttpHeaders;

import com.elcom.model.enums.AuthorizationToken;
import com.elcom.model.object.AuthorizationTokenResult;
import com.elcom.sharedbiz.validation.AuthorizationException;
import com.elcom.util.StringUtils;

public class BaseAuthorization {

    protected HttpHeaders headers = null;
    private String AUTHORIZATION = "Authorization";
    private String AUTHORIZATION_IDENTITY = "vi-identity";
    private String AUTHORIZATION_CHEME_BEARER = "Bearer ";
    private String AUTHORIZATION_CHEME_BASIC = "Basic ";
    private String AUTHORIZATION_CHEME_OTT = "Ott ";
    protected String exMes = "Unauthorized.";

    /*public BaseAuthorization() {
	}*/
    public BaseAuthorization(HttpHeaders headers) {
        this.headers = headers;
    }

    public AuthorizationTokenResult processHeader() throws Exception {

        AuthorizationTokenResult result = null;

        List<String> tempHeaders = headers.getRequestHeader(AUTHORIZATION);
        if (tempHeaders == null || tempHeaders.isEmpty()) {
            throw new AuthorizationException(exMes);
        }

        /* Identity (UUID) */
        List<String> identityHeader = headers.getRequestHeader(AUTHORIZATION_IDENTITY);
        String identityValue = "";
        if (identityHeader != null && !identityHeader.isEmpty()) {
            identityValue = identityHeader.get(0);
        }
        if (StringUtils.isNullOrEmpty(identityValue)) {
            throw new AuthorizationException(exMes);
        }

        String auth = tempHeaders.get(0);
        if (StringUtils.isNullOrEmpty(auth)) {
            throw new AuthorizationException(exMes);
        }

        if (!auth.startsWith(AUTHORIZATION_CHEME_BEARER) && !auth.startsWith(AUTHORIZATION_CHEME_BASIC) && !auth.startsWith(AUTHORIZATION_CHEME_OTT)) {
            throw new AuthorizationException(exMes);
        }

        String token = null;
        if (auth.startsWith(AUTHORIZATION_CHEME_BEARER)) {

            token = auth.replaceFirst(AUTHORIZATION_CHEME_BEARER, StringUtils.Empty);

            result = new AuthorizationTokenResult(
                    AuthorizationToken.BEARER,
                     token,
                     identityValue
            );
        }

        if (auth.startsWith(AUTHORIZATION_CHEME_BASIC)) {
            token = auth.replaceFirst(AUTHORIZATION_CHEME_BASIC, StringUtils.Empty);
            result = new AuthorizationTokenResult(AuthorizationToken.BASIC, token);
        }

        if (auth.startsWith(AUTHORIZATION_CHEME_OTT)) {
            token = auth.replaceFirst(AUTHORIZATION_CHEME_OTT, StringUtils.Empty);
            result = new AuthorizationTokenResult(AuthorizationToken.OTT, token);
        }

        if (StringUtils.isNullOrEmpty(token)) {
            throw new AuthorizationException(exMes);
        }

        return result;
    }
}
