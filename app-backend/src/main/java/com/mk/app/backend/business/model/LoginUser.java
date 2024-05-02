package com.mk.app.backend.business.model;

import com.mk.app.backend.business.security.JwtUtil;
import java.util.Collection;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 登录用户身份权限
 *
 * @author ruoyi
 */
@RequiredArgsConstructor
public class LoginUser implements UserDetails {

    private User user;
    private String token;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public User getUser() {
        return user;
    }

    public LoginUser(User user) {
        this.user = user;
        this.token = JwtUtil.createJWT(String.valueOf(user.getId()));
    }

    public String getToken() {
        return token;
    }
}
