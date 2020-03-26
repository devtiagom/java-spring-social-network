package com.myportfolio.socialnetwork.dtos;

import com.myportfolio.socialnetwork.domain.UserDomain;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
public class UserRequestUpdateDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Length(min = 5, max = 120, message = "O campo nome entre 5 e 120 caracteres")
    private String name;

    @Email(message = "E-mail inválido")
    private String email;

    @Length(min = 4, max = 8, message = "O campo senha deve ter entre 4 e 8 caracteres")
    private String password;

    public UserRequestUpdateDTO(UserDomain userDomain) {
        this.name = userDomain.getName();
        this.email = userDomain.getEmail();
        this.password = userDomain.getPassword();
    }
}
