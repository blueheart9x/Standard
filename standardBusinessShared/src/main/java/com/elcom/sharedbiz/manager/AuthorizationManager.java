package com.elcom.sharedbiz.manager;

import java.io.Closeable;
import java.io.IOException;
import com.elcom.data.user.entity.User;
import com.elcom.model.dto.AuthorizationRefreshTokenDTO;
import com.elcom.model.dto.AuthorizationRequestDTO;
import com.elcom.model.dto.AuthorizationResponseDTO;
import com.elcom.model.dto.AuthorizationResponseDTODetails;
import com.elcom.model.dto.AuthorizationResponseRefreshTokenDTO;
import com.elcom.sharedbiz.aggregate.CommonAggregate;
import com.elcom.sharedbiz.aggregate.UserAggregate;
import com.elcom.sharedbiz.dto.UserDTO;
import com.elcom.sharedbiz.factory.UserFactory;
import com.elcom.sharedbiz.validation.AuthorizationException;
import com.elcom.sharedbiz.validation.UserValidation;
import com.elcom.util.security.JWTutils;

public class AuthorizationManager extends BaseManager implements Closeable {
    private UserAggregate _uAgg = null;
    private CommonAggregate _commonAgg = null;

    public AuthorizationManager() {
        if (_uAgg == null) {
            _uAgg = UserFactory.getUserAgg(this.uok);
        }
        if (_commonAgg == null) {
            _commonAgg = UserFactory.getCommonAggregate(this.uok);
        }
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
    public AuthorizationResponseDTO authorized(AuthorizationRequestDTO request) throws Exception {

        return this.tryCatch(() -> {

            UserValidation.validateAuthorization(request);

            User user = getUserInfo(request.getAccountName(), request.getPassword(), "NORMAL");

            // Issue a token for the user
            String token = JwTokenHelper.createJWT(user.getUuid());

            return new AuthorizationResponseDTO(
                    new AuthorizationResponseDTODetails(
                            token, JWTutils.createToken(user.getUuid()), user.getId(), user.getEmail(), user.getFullName(),
                            user.getMobile(), user.getSkype(), user.getFacebook(), user.getAvatar(), user.getUserType(),
                            user.getStatus(), user.getCreatedAt(),
                            user.getLastLogin(), user.getUuid(), user.getAddress(), user.getCompanyName(), user.getCompanyId()
                    )
            );
        });
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
    public AuthorizationResponseRefreshTokenDTO refreshToken(AuthorizationRefreshTokenDTO request) throws Exception {

        return this.tryCatch(() -> {

            UserValidation.validateRefreshToken(request);

            if (!request.getUuid().equals(JWTutils.getContentInToken(request.getRefreshToken()))) {
                throw new AuthorizationException("Refresh token is invalid!");
            }

            User user = null;//getUserInfoByUUID(request.getUuid());

            // Issue a token for the user
            String token = JwTokenHelper.createJWT(user.getUuid());

            return new AuthorizationResponseRefreshTokenDTO(token);
        });
    }

    private User getUserInfo(String accountName, String password, String loginType) throws Exception {

        //User user = _commonAgg.login(accountName, password, loginType);
        User user = null;

        if (user == null) {
            throw new AuthorizationException("This account is invalid.");
        }

        if (user.getStatus() == 0) {
            throw new AuthorizationException("This account is locked.");
        }

        return user;
    }

    public UserDTO authorized(String email, String password) throws AuthorizationException, Exception {

        return this.tryCatch(() -> {

            User user = _uAgg.login(email, password, "NORMAL");

            if (user == null) {
                throw new AuthorizationException("This account is invalid.");
            }

            if (user.getStatus() == 0) {
                throw new AuthorizationException("This account is locked.");
            }

            return _uAgg.getUserInfoBy(email);
        });
    }
    
    @Override
    public void close() throws IOException {
        this._uAgg = null;
    }
}
