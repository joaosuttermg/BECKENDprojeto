package com.salasync.projectfinal.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.salasync.projectfinal.entity.User;


public interface UserRepository extends JpaRepository<User, Long> {
UserDetails findByLogin(String email);
}
