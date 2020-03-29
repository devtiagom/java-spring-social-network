package com.myportfolio.socialnetwork.services;

import com.myportfolio.socialnetwork.domain.CommentDomain;
import com.myportfolio.socialnetwork.domain.PostDomain;
import com.myportfolio.socialnetwork.domain.UserDomain;
import com.myportfolio.socialnetwork.domain.enums.UserProfile;
import com.myportfolio.socialnetwork.repositories.CommentRepository;
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
    private CommentRepository commentRepository;

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

        this.userRepository.saveAll(Arrays.asList(
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

        PostDomain post04 = new PostDomain(
                "Frases do Bart 2",
                "Eu não estou autorizado a demitir professores substitutos.",
                user03
        );

        PostDomain post05 = new PostDomain(
                "Frases do Bart 3",
                "Eu não farei download ilegal deste filme.",
                user03
        );

        PostDomain post06 = new PostDomain(
                "Frases do Homer 3",
                "Se Deus não quisesse que nós comêssemos as vacas, não as teria feito de carne.",
                user01
        );

        PostDomain post07 = new PostDomain(
                "Frases do Homer 4",
                "Eu não sou normalmente alguém que ora, mas se você estiver aí em cima, por favor me salve, Superman.",
                user01
        );

        this.postRepository.saveAll(Arrays.asList(post01, post02, post03, post04, post05, post06, post07));

        CommentDomain comment01 = new CommentDomain(
                "Lotus eleatess ducunt ad byssus. Cum contencio favere, omnes classises experientia velox, grandis lacteaes.",
                post01,
                user02
        );

        CommentDomain comment02 = new CommentDomain(
                "Ay Caramba.",
                post01,
                user03
        );

        CommentDomain comment03 = new CommentDomain(
                "Shut up Hommer.",
                post02,
                user03
        );

        CommentDomain comment04 = new CommentDomain(
                "dui faucibus in ornare quam viverra orci sagittis eu volutpat.",
                post02,
                user04
        );

        CommentDomain comment05 = new CommentDomain(
                "Aid neque aliquam vestibulum morbi blandit cursus risus at ultrices.",
                post03,
                user04
        );

        CommentDomain comment06 = new CommentDomain(
                "pellentesque sit amet porttitor eget dolor morbi non arcu risus.",
                post07,
                user04
        );

        this.commentRepository.saveAll(Arrays.asList(comment01, comment02, comment03, comment04, comment05, comment06));
    }
}
