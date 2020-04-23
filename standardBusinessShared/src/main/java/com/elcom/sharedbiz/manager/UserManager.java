package com.elcom.sharedbiz.manager;

public class UserManager extends BaseManager {

    public boolean isServiceAllow(String loginId) throws Exception {
        return this.tryCatch(() -> {
            //return UserFactory.getUserAgg(this.uok).isServiceAllow(loginId, serviceName);
            return false;
        });
    }
}
