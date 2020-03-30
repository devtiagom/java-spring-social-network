package com.myportfolio.socialnetwork.services;

import com.myportfolio.socialnetwork.domain.PostDomain;
import com.myportfolio.socialnetwork.domain.UserDomain;
import com.myportfolio.socialnetwork.dtos.PostRequestDTO;
import com.myportfolio.socialnetwork.dtos.PostRequestUpdateDTO;
import com.myportfolio.socialnetwork.repositories.PostRepository;
import com.myportfolio.socialnetwork.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserService userService;

    public Page<PostDomain> index(Integer page, Integer size, String direction, String orderBy) {
        return this.postRepository.findAll(PageRequest.of(page, size, Sort.Direction.valueOf(direction), orderBy));
    }

    public PostDomain show(Long id) {
        return this.postRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + PostDomain.class.getName()
        ));
    }

    public PostDomain store(PostRequestDTO postDTO) {
        return this.postRepository.save(this.fromDTO(postDTO));
    }

    public void update(Long id, PostRequestUpdateDTO postDTO) {
        PostDomain postFromDB = this.show(id);

        if (postFromDB != null) {
            if (postDTO.getTitle() != null) postFromDB.setTitle(postDTO.getTitle());
            if (postDTO.getContent() != null) postFromDB.setContent(postDTO.getContent());
            this.postRepository.save(postFromDB);
        }
    }

    public void destroy(Long id) {
        PostDomain postFromDB = this.show(id);
        if (postFromDB != null) this.postRepository.delete(postFromDB);
    }

    public Page<PostDomain> findByAuthor(Long authorId, Integer page, Integer size, String direction, String orderBy) {
        UserDomain author = this.userService.show(authorId);
        if (author == null) return null;
        return this.postRepository.findAllByAuthor(author, PageRequest.of(page, size, Sort.Direction.valueOf(direction), orderBy));
    }

    public Integer incLikes(Long id) {
        PostDomain postFromDB = this.show(id);
        if (postFromDB == null) return null;
        postFromDB.incLikesCount();
        this.postRepository.save(postFromDB);
        return postFromDB.getLikes();
    }

    private PostDomain fromDTO(PostRequestDTO postDTO) {
        UserDomain author = this.userService.show(postDTO.getAuthorId());
        if (author == null) return null;
        return new PostDomain(postDTO.getTitle(), postDTO.getContent(), author);
    }
}
