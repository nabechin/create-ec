package com.example.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * ユーザ情報を受け取るFormクラス.
 * 
 * @author yuma.watanabe
 *
 */
public class UserForm {
	/**
	 * ユーザid
	 */
	private String id;
	/**
	 * ユーザ名
	 */
	@NotBlank(message = "名前を入力してください")
	private String name;
	/**
	 * ユーザのメールアドレス
	 */
	@Email(message = "形式が不正です")
	@NotBlank(message = "メールアドレスを入力してください")
	private String email;
	/**
	 * ユーザの郵便番号
	 */
	@Pattern(message = "入力形式に従ってください", regexp = "[0-9]{7}")
	@NotBlank(message = "郵便番号を入力してください")
	private String zipcode;
	/**
	 * ユーザのパスワード
	 */
	@NotBlank(message = "パスワードを入力してください")
	private String password;
	/**
	 * ユーザの住所
	 */
	@NotBlank(message = "住所を入力してください")
	private String address;
	/**
	 * ユーザの電話番号
	 */
	@Pattern(message = "入力形式に従ってください", regexp = "[0-9]{11}")
	@NotBlank(message = "電話番号を入力してください")
	private String telephone;
	
	@NotBlank(message="確認用パスワードを入力してください")
	private String passConfirm;

	public int getIntegerZipcode() {
		return Integer.parseInt(zipcode);
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	

	public String getPassConfirm() {
		return passConfirm;
	}


	public void setPassConfirm(String passConfirm) {
		this.passConfirm = passConfirm;
	}


	@Override
	public String toString() {
		return "UserForm [id=" + id + ", name=" + name + ", email=" + email + ", zipcode=" + zipcode + ", password="
				+ password + ", address=" + address + ", telephone=" + telephone + "]";
	}

}
