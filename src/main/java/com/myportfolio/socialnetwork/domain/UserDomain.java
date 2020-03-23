package com.myportfolio.socialnetwork.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

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
    private String name;

    @Getter @Setter
    private String email;

    @Getter
    private String password;

    public UserDomain(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
