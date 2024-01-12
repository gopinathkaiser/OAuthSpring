package com.OAuth.OAuthWithout.JWT.Repository;

import com.OAuth.OAuthWithout.JWT.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity,Long> {
    UserEntity findByEmail(String username);
}
