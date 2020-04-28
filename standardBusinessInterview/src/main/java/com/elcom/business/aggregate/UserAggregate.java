package com.elcom.business.aggregate;

import com.elcom.data.UnitOfWork;
import com.elcom.data.exception.NoRecordFoundException;
import com.elcom.data.entity.User;
import com.elcom.data.entity.dto.UserDataPaging;
import com.elcom.sharedbiz.dto.UserDTO;
import com.elcom.sharedbiz.validation.AuthorizationException;
import com.elcom.sharedbiz.validation.ValidationException;
import org.modelmapper.ModelMapper;

public class UserAggregate {

    private UnitOfWork _uok = null;
    private static ModelMapper modelMapper = new ModelMapper();

    public UserAggregate(UnitOfWork uok) {
        this._uok = uok;
    }

    public User login(String accountName, String password, String loginType) throws AuthorizationException {
        User user = null;
        try {
            user = this._uok.usersRepository().findUser(accountName, password, loginType);
        } catch (Exception e) {
            e.printStackTrace();
            throw new AuthorizationException("AccountName or Password is invalid.");
        }
        return user;
    }
    
    public User findUserByUUID(String uuid) throws AuthorizationException {
        User user = null;
        try {
            user = this._uok.usersRepository().findUserByUUID(uuid);
        } catch (Throwable ex) {
            System.out.println("UserAggregate.findUserByUUID.ex: " + ex.toString());
            throw new AuthorizationException("UUID is invalid.");
        }
        return user;
    }

    public UserDTO getUserInfoBy(String email) throws ValidationException, NoRecordFoundException {
        User user = this._uok.usersRepository().findUserInfoBy(email);
        return user != null ? modelMapper.map(user, UserDTO.class) : null;
    }

    public User findDetails(String uuid) throws Exception {
        return this._uok.usersRepository().findUserByUUIDToUpdate(uuid);
    }
    public UserDataPaging findAllUserByCompanyId(Long companyId) {
        return this._uok.usersRepository().findAllUserByCompanyId(companyId);
    }
}
