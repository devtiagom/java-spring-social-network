package com.myportfolio.socialnetwork.controllers;

import com.myportfolio.socialnetwork.domain.PostDomain;
import com.myportfolio.socialnetwork.dtos.PostRequestDTO;
import com.myportfolio.socialnetwork.dtos.PostRequestUpdateDTO;
import com.myportfolio.socialnetwork.dtos.PostResponseDTO;
import com.myportfolio.socialnetwork.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<PostResponseDTO>> index(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "24") Integer size,
            @RequestParam(value = "direction", defaultValue = "DESC") String direction,
            @RequestParam(value = "orderBy", defaultValue = "date") String orderBy
    ) {
        Page<PostResponseDTO> postsDTO = this.postService.index(page, size, direction, orderBy).map(PostResponseDTO::new);
        return ResponseEntity.status(HttpStatus.OK).body(postsDTO);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<PostResponseDTO> show(@PathVariable Long id) {
        PostResponseDTO postDTO = new PostResponseDTO(this.postService.show(id));
        return ResponseEntity.status(HttpStatus.OK).body(postDTO);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> store(@Valid @RequestBody PostRequestDTO postDTO) {
        PostDomain post = this.postService.store(postDTO);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(post.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@PathVariable Long id, @Valid @RequestBody PostRequestUpdateDTO postDTO) {
        this.postService.update(id, postDTO);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> destroy(@PathVariable Long id) {
        this.postService.destroy(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
