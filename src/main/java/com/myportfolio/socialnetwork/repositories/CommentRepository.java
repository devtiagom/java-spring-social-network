package com.myportfolio.socialnetwork.repositories;

import com.myportfolio.socialnetwork.domain.CommentDomain;
import com.myportfolio.socialnetwork.domain.PostDomain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CommentRepository extends JpaRepository<CommentDomain, Long> {
    @Transactional(readOnly = true)
    Page<CommentDomain> findAllByPost(PostDomain post, Pageable pageable);
}
