package com.example.carservice.data.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@ToString
@Table(name = "user")
public class User extends BaseEntity implements UserDetails {
    @Column(nullable = false)
    private String firstName;
    @Column
    private String lastName;
    @Column(nullable = false,unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column
    private boolean isAccountNonExpired = true;
    @Column
    private boolean isAccountNonLocked = true;
    @Column
    private boolean isCredentialsNonExpired = true;
    @Column
    private boolean isEnabled = true;

    @ManyToMany(fetch = FetchType.EAGER)
    @ToString.Exclude
    private Set<Role> authorities;
    //Employee Qualifications
    @ManyToMany
    @JoinTable(
            name = "employee_qualifications",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "qualification_id")
    )
    @ToString.Exclude
    private List<Qualification> qualifications;
    //Employee where works(Car Service)  maybe create Owner/Manager
    @ManyToOne
    @JoinColumn(name = "carservice_id")
    @ToString.Exclude
    private CarService carService;
    //Customer vehicles
    @OneToMany(mappedBy = "owner",fetch = FetchType.EAGER)
    @ToString.Exclude
    private Set<Vehicle> myVehicles = new HashSet<>();
    //Customer repairs
    @OneToMany(mappedBy = "customer")
    @ToString.Exclude
    private Set<Repair> myRepairs = new HashSet<>();
}
