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
public class CommentRequestDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotEmpty(message = "Preenchimento obrigatório")
    @Length(max = 280, message = "O campo deve ter no máximo 280 caracteres")
    private String content;

    @NotNull(message = "Preenchimento obrigatório")
    private Long postId;

    @NotNull(message = "Preenchimento obrigatório")
    private Long authorId;
}
