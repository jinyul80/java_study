package com.study.blog.config.auth;

import com.study.blog.model.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@Getter
public class UserDetail implements UserDetails {
    private User user;

    public UserDetail(User user) {
        this.user = user;
    }

    // 계정이 갖고 있는 권한목록을 리턴한다.
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collectors = new ArrayList<>();
        // 스프링에서는 "ROLE_" 추가로 붙여야 함
        collectors.add((GrantedAuthority) () -> "ROLE_"+user.getRole());

        return collectors;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    // 계정이 만료되지 않았는지 리턴한다.(true:만료안됨)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 계정이 잠겨있지 않았는지 리턴한다. (true:잠기지 않음)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 비밀번호가 만료되지 않았는지 리턴한다. (true:만료안됨)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 계정 활성화(사용가능)인지 리턴한다. (true:활성화)
    @Override
    public boolean isEnabled() {
        return true;
    }
}
