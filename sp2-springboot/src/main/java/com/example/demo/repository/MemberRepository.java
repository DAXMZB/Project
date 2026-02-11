package com.example.demo.repository;

import com.example.demo.entity.Member; //import Entity

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository; //import JPA Repository
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {
	/*
	 * Generic Types Explained 1.Member：This is the Entity class the repository will
	 * manage. 2.Integer：This is the data type of the Primary Key(@Id) in the Member
	 * class
	 */
	Optional<Member> findByUsernameAndPassword(String username, String password);

	boolean existsByUsername(String username);
}
