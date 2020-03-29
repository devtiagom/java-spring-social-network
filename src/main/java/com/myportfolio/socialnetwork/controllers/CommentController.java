package com.myportfolio.socialnetwork.controllers;

import com.myportfolio.socialnetwork.domain.CommentDomain;
import com.myportfolio.socialnetwork.dtos.CommentRequestDTO;
import com.myportfolio.socialnetwork.dtos.CommentRequestUpdateDTO;
import com.myportfolio.socialnetwork.dtos.CommentResponseDTO;
import com.myportfolio.socialnetwork.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<CommentResponseDTO>> index(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "24") Integer size,
            @RequestParam(value = "direction", defaultValue = "DESC") String direction,
            @RequestParam(value = "orderBy", defaultValue = "date") String orderBy
    ) {
        Page<CommentResponseDTO> commentsDTO = this.commentService.index(page, size, direction, orderBy)
                .map(CommentResponseDTO::new);
        return ResponseEntity.status(HttpStatus.OK).body(commentsDTO);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<CommentResponseDTO> show(@PathVariable Long id) {
        CommentResponseDTO commentDTO = new CommentResponseDTO(this.commentService.show(id));
        return ResponseEntity.status(HttpStatus.OK).body(commentDTO);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> store(@RequestBody CommentRequestDTO commentDTO) {
        CommentDomain comment = this.commentService.store(commentDTO);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(comment.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody CommentRequestUpdateDTO commentDTO) {
        this.commentService.update(id, commentDTO);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> destroy(@PathVariable Long id) {
        this.commentService.destroy(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
