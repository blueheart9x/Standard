/**
 *
 */
package com.elcom.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.Callable;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.StreamingOutput;

import com.elcom.common.Labels;
import com.elcom.common.Messages;
import com.elcom.data.exception.NoRecordFoundException;
import com.elcom.model.dto.interview.ResponseData;
import com.elcom.model.dto.interview.ResponseDataPaging;
import com.elcom.model.enums.AuthorizationToken;
import com.elcom.model.object.AuthorizationTokenResult;
import com.elcom.sharedbiz.manager.AuthorizationManager;
import com.elcom.sharedbiz.manager.JwTokenHelper;
import com.elcom.sharedbiz.validation.AuthorizationException;
import com.elcom.sharedbiz.validation.ValidationException;
import com.elcom.util.security.SecurityUtil;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;

/**
 * @author anhdv
 *
 */
public class BasedService {

    protected HttpHeaders headers = null;
    protected String sourceIp = null;
    //protected long beginExecutionTime = 0L;
    private BaseAuthorization _authorization = null;
    protected static File catalinaBase = new File(System.getProperty("catalina.base")).getAbsoluteFile();

    /*public BasedService() {
	}*/
    public BasedService(@Context HttpHeaders headers, @Context HttpServletRequest request) {
        this.headers = headers;
        this.sourceIp = SecurityUtil.getSourceIp(request);
        //this.beginExecutionTime = System.currentTimeMillis();
        _authorization = new BaseAuthorization(headers);
    }

    //protected User currentUser = new User();
    protected Response ok(Object object) {
        return Response.status(Status.OK.getStatusCode()).entity(object).build();
    }

    protected Response ok(ResponseDataPaging object) {
        return Response.status(Status.OK.getStatusCode()).entity(object.toJsonStr()).build();
    }

    protected Response okPagingWithNull(ResponseDataPaging object) {
        return Response.status(Status.OK.getStatusCode()).entity(object).build();
    }

    protected Response ok(ResponseData object) {
        return Response.status(Status.OK.getStatusCode()).entity(object.toJsonStr()).build();
    }

    protected Response okWithNull(ResponseData object) {
        return Response.status(Status.OK.getStatusCode()).entity(object).build();
    }

    protected Response file(String url, String filename, Boolean isCleanUp) throws ValidationException {
        File file = new File(url);

        if (!file.exists()) {
            throw new ValidationException(Messages.getString("validation.field.mainMessage", Labels.getString("label.file.download")));
        }

        ResponseBuilder response = Response.ok((Object) file);
        response.header("Content-Disposition", String.format("attachment; filename=\"%s\"", filename));

        //clean up
        if (isCleanUp) {
            file.deleteOnExit();
        }

        return response.build();
    }

    protected Response created(Object object) {
        return Response.status(Status.CREATED.getStatusCode()).entity(object)
                .build();
    }

    protected Response accepted(Object object) {
        return Response.status(Status.ACCEPTED.getStatusCode()).entity(object)
                .build();
    }

    protected Response noContent(ResponseData object) {
        return Response.status(Status.NO_CONTENT.getStatusCode())
                .entity(object.toJsonStr()).build();
    }

    protected Response noContent(Object object) {
        return Response.status(Status.NO_CONTENT.getStatusCode())
                .entity(object).build();
    }

    protected Response notModified(Object object) {
        return Response.status(Status.NOT_MODIFIED.getStatusCode())
                .entity(object).build();
    }

    protected Response internalServerError(Object object) {
        return Response.status(Status.INTERNAL_SERVER_ERROR.getStatusCode())
                .entity(object).build();
    }

    protected Response unauthorize(Object object) {
        return Response.status(Status.UNAUTHORIZED.getStatusCode())
                .entity(object).build();
    }

    protected Response badRequest(Object object) {
        return Response.status(Status.BAD_REQUEST.getStatusCode())
                .entity(object).build();
    }

    protected Response returnObject(Object object) {
        return Response.ok(object, MediaType.APPLICATION_JSON).build();
    }

    protected <T> T authorize(String authType, Callable<T> func) throws Exception {

        if ("standard".equals(authType)) {
            validateToken();
        } else // refresh
        {
            validateRefreshToken();
        }

        return func.call();
    }

    protected <T> T anonymous(Callable<T> func) throws Exception {
        return func.call();
    }

    private void validateToken() throws Exception {

        try (AuthorizationManager manager = new AuthorizationManager()) {

            //IAuthorization authorization = null;
            AuthorizationTokenResult result = _authorization.processHeader();

            if (result.getTokenEnum() == AuthorizationToken.BEARER) {

                String token = result.getToken();

                try {

                    if (JwTokenHelper.isTokenExpired(token)) {
                        throw new AuthorizationException("Token is expired!");
                    }

                    String identity = result.getIdentity();
                    String identityFromToken = JwTokenHelper.getIdentityFromToken(token);
                    if (!identity.equals(identityFromToken)) {
                        throw new AuthorizationException("Unauthorized.");
                    }

                } catch (Exception ex) {

                    System.out.println("validateToken.ex: " + ex.toString());

                    if (ex instanceof ExpiredJwtException) {
                        throw new AuthorizationException("Token is expired!");
                    } else if (ex instanceof MalformedJwtException) {
                        throw new AuthorizationException("Token is invalid!");
                    } else {
                        throw new AuthorizationException("Unauthorized.");
                    }
                }
            }

            /*if(result.getTokenEnum() == AuthorizationToken.BASIC) {
				
				authorization = new BasicAuthorization(null);
				authorization.authorize(result.getToken());
			}*/
        } catch (NoRecordFoundException ex) {

            throw new AuthorizationException("Unauthorized");
        }
    }

    private void validateRefreshToken() throws Exception {

        try (AuthorizationManager manager = new AuthorizationManager()) {

            AuthorizationTokenResult result = _authorization.processHeader();

            if (result.getTokenEnum() == AuthorizationToken.BEARER) {

                String token = result.getToken();

                String epIdentity = "";
                String identityFromToken = "";

                try {

                    epIdentity = result.getIdentity();
                    identityFromToken = JwTokenHelper.getIdentityFromToken(token);

                    if (!epIdentity.equals(identityFromToken)) {
                        throw new AuthorizationException("Unauthorized.");
                    }

                } catch (Exception ex) {

                    System.out.println("validateRefreshToken.ex: " + ex.toString());

                    if (ex instanceof ExpiredJwtException) {

                        identityFromToken = ((ExpiredJwtException) ex).getClaims().getSubject();

                        if (!epIdentity.equals(identityFromToken)) {
                            throw new AuthorizationException("Unauthorized.");
                        }
                    } else if (ex instanceof MalformedJwtException) {
                        throw new AuthorizationException("Token is invalid!");
                    } else {
                        throw new AuthorizationException("Unauthorized.");
                    }
                }
            }

        } catch (NoRecordFoundException ex) {

            throw new AuthorizationException("Unauthorized");
        }
    }

    /*private void validatePermission(ServiceName serviceName) throws Exception {
		try (UserManager manager = new UserManager()) {
			
			if(!manager.isServiceAllow(this.currentUser.getLoginId().toString(), serviceName))
				throw new PermissionException("Permission denied.");
		}
	}*/
    protected Response responseFileDownload(File file) throws ValidationException, IOException {

        if (!file.exists()) {
            throw new ValidationException(Messages.getString("validation.field.mainMessage", "File not found!"));
        }

        StreamingOutput stream = new StreamingOutput() {

            @Override
            public void write(OutputStream outputStream) throws IOException {

                InputStream inputStream = null;

                try {

                    inputStream = new FileInputStream(file);

                    byte[] bytesArray = new byte[4096];
                    int bytesRead = -1;
                    while ((bytesRead = inputStream.read(bytesArray)) != -1) {
                        outputStream.write(bytesArray, 0, bytesRead);
                    }

                    // Delete file after response
                    // file.delete();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (inputStream != null) {
                        inputStream.close();
                    }
                }
            }
        };

        return Response.ok(stream)
                .header("content-disposition", "attachment; filename = " + file.getName())
                .build();
    }
}
