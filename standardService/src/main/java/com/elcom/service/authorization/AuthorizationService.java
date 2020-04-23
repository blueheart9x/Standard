package com.elcom.service.authorization;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.elcom.model.dto.AuthorizationRefreshTokenDTO;
import com.elcom.model.dto.AuthorizationRequestDTO;
import com.elcom.model.dto.ResponseSuccess;
import com.elcom.service.BasedService;
import com.elcom.sharedbiz.manager.AuthorizationManager;

@Path("/v1.0/authorization")
public class AuthorizationService extends BasedService {

    public AuthorizationService(@Context HttpHeaders headers, @Context HttpServletRequest request) {
        super(headers, request);
    }

    /**
     * verify and certify user by passing email/mobile and password. this method
     * is used for Interview Services.
     *
     * @author anhdv
     * @param email/mobile, password
     * @return user information that is wrapped in AuthorizationResponseDTO
     * object
     * @throws Exception when email/mobile or password is invalid.
     */
    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response authorized(AuthorizationRequestDTO request) throws Exception {
        try (AuthorizationManager manager = new AuthorizationManager()) {
            return ok(manager.authorized(request));
        }
    }

    /**
     * verify refresh_token based on email address. this method is used for EP
     * services.
     *
     * @author anhdv
     * @param email, refresh_token
     * @return a new access_token
     * @throws Exception when email or refresh_token is invalid.
     */
    @POST
    @Path("/refresh-token")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response refreshToken(AuthorizationRefreshTokenDTO request) throws Exception {
        return this.authorize("refresh", () -> {
            try (AuthorizationManager manager = new AuthorizationManager()) {
                return ok(manager.refreshToken(request));
            }
        });
    }

    /**
     * verify token whether still valid or not.
     *
     * @author anhdv
     * @param none
     * @return 200 or 401
     */
    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get() throws Exception {

        return this.authorize("standard", () -> {

            return ok(new ResponseSuccess());
        });
    }
}
