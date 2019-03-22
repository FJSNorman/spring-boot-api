package com.fjsn.demo.userapi.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fjsn.demo.userapi.entity.User;

public interface UserRepo extends JpaRepository<User, Long> {

	List<User> findByStatus(String status);
}
