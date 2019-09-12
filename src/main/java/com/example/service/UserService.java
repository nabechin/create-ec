package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.User;
import com.example.repository.UserRepository;

/**
 * ユーザ情報を捜査するServiceクラス.
 * 
 * @author yuma.watanabe
 *
 */
@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository repository;
	
	@Autowired
	private PasswordEncoder encoder;

	/**
	 * ユーザ情報の挿入.
	 * 
	 * @param user ユーザ情報.
	 */
	public void insert(User user) {
		String PasswordEncoder = encoder.encode(user.getPassword());
		user.setPassword(PasswordEncoder);
		repository.insert(user);
	}

	public User findByMailAddress(String email) {
		User user = repository.findByMailAddress(email);
		return user;
	}
}
