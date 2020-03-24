package com.myportfolio.socialnetwork.services;

import com.myportfolio.socialnetwork.domain.UserDomain;
import com.myportfolio.socialnetwork.domain.enums.UserProfile;
import com.myportfolio.socialnetwork.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DBServiceH2 {

    @Autowired
    private UserRepository userRepository;

    public void instantiateTestDatabase() {
        UserDomain user01 = new UserDomain(
                "Fulano de Tal",
                "fulano.tal@gmail.com",
                "1234"
        ).addProfile(UserProfile.USER).addProfile(UserProfile.ADMIN);

        UserDomain user02 = new UserDomain(
                "Beltrano de Tal",
                "beltrano.tal@gmail.com",
                "1234"
        ).addProfile(UserProfile.USER);

        UserDomain user03 = new UserDomain(
                "Ciclano de Tal",
                "ciclano.tal@gmail.com",
                "1234"
        ).addProfile(UserProfile.USER);

        userRepository.saveAll(Arrays.asList(user01, user02, user03));
    }
}
