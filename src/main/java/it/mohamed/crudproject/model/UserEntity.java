package it.mohamed.crudproject.model;

import it.mohamed.crudproject.config.token.Token;
import it.mohamed.crudproject.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "user_table")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity implements UserDetails{

    @Serial
    private static final long serialVersionUID = 1905122041950251207L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_user", unique = true)
    private int id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(nullable = false, length = 100)
    private String login;

    @Column(nullable = false, length = 100)
    private String email;

    @Column(nullable = false, length = 100)
    private String password;

    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL)
    private List<TaskEntity> taskEntities = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "userEntity")
    private List<Token> tokens;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAuthorities();
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

}
