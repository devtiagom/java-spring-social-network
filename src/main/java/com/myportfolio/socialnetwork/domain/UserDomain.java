package com.myportfolio.socialnetwork.domain;

import com.myportfolio.socialnetwork.domain.enums.UserProfile;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "users")
public class UserDomain implements Serializable {

    private static final long serialVersionUID = 1L;

    @Getter
    @Id
    @GeneratedValue(generator = "users_seq", strategy = GenerationType.AUTO)
    private Long id;

    @Getter @Setter
    @Column(nullable = false)
    private String name;

    @Getter @Setter
    @Column(nullable = false, unique = true)
    private String email;

    @Getter @Setter
    @Column(nullable = false)
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "profiles")
    private Set<Integer> profiles = new HashSet<>();

    public UserDomain(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public Set<UserProfile> getProfiles() {
        return this.profiles.stream().map(UserProfile::toEnum).collect(Collectors.toSet());
    }

    public UserDomain addProfile(UserProfile profile) {
        this.profiles.add(profile.getProfileCode());
        return this;
    }

    public void setId(Long id) {
        if (this.id != null) this.id = id;
    }
}
