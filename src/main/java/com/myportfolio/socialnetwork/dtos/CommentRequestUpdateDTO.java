package com.myportfolio.socialnetwork.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
public class CommentRequestUpdateDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Length(max = 280, message = "O campo deve ter no máximo 280 caracteres")
    private String content;
}
