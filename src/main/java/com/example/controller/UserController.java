package com.example.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService service;

	@ModelAttribute
	public UserForm setUpForm() {
		return new UserForm();
	}

	@RequestMapping("")
	public String login() {
		return "login";
	}

	@RequestMapping("toLogin")
	public String toLogin(Model model, @RequestParam(required = false) String error) {
		System.err.println("login error:" + error);
		if (error != null) {
			System.err.println("login failed");
			model.addAttribute("errorMessage", "メールアドレスまたはパスワードが不正です");
		}
		return "login.html";
	}

	/**
	 * 登録フォームの表示.
	 * 
	 * @return 登録フォーム画面
	 */
	@RequestMapping("/register")
	public String register() {
		return "register_user";
	}

	/**
	 * @param form   ユーザフォーム
	 * @param result エラーメッセージ格納オブジェクト
	 * 
	 */
	@RequestMapping("/insert")
	public String insert(@Validated UserForm form, BindingResult result, String pass) {
		if (service.findByMailAddress(form.getEmail()) != null) {
			result.rejectValue("email", "", "Eメールはすでに登録されています");
		}
		if (!(form.getPassword().equals(form.getPassConfirm()))) {
			result.rejectValue("passConfirm", "", "パスワードと確認用パスワードが異なります");
		}
		if (result.hasErrors()) {
			return register();
		}
		User user = new User();
		BeanUtils.copyProperties(form, user);
		service.insert(user);

		return "redirect:/user/login";

	}

}
