package com.example.StudentFeedback.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.StudentFeedback.entity.Token;

public interface TokenRepository extends JpaRepository<Token, Long> {

	@Query("SELECT r FROM Token r WHERE r.token=?1")
	public Token FindByToken(String token);
	
	public Optional<Token> findByToken(String token);
	public Optional<Token> findByEmail(String email);
	public void deleteByToken(String token);

}
