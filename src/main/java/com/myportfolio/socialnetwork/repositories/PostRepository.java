package com.myportfolio.socialnetwork.repositories;

import com.myportfolio.socialnetwork.domain.PostDomain;
import com.myportfolio.socialnetwork.domain.UserDomain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PostRepository extends JpaRepository<PostDomain, Long> {
    @Transactional(readOnly = true)
    Page<PostDomain> findAllByAuthor(UserDomain author, Pageable pageable);
}
