package com.github.authorization.repository;

import com.github.authorization.domain.entity.UserAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccountEntity, Integer> {

    Optional<UserAccountEntity> findByEmail(String email);
}
