package com.myportfolio.socialnetwork.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
public class PostRequestUpdateDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Length(max = 50, message = "O campo deve ter no máximo 50 caracteres")
    private String title;

    @Length(max = 280, message = "O campo deve ter no máximo 280 caracteres")
    private String content;
}
