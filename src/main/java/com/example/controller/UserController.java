package com.example.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.User;
import com.example.form.UserForm;
import com.example.service.UserService;

/**
 * ユーザ情報を操作するコントローラクラス.
 * 
 * @author yuma.watanabe
 *
 */
@Controller
@RequestMapping("/")
public class UserController {

	@ModelAttribute
	public UserForm setUpForm() {
		return new UserForm();
	}

	@Autowired
	private UserService service;

	/**
	 * @param form ユーザフォーム
	 * @param result　エラーメッセージ格納オブジェクト
	 * 
	 */
	@RequestMapping("/insert")
	public String insert(@Validated UserForm form, BindingResult result) {
		User user = new User();
		BeanUtils.copyProperties(form, user);
		service.insert(user);
	}

}
