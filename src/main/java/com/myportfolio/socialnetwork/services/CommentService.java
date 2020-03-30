package com.myportfolio.socialnetwork.services;

import com.myportfolio.socialnetwork.domain.CommentDomain;
import com.myportfolio.socialnetwork.domain.PostDomain;
import com.myportfolio.socialnetwork.domain.UserDomain;
import com.myportfolio.socialnetwork.dtos.CommentRequestDTO;
import com.myportfolio.socialnetwork.dtos.CommentRequestUpdateDTO;
import com.myportfolio.socialnetwork.repositories.CommentRepository;
import com.myportfolio.socialnetwork.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    public Page<CommentDomain> index(Integer page, Integer size, String direction, String orderBy) {
        return this.commentRepository.findAll(PageRequest.of(page, size, Sort.Direction.valueOf(direction), orderBy));
    }

    public CommentDomain show(Long id) {
        return this.commentRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + CommentDomain.class.getName()
        ));
    }

    public CommentDomain store(CommentRequestDTO commentDTO) {
        return this.commentRepository.save(this.fromDTO(commentDTO));
    }

    public void update(Long id, CommentRequestUpdateDTO commentDTO) {
        CommentDomain commentFromDB = this.show(id);

        if (commentFromDB != null) {
            if (commentDTO.getContent() != null) commentFromDB.setContent(commentDTO.getContent());
            this.commentRepository.save(commentFromDB);
        }
    }

    public void destroy(Long id) {
        CommentDomain commentFromDB = this.show(id);
        if (commentFromDB != null) this.commentRepository.delete(commentFromDB);
    }

    public Page<CommentDomain> findByPost(Long postId, Integer page, Integer size, String direction, String orderBy) {
        PostDomain post = this.postService.show(postId);
        if (post == null) return null;
        return this.commentRepository.findAllByPost(post, PageRequest.of(page, size, Sort.Direction.valueOf(direction), orderBy));
    }

    public Integer incLikes(Long id) {
        CommentDomain commentFromDB = this.show(id);
        if (commentFromDB == null) return null;
        commentFromDB.incLikesCount();
        this.commentRepository.save(commentFromDB);
        return commentFromDB.getLikes();
    }

    private CommentDomain fromDTO(CommentRequestDTO commentDTO) {
        PostDomain post = this.postService.show(commentDTO.getPostId());
        UserDomain author = this.userService.show(commentDTO.getAuthorId());
        if (post == null || author == null) return null;
        return new CommentDomain(commentDTO.getContent(), post, author);
    }
}
