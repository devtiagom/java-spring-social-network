package com.myportfolio.socialnetwork.controllers;

import com.myportfolio.socialnetwork.domain.PostDomain;
import com.myportfolio.socialnetwork.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<PostDomain>> index() {
        List<PostDomain> posts = postService.index();
        return ResponseEntity.status(HttpStatus.OK).body(posts);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<PostDomain> show(@PathVariable Long id) {
        PostDomain post = postService.show(id);
        return ResponseEntity.status(HttpStatus.OK).body(post);
    }
}
