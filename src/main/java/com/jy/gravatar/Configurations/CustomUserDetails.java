package com.jy.gravatar.Configurations;

import com.jy.gravatar.Beans.User;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class CustomUserDetails extends org.springframework.security.core.userdetails.User {

    private User user;

    public CustomUserDetails(User user, Collection<? extends GrantedAuthority> authorities) {
        super(user.getUsername(), user.getPassword(), authorities);
        this.user = user;
    }
    public CustomUserDetails(User user, String password, Collection<? extends GrantedAuthority> authorities) {
        super(user.getUsername(), password, authorities);
        this.user = user;
    }

    public CustomUserDetails(User user,
                             boolean enabled,
                             boolean accountNonExpired,
                             boolean credentialsNonExpired,
                             boolean accountNonLocked,
                             Collection<? extends GrantedAuthority> authorities) {
        super(user.getUsername(), user.getPassword(), enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.user = user;
    }
    public User getUser()
    {
        return user;
    }
}
