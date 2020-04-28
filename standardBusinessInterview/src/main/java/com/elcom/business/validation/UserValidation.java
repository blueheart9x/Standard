package com.elcom.business.validation;

import com.elcom.common.Messages;
import com.elcom.sharedbiz.validation.AbstractValidation;
import com.elcom.sharedbiz.validation.ValidationException;
import com.elcom.util.StringUtils;

public class UserValidation extends AbstractValidation {

    public void validateGetIdUserCompany(Long userId, Long companyId) throws ValidationException {

        if (userId == null || userId.equals(0L)) {
            getMessageDes().add(Messages.getString("validation.field.madatory", "userId"));
        }

        if (companyId == null || companyId.equals(0L)) {
            getMessageDes().add(Messages.getString("validation.field.madatory", "companyId"));
        }

        /**/
        if (!isValid()) {
            throw new ValidationException(this.buildValidationMessage());
        }
    }

    public void validateIdentityUser(String uuid) throws ValidationException {

        if (StringUtils.isNullOrEmpty(uuid)) {
            getMessageDes().add(Messages.getString("validation.field.madatory", "uuid"));
        } else if (uuid.length() != 36) {
            getMessageDes().add(Messages.getString("validation.field.invalidFormat", "uuid"));
        }

        /**/
        if (!isValid()) {
            throw new ValidationException(this.buildValidationMessage());
        }
    }

    public void validateChangePassword(String uuid, String password) throws ValidationException {

        if (StringUtils.isNullOrEmpty(uuid)) {
            getMessageDes().add(Messages.getString("validation.field.madatory", "uuid"));
        } else if (uuid.length() != 36) {
            getMessageDes().add(Messages.getString("validation.field.invalidFormat", "uuid"));
        }

        if (StringUtils.isNullOrEmpty(password)) {
            getMessageDes().add(Messages.getString("validation.field.madatory", "password"));
        }

        /**/
        if (!isValid()) {
            throw new ValidationException(this.buildValidationMessage());
        }
    }

    public void validateEmail(String email) throws ValidationException {

        if (StringUtils.isNullOrEmpty(email)) {
            getMessageDes().add(Messages.getString("validation.field.madatory", "email"));
        } else if (!StringUtils.validateEmail(email)) {
            getMessageDes().add(Messages.getString("validation.field.invalidFormat", "email"));
        } else if (email.length() > 64) {
            getMessageDes().add(Messages.getString("validation.field.length", "email", 64));
        }

        /**/
        if (!isValid()) {
            throw new ValidationException(this.buildValidationMessage());
        }
    }

    public void validateMobile(String mobile) throws ValidationException {

        if (StringUtils.isNullOrEmpty(mobile)) {
            getMessageDes().add(Messages.getString("validation.field.madatory", "mobile"));
        } else if (mobile.length() > 20) {
            getMessageDes().add(Messages.getString("validation.field.length", "mobile", 20));
        }

        /**/
        if (!isValid()) {
            throw new ValidationException(this.buildValidationMessage());
        }
    }
}
