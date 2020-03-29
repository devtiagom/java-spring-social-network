package com.myportfolio.socialnetwork.dtos;

import com.myportfolio.socialnetwork.domain.CommentDomain;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class CommentResponseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private Date date;
    private String content;
    private Integer likes;
    private Integer dislikes;
    private Long postId;
    private String authorName;

    public CommentResponseDTO(CommentDomain commentDomain) {
        this.id = commentDomain.getId();
        this.date = commentDomain.getDate();
        this.content = commentDomain.getContent();
        this.likes = commentDomain.getLikes();
        this.dislikes = commentDomain.getDislikes();
        this.postId = commentDomain.getPost().getId();
        this.authorName = commentDomain.getAuthor().getName();
    }
}
