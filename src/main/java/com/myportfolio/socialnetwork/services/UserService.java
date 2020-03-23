package com.myportfolio.socialnetwork.services;

import com.myportfolio.socialnetwork.domain.UserDomain;
import com.myportfolio.socialnetwork.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<UserDomain> index() {
        return userRepository.findAll();
    }

    public UserDomain show(Long id) {
        return userRepository.findById(id).isPresent() ? userRepository.findById(id).get() : null;
    }

    public UserDomain store(UserDomain user) {
        return userRepository.save(user);
    }

    public UserDomain update(Long id, UserDomain userFromRequest) {
        UserDomain userFromDB = this.show(id);

        if (userFromDB != null) {
            userFromDB.setName(userFromRequest.getName());
            userFromDB.setEmail(userFromRequest.getEmail());
            return userRepository.save(userFromDB);
        }

        return null;
    }

    public void destroy(Long id) {
        UserDomain userFromDB = this.show(id);
        if (userFromDB != null) userRepository.delete(userFromDB);
    }
}
