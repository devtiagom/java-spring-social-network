package com.myportfolio.socialnetwork.services;

import com.myportfolio.socialnetwork.domain.PostDomain;
import com.myportfolio.socialnetwork.repositories.PostRepository;
import com.myportfolio.socialnetwork.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public List<PostDomain> index() {
        return postRepository.findAll();
    }

    public PostDomain show(Long id) {
        return postRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + PostDomain.class.getName()
        ));
    }
}
