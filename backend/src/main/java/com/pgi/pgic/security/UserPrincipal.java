package com.pgi.pgic.security;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.pgi.pgic.entity.Role;
import com.pgi.pgic.entity.Utilisateur;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
public class UserPrincipal implements UserDetails {
    private Long id;
    private String email;
    private String password;
    private Role role;

    public UserPrincipal(Utilisateur utilisateur) {
        this.id = utilisateur.getId();
        this.email = utilisateur.getEmail();
        this.password = utilisateur.getMotDePasse();
        this.role = utilisateur.getRole();
    }

    public static UserPrincipal create(Utilisateur utilisateur) {
        return new UserPrincipal(utilisateur);
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() { return true; }
    @Override
    public boolean isAccountNonLocked() { return true; }
    @Override
    public boolean isCredentialsNonExpired() { return true; }
    @Override
    public boolean isEnabled() { return true; }

    // Getters pour id, email, role
    public Long getId() { return id; }
    public String getEmail() { return email; }
    public Role getRole() { return role; }
}