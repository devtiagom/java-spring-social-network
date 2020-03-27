package com.myportfolio.socialnetwork.repositories;

import com.myportfolio.socialnetwork.domain.PostDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<PostDomain, Long> {
}
