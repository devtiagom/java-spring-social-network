package com.myportfolio.socialnetwork.repositories;

import com.myportfolio.socialnetwork.domain.CommentDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<CommentDomain, Long> {
}
