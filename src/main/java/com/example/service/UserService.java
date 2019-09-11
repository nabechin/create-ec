package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.User;
import com.example.repository.UserRepository;

/**
 * ユーザ情報を操作するサービスクラス.
 * @author yuma.watanabe 
 */
@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository repository;

	/**
	 * ユーザ情報を追加する.
	 * 
	 * @param user ユーザ情報
	 */
	public void insert(User user) {
		repository.insert(user);

	}

}
