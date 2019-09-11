package com.example.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.domain.LoginUser;
import com.example.domain.User;
import com.example.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserRepository repository;
	
	@Override
	public  UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = repository.findByMailAddress(email);
		if(user==null) {
			throw new UsernameNotFoundException("そのメールアドレスは登録されていません");
		}
		Collection<GrantedAuthority> authorityList = new ArrayList<>();
		authorityList.add(new SimpleGrantedAuthority("ROLE_USER")); // ユーザ権限付与
		if(user.getRole().equals("ADMIN")) {
		authorityList.add(new SimpleGrantedAuthority("ROLE_ADMIN")); // 管理者権限付与
	}
		return new LoginUser(user, authorityList);
		
	}

}
