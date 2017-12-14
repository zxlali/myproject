package com.zxl.test.myproject.config;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Alex on 17/6/2 下午3:08.
 */
public class CustUserDetail implements UserDetails {

    private String username;
    private String password;
    private Boolean enable;
    private final Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
    @Override public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<String> roles) {
        roles.stream().map(SimpleGrantedAuthority::new).forEach(authorities :: add);
    }


    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    @Override public String getPassword() {
        return password;
    }

    @Override public String getUsername() {
        return username;
    }

    @Override public boolean isAccountNonExpired() {
        return true;
    }

    @Override public boolean isAccountNonLocked() {
        return true;
    }

    @Override public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override public boolean isEnabled() {
        return enable;
    }
}
