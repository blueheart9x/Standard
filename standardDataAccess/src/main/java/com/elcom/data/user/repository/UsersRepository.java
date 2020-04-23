package com.elcom.data.user.repository;

import org.hibernate.Session;
import com.elcom.data.BaseRepository;
import com.elcom.data.repository.IUpsertRepository;
import com.elcom.data.user.entity.User;
import com.elcom.data.user.entity.UserCompany;

public class UsersRepository extends BaseRepository implements IUpsertRepository<User> {

    public UsersRepository(Session session) {
        super(session);
    }

    public void update(User item) {
        this.session.update(item);
    }

    public void upsert(User item) {
        this.session.saveOrUpdate("User", item);
    }
    
    public boolean insertUserCompany(UserCompany item) {
        this.session.save("UserCompany", item);
        return true;
    }
    
}
