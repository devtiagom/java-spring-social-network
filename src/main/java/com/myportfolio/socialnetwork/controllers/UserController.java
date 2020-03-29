package com.myportfolio.socialnetwork.controllers;

import com.myportfolio.socialnetwork.domain.UserDomain;
import com.myportfolio.socialnetwork.dtos.PostResponseDTO;
import com.myportfolio.socialnetwork.dtos.UserRequestDTO;
import com.myportfolio.socialnetwork.dtos.UserRequestUpdateDTO;
import com.myportfolio.socialnetwork.dtos.UserResponseDTO;
import com.myportfolio.socialnetwork.services.PostService;
import com.myportfolio.socialnetwork.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<UserResponseDTO>> index(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "24") Integer size,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
            @RequestParam(value = "orderBy", defaultValue = "name") String orderBy
    ) {
        Page<UserResponseDTO> usersDTO = this.userService.index(page, size, direction, orderBy).map(UserResponseDTO::new);
        return ResponseEntity.status(HttpStatus.OK).body(usersDTO);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<UserResponseDTO> show(@PathVariable Long id) {
        UserResponseDTO userDTO = new UserResponseDTO(this.userService.show(id));
        return ResponseEntity.status(HttpStatus.OK).body(userDTO);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> store(@Valid @RequestBody UserRequestDTO userDTO) {
        UserDomain user = this.userService.store(userDTO);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@PathVariable Long id, @Valid @RequestBody UserRequestUpdateDTO userDTO) {
        this.userService.update(id, userDTO);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> destroy(@PathVariable Long id) {
        this.userService.destroy(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @RequestMapping(value = "/{id}/posts", method = RequestMethod.GET)
    public ResponseEntity<Page<PostResponseDTO>> findUserPosts(
            @PathVariable Long id,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "24") Integer size,
            @RequestParam(value = "direction", defaultValue = "DESC") String direction,
            @RequestParam(value = "orderBy", defaultValue = "date") String orderBy
    ) {
        Page<PostResponseDTO> userPosts = this.postService.findByAuthor(id, page, size, direction, orderBy)
                .map(PostResponseDTO::new);
        return ResponseEntity.status(HttpStatus.OK).body(userPosts);
    }
}
