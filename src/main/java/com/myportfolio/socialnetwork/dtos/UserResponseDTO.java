package com.myportfolio.socialnetwork.dtos;

import com.myportfolio.socialnetwork.domain.UserDomain;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
public class UserResponseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String email;

    public UserResponseDTO(UserDomain userDomain) {
        this.id = userDomain.getId();
        this.name = userDomain.getName();
        this.email = userDomain.getEmail();
    }
}
