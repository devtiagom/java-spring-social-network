package com.myportfolio.socialnetwork.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@EqualsAndHashCode
@ToString
@Entity
@Table(name = "comments")
public class CommentDomain implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final Integer INITIAL_LIKES_COUNT = 0;
    private static final Integer INITIAL_DISLIKES_COUNT = 0;

    @Getter
    @Id
    @GeneratedValue(generator = "comments_seq", strategy = GenerationType.AUTO)
    private Long id;

    @Getter
    @Column(nullable = false)
    private Date date;

    @Getter @Setter
    @Column(nullable = false)
    private String content;

    @Getter
    @Column(nullable = false)
    private Integer likes;

    @Getter
    @Column(nullable = false)
    private Integer dislikes;

    @Getter
    @ManyToOne
    @JoinColumn(name = "post_id")
    private PostDomain post;

    @Getter
    @ManyToOne
    @JoinColumn(name = "author_id")
    private UserDomain author;

    public CommentDomain() {
        this.date = new Date(System.currentTimeMillis());
        this.likes = CommentDomain.INITIAL_LIKES_COUNT;
        this.dislikes = CommentDomain.INITIAL_DISLIKES_COUNT;
    }

    public CommentDomain(String content, PostDomain post, UserDomain author) {
        this();
        this.content = content;
        this.post = post;
        this.author = author;
    }

    public CommentDomain(Long id, String content, PostDomain post, UserDomain author) {
        this(content, post, author);
        this.id = id;
    }

    public void setId(Long id) {
        if (this.id != null) this.id = id;
    }

    public void setPost(PostDomain post) {
        if (this.post != null) this.post = post;
    }

    public void setAuthor(UserDomain author) {
        if (this.author != null) this.author = author;
    }

    public void incLikesCount() {
        this.likes++;
    }

    public void decLikesCount() {
        this.likes--;
    }

    public void incDislikesCount() {
        this.dislikes++;
    }

    public void decDislikesCount() {
        this.dislikes--;
    }
}
