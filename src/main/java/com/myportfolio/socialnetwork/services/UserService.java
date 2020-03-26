package com.myportfolio.socialnetwork.services;

import com.myportfolio.socialnetwork.domain.UserDomain;
import com.myportfolio.socialnetwork.domain.enums.UserProfile;
import com.myportfolio.socialnetwork.dtos.UserRequestDTO;
import com.myportfolio.socialnetwork.dtos.UserRequestUpdateDTO;
import com.myportfolio.socialnetwork.repositories.UserRepository;
import com.myportfolio.socialnetwork.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public Page<UserDomain> index(Integer page, Integer size, String direction, String orderBy) {
        return this.userRepository.findAll(PageRequest.of(page, size, Sort.Direction.valueOf(direction), orderBy));
    }

    public UserDomain show(Long id) {
        return this.userRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + UserDomain.class.getName()
        ));
    }

    public UserDomain store(UserRequestDTO userDTO) {
        return this.userRepository.save(this.fromDTO(userDTO).addProfile(UserProfile.USER));
    }

    public void update(Long id, UserRequestUpdateDTO userDTO) {
        UserDomain user = this.show(id);

        if (user != null) {
            if (userDTO.getName() != null) user.setName(userDTO.getName());
            if (userDTO.getEmail() != null) user.setEmail(userDTO.getEmail());
            this.userRepository.save(user);
        }
    }

    public void destroy(Long id) {
        UserDomain userFromDB = this.show(id);
        if (userFromDB != null) this.userRepository.delete(userFromDB);
    }

    private UserDomain fromDTO(UserRequestDTO userDTO) {
        return new UserDomain(
                userDTO.getName(),
                userDTO.getEmail(),
                this.bCryptPasswordEncoder.encode(userDTO.getPassword())
        );
    }
}
