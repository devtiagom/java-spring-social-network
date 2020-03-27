package com.myportfolio.socialnetwork.dtos;

import com.myportfolio.socialnetwork.domain.PostDomain;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class PostResponseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private Date date;
    private String title;
    private String content;
    private Integer likes;
    private Integer dislikes;
    private String authorName;

    public PostResponseDTO(PostDomain postDomain) {
        this.id = postDomain.getId();
        this.date = postDomain.getDate();
        this.title = postDomain.getTitle();
        this.content = postDomain.getContent();
        this.likes = postDomain.getLikes();
        this.dislikes = postDomain.getDislikes();
        this.authorName = postDomain.getAuthor().getName();
    }
}
