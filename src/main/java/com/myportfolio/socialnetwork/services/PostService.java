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

import java.util.List;

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
        PostDomain post = this.show(id);

        if (post != null) {
            if (postDTO.getTitle() != null) post.setTitle(postDTO.getTitle());
            if (postDTO.getContent() != null) post.setContent(postDTO.getContent());
            this.postRepository.save(post);
        }
    }

    public void destroy(Long id) {
        PostDomain postFromDB = this.show(id);
        if (postFromDB != null) this.postRepository.delete(postFromDB);
    }

    private PostDomain fromDTO(PostRequestDTO postDTO) {
        UserDomain author = this.userService.show(postDTO.getAuthorId());
        return new PostDomain(postDTO.getTitle(), postDTO.getContent(), author);
    }
}
