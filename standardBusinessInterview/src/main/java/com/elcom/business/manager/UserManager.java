package com.elcom.business.manager;

import com.elcom.business.factory.StandardFactory;
import com.elcom.business.factory.UserFactory;
import java.io.IOException;
import javax.ws.rs.core.Response.Status;
import com.elcom.common.Messages;
import com.elcom.data.entity.dto.UserDataPaging;
import com.elcom.data.entity.User;
import com.elcom.model.dto.ResponseData;
import com.elcom.model.dto.ResponseDataPaging;
import com.elcom.sharedbiz.manager.BaseManager;
import com.elcom.sharedbiz.validation.ValidationException;

public class UserManager extends BaseManager {

    public UserManager() {
    }

    public ResponseData findDetails(String uuid) throws Exception {
        StandardFactory.getUserValidation().validateIdentityUser(uuid);
        return this.tryCatch(() -> {
            User result = UserFactory.getUserAgg(uok).findDetails(uuid);
            return new ResponseData(
                    result != null ? Status.OK.getStatusCode() : Status.NO_CONTENT.getStatusCode(),
                    result != null ? Status.OK.toString() : Status.NO_CONTENT.toString(),
                    result
            );
        });
    }

    public ResponseDataPaging findAllUserByCompanyId(Long companyId) throws Exception {

        if (companyId == null) {
            throw new ValidationException(Messages.getString("validation.field.madatory", "companyId"));
        }

        return this.tryCatch(() -> {

            UserDataPaging result = UserFactory.getUserAgg(uok).findAllUserByCompanyId(companyId);

            return new ResponseDataPaging(
                    !result.getDataRows().isEmpty() ? Status.OK.getStatusCode() : Status.NO_CONTENT.getStatusCode(),
                    !result.getDataRows().isEmpty() ? Status.OK.toString() : Status.NO_CONTENT.toString(),
                    result.getTotalRows(),
                    result.getDataRows()
            );
        });
    }

    @Override
    public void close() throws IOException {

        this.uok = null;
    }
}
