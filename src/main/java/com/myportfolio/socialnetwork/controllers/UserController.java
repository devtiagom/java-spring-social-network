package com.myportfolio.socialnetwork.controllers;

import com.myportfolio.socialnetwork.domain.UserDomain;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<UserDomain>> index() {
        UserDomain user01 = new UserDomain("Fulano", "fulano@gmail.com", "1234");
        UserDomain user02 = new UserDomain("Ciclano", "ciclano@gmail.com", "1234");

        List<UserDomain> users = new ArrayList<>();
        users.add(user01);
        users.add(user02);

        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<UserDomain> show(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(new UserDomain());
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> store(@RequestBody UserDomain user) {
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody UserDomain user) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
