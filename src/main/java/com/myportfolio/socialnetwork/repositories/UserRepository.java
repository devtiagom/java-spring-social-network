package com.myportfolio.socialnetwork.repositories;

import com.myportfolio.socialnetwork.domain.UserDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserDomain, Long> {}
