package com.myportfolio.socialnetwork.services;

import com.myportfolio.socialnetwork.domain.PostDomain;
import com.myportfolio.socialnetwork.domain.UserDomain;
import com.myportfolio.socialnetwork.domain.enums.UserProfile;
import com.myportfolio.socialnetwork.repositories.PostRepository;
import com.myportfolio.socialnetwork.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DBServiceH2 {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void instantiateTestDatabase() {
        UserDomain user01 = new UserDomain(
                "Homer J. Simpson",
                "homer.simpson@gmail.com",
                bCryptPasswordEncoder.encode("1234")
        ).addProfile(UserProfile.USER).addProfile(UserProfile.ADMIN);

        UserDomain user02 = new UserDomain(
                "Marge Bouvier Simpson",
                "marge.simpson@gmail.com",
                bCryptPasswordEncoder.encode("1234")
        ).addProfile(UserProfile.USER);

        UserDomain user03 = new UserDomain(
                "Bartholomew Simpson",
                "bart.simpson@gmail.com",
                bCryptPasswordEncoder.encode("1234")
        ).addProfile(UserProfile.USER);

        UserDomain user04 = new UserDomain(
                "Lisa Simpson",
                "lisa.simpson@gmail.com",
                bCryptPasswordEncoder.encode("1234")
        ).addProfile(UserProfile.USER);

        UserDomain user05 = new UserDomain(
                "Meg Simpson",
                "meg.simpson@gmail.com",
                bCryptPasswordEncoder.encode("1234")
        ).addProfile(UserProfile.USER);

        UserDomain user06 = new UserDomain(
                "Abraham Simpson",
                "abraham.simpson@gmail.com",
                bCryptPasswordEncoder.encode("1234")
        ).addProfile(UserProfile.USER);

        UserDomain user07 = new UserDomain(
                "Apu Nahasapeemapetilon",
                "apu.nahasapeemapetilon@gmail.com",
                bCryptPasswordEncoder.encode("1234")
        ).addProfile(UserProfile.USER);

        UserDomain user08 = new UserDomain(
                "Barney Gumble",
                "barney.gumble@gmail.com",
                bCryptPasswordEncoder.encode("1234")
        ).addProfile(UserProfile.USER);

        UserDomain user09 = new UserDomain(
                "Clancy Wiggum",
                "chief.wiggum@gmail.com",
                bCryptPasswordEncoder.encode("1234")
        ).addProfile(UserProfile.USER);

        UserDomain user10 = new UserDomain(
                "Edna Krabappel",
                "ms.krabappel@gmail.com",
                bCryptPasswordEncoder.encode("1234")
        ).addProfile(UserProfile.USER);

        userRepository.saveAll(Arrays.asList(
                user01,
                user02,
                user03,
                user04,
                user05,
                user06,
                user07,
                user08,
                user09,
                user10
        ));

        PostDomain post01 = new PostDomain(
                "Frases do Homer",
                "Você pode ter todo o dinheiro do mundo mas existe algo que você nunca poderá comprar: Um dinossauro.",
                user01
        );

        PostDomain post02 = new PostDomain(
                "Frases do Homer 2",
                "A culpa é minha e eu coloco em quem eu quiser.",
                user01
        );

        PostDomain post03 = new PostDomain(
                "Frases do Bart",
                "Ay caramba!",
                user03
        );

        postRepository.saveAll(Arrays.asList(post01, post02, post03));
    }
}
