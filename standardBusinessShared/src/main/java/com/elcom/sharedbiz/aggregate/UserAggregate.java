package com.elcom.sharedbiz.aggregate;

import com.elcom.data.UnitOfWork;
import com.elcom.data.exception.NoRecordFoundException;
import com.elcom.data.user.entity.User;
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
            //user = this._uok.user.usersRepository().findUser(accountName, password, loginType);

            /*if(user!=null ) {
				if ("anhdv@elcom.com.vn".equals(accountName))
		            user.setRoles(Arrays.asList(Role.ROLE_MANAGER));
		        else if ("anhdv1@elcom.com.vn".equals(accountName))
		            user.setRoles(Arrays.asList(Role.ROLE_EMPLOYEE));
		        else if ("anhdv2@elcom.com.vn".equals(accountName))
		            user.setRoles(Arrays.asList(Role.ROLE_MANAGER, Role.ROLE_EMPLOYEE));
		        else // anhdv3@elcom.com.vn
		            user.setRoles(new ArrayList<>());
			}*/
        } catch (Throwable e) {
            throw new AuthorizationException("AccountName or Password is invalid.");
        }
        return user;
    }

    public UserDTO getUserInfoBy(String email) throws ValidationException, NoRecordFoundException {
        User user = null;//this._uok.user.usersRepository().findUserInfoBy(email);
        return user != null ? modelMapper.map(user, UserDTO.class) : null;
    }

}
