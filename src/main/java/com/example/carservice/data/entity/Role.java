package com.example.carservice.data.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="role")
public class Role extends BaseEntity implements GrantedAuthority {
    private String authority;

    @ManyToMany(mappedBy = "authorities", fetch = FetchType.EAGER)
    private Set<User> users;
}
