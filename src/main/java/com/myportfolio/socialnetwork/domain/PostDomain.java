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
@Table(name = "posts")
public class PostDomain implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final Integer INITIAL_LIKES_COUNT = 0;
    private static final Integer INITIAL_DISLIKES_COUNT = 0;

    @Getter
    @Id
    @GeneratedValue(generator = "posts_seq", strategy = GenerationType.AUTO)
    private Long id;

    @Getter
    @Column(nullable = false)
    private Date date;

    @Getter @Setter
    @Column(nullable = false)
    private String title;

    @Getter @Setter
    @Column(nullable = false)
    private String content;

    @Getter
    private Integer likes;

    @Getter
    private Integer dislikes;

    @Getter
    @ManyToOne
    @JoinColumn(name = "id_author")
    private UserDomain author;

    public PostDomain() {
        this.likes = PostDomain.INITIAL_LIKES_COUNT;
        this.dislikes = PostDomain.INITIAL_DISLIKES_COUNT;
        this.date = new Date(System.currentTimeMillis());
    }

    public PostDomain(String title, String content, UserDomain author) {
        this();
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public PostDomain(Long id, String title, String content, UserDomain author) {
        this(title, content, author);
        this.id = id;
    }

    public void setId(Long id) {
        if (this.id != null) this.id = id;
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
