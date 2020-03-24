package com.myportfolio.socialnetwork.services;

import com.myportfolio.socialnetwork.domain.UserDomain;
import com.myportfolio.socialnetwork.domain.enums.UserProfile;
import com.myportfolio.socialnetwork.dtos.UserRequestDTO;
import com.myportfolio.socialnetwork.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserDomain> index() {
        return userRepository.findAll();
    }

    public UserDomain show(Long id) {
        return userRepository.findById(id).isPresent() ? userRepository.findById(id).get() : null;
    }

    public UserDomain store(UserRequestDTO userDTO) {
        return userRepository.save(this.fromDTO(userDTO).addProfile(UserProfile.USER));
    }

    public void update(Long id, UserRequestDTO userDTO) {
        UserDomain user = this.show(id);

        if (user != null) {
            user.setName(userDTO.getName());
            user.setEmail(userDTO.getEmail());
            userRepository.save(user);
        }
    }

    public void destroy(Long id) {
        UserDomain userFromDB = this.show(id);
        if (userFromDB != null) userRepository.delete(userFromDB);
    }

    private UserDomain fromDTO(UserRequestDTO userDTO) {
        return new UserDomain(userDTO.getName(), userDTO.getEmail(), userDTO.getPassword());
    }
}
