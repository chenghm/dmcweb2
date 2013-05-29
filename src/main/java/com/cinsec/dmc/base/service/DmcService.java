package com.cinsec.dmc.base.service;

import java.util.List;

import com.cinsec.dmc.base.entity.DmcSite;
import com.cinsec.dmc.base.entity.DmcUser;

public interface DmcService {
    
    DmcUser getUser(int id);
    DmcUser findUserByUsername(String username);
    int createUser(DmcUser user);
    List<DmcSite> getSites();
    List<DmcSite> findSitesByUserId(int userId);
}
