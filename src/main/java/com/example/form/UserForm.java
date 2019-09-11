package com.example.form;

/**
 * ユーザ情報を受け取るFormクラス.
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
	private String name;
	/**
	 * ユーザのメールアドレス
	 */
	private String email;
	/**
	 * ユーザの郵便番号
	 */
	private String zipcode;
	/**
	 * ユーザのパスワード
	 */
	private String password;
	/**
	 * ユーザの住所
	 */
	private String address;
	/**
	 * ユーザの電話番号
	 */
	private String telephone;

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

}
