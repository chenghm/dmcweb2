package com.cinsec.dmc.user.service.impl;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cinsec.dmc.user.dao.IUserDao;
import com.cinsec.dmc.user.entity.User;

@Service
public class UserDetailServiceImpl implements UserDetailsService,Serializable {

	
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -6294973248618460180L;
	private final IUserDao userDao;

    @Autowired
    public UserDetailServiceImpl(IUserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username/password.");
        }

        return new UserDetailsImpl(user);
    }

    private final class UserDetailsImpl extends User implements UserDetails {
        private static final long serialVersionUID = 2586035525618223858L;

        UserDetailsImpl(User user) {
            setId(user.getId());
            setUsername(user.getUsername());
            setPassword(user.getPassword());
            setRole(user.getRole());
        }
        

        public Collection<? extends GrantedAuthority> getAuthorities() {
            return userDao.getAuthorities(this);
        }

        public boolean isAccountNonExpired() {
            return true;
        }

        public boolean isAccountNonLocked() {
            return true;
        }

        public boolean isCredentialsNonExpired() {
            return true;
        }

        public boolean isEnabled() {
            return true;
        }
        
    }

   
}
