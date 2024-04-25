package com.mat.api.models.profiles;

import com.mat.api.core.crudbasic.auditing.CustomAuditEntity;
import com.mat.api.models.ennum.Role;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
//@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "users")
@RequiredArgsConstructor
public class UserEntity extends CustomAuditEntity implements Serializable {
    private static final long serialVersionUID = -4414534673832359181L;

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "user_id_seq",
            sequenceName = "user_id_seq",
            allocationSize = 1,
            initialValue = 100)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "user_id_seq")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "enabled")
    private boolean enabled;


    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    public List<SimpleGrantedAuthority> getGrantedAuthoritiesList() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    /*@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))*/
    /*@Column(name = "role")

    @ElementCollection(targetClass = Role.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    private Set<Role> roles = new HashSet<>();

    public List<SimpleGrantedAuthority> getGrantedAuthoritiesList() {
        return this.roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.name()))
                .collect(Collectors.toList());
    }
*/



}
