package com.myportfolio.socialnetwork.dtos;

import com.myportfolio.socialnetwork.domain.UserDomain;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
public class UserRequestDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private String email;
    private String password;

    public UserRequestDTO(UserDomain userDomain) {
        this.name = userDomain.getName();
        this.email = userDomain.getEmail();
        this.password = userDomain.getPassword();
    }
}
