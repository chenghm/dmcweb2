package com.cinsec.dmc.base.service;

import com.cinsec.dmc.base.entity.DmcUser;

public interface UserContext {
    DmcUser getCurrentUser();
    void setCurrentUser(DmcUser user);

}
