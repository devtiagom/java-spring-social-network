package com.myportfolio.socialnetwork.services;

import com.myportfolio.socialnetwork.domain.PostDomain;
import com.myportfolio.socialnetwork.domain.UserDomain;
import com.myportfolio.socialnetwork.dtos.PostRequestDTO;
import com.myportfolio.socialnetwork.repositories.PostRepository;
import com.myportfolio.socialnetwork.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserService userService;

    public List<PostDomain> index() {
        return this.postRepository.findAll();
    }

    public PostDomain show(Long id) {
        return this.postRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + PostDomain.class.getName()
        ));
    }

    public PostDomain store(PostRequestDTO postDTO) {
        return this.postRepository.save(this.fromDTO(postDTO));
    }

    private PostDomain fromDTO(PostRequestDTO postDTO) {
        UserDomain author = this.userService.show(postDTO.getAuthorId());
        return new PostDomain(postDTO.getTitle(), postDTO.getContent(), author);
    }
}
