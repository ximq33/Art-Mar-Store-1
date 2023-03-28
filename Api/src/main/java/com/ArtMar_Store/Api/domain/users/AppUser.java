package com.ArtMar_Store.Api.domain.users;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Document(collection = "users")
public record AppUser(
        @Id
        UserId userId,
        String name,
        String email,
        String password,
        GrantedAuthority roles,
        Boolean enabled
)  implements UserDetails {
        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
                return List.of(roles);
        }

        @Override
        public String getPassword() {
                return password;
        }

        @Override
        public String getUsername() {
                return name;
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
                return enabled;                    //TODO implement user enabling
        }
}

