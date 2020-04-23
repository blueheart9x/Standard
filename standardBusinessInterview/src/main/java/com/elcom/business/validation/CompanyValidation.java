package com.elcom.business.validation;

import com.elcom.common.Messages;
import com.elcom.data.interview.entity.Company;
import com.elcom.model.dto.CompanyUpsertDTO;
import com.elcom.sharedbiz.validation.AbstractValidation;
import com.elcom.sharedbiz.validation.ValidationException;
import com.elcom.util.StringUtils;

public class CompanyValidation extends AbstractValidation {

    public void validateUpsert(CompanyUpsertDTO item, String actionType) throws ValidationException {

        if (item == null) {
            getMessageDes().add(Messages.getString("validation.field.invalidFormat", "input"));
            throw new ValidationException(this.buildValidationMessage());
        }

        if ("UPDATE".equals(actionType)) {
            if (item.getId() == null || item.getId().equals(0L)) {
                getMessageDes().add(Messages.getString("validation.field.madatory", "id"));
            } else if (item.getId().equals(0L)) {
                getMessageDes().add(Messages.getString("validation.field.invalidFormat", "id"));
            }
        }

        if (StringUtils.isNullOrEmpty(item.getName())) {
            getMessageDes().add(Messages.getString("validation.field.madatory", "name"));
        } else if (item.getName().length() > 256) {
            getMessageDes().add(Messages.getString("validation.field.length", "name", 256));
        }

        if (StringUtils.isNullOrEmpty(item.getAddress())) {
            getMessageDes().add(Messages.getString("validation.field.madatory", "address"));
        } else if (item.getAddress().length() > 256) {
            getMessageDes().add(Messages.getString("validation.field.length", "address", 256));
        }

        if ("INSERT".equals(actionType)) {
            if (StringUtils.isNullOrEmpty(item.getEmail())) {
                getMessageDes().add(Messages.getString("validation.field.madatory", "email"));
            } else if (!StringUtils.validateEmail(item.getEmail())) {
                getMessageDes().add(Messages.getString("validation.field.invalidFormat", "email"));
            } else if (item.getEmail().length() > 128) {
                getMessageDes().add(Messages.getString("validation.field.length", "email", 128));
            }

            if (StringUtils.isNullOrEmpty(item.getPassword())) {
                getMessageDes().add(Messages.getString("validation.field.madatory", "password"));
            } else if (item.getPassword().length() > 128) {
                getMessageDes().add(Messages.getString("validation.field.length", "password", 128));
            }
        }

        /*if ( item.getStatus() == null )
			getMessageDes().add(Messages.getString("validation.field.madatory", "status"));
		
		if ( item.get_package() == null || item.get_package() == 0 )
			getMessageDes().add(Messages.getString("validation.field.madatory", "_package"));*/
 /**/
        if (!isValid()) {
            throw new ValidationException(this.buildValidationMessage());
        }
    }

    public void validateUpdate(Company item) throws ValidationException {
        if (item == null) {
            getMessageDes().add(Messages.getString("validation.field.invalidFormat", "input"));
            throw new ValidationException(this.buildValidationMessage());
        }

        if (item.getId() == null || item.getId().equals(0L)) {
            getMessageDes().add(Messages.getString("validation.field.madatory", "id"));
        } else if (item.getId().equals(0L)) {
            getMessageDes().add(Messages.getString("validation.field.invalidFormat", "id"));
        }

        if (StringUtils.isNullOrEmpty(item.getName())) {
            getMessageDes().add(Messages.getString("validation.field.madatory", "name"));
        } else if (item.getName().length() > 256) {
            getMessageDes().add(Messages.getString("validation.field.length", "name", 256));
        }

        if (StringUtils.isNullOrEmpty(item.getAddress())) {
            getMessageDes().add(Messages.getString("validation.field.madatory", "address"));
        } else if (item.getAddress().length() > 256) {
            getMessageDes().add(Messages.getString("validation.field.length", "address", 256));
        }

        if (!isValid()) {
            throw new ValidationException(this.buildValidationMessage());
        }
    }
}
