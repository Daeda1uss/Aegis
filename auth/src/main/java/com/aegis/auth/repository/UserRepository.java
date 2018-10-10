package com.aegis.auth.repository;

import com.aegis.auth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by GarryGe on 2018/3/27.
 */
//https://github.com/sharmaritesh/spring-angularjs-oauth2-sample
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
