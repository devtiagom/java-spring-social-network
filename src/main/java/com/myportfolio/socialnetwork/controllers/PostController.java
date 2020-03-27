package com.myportfolio.socialnetwork.controllers;

import com.myportfolio.socialnetwork.domain.PostDomain;
import com.myportfolio.socialnetwork.dtos.PostRequestDTO;
import com.myportfolio.socialnetwork.dtos.PostResponseDTO;
import com.myportfolio.socialnetwork.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<PostResponseDTO>> index() {
        List<PostResponseDTO> postsDTO = postService.index().stream().map(PostResponseDTO::new).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(postsDTO);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<PostResponseDTO> show(@PathVariable Long id) {
        PostResponseDTO postDTO = new PostResponseDTO(postService.show(id));
        return ResponseEntity.status(HttpStatus.OK).body(postDTO);
    }
}
