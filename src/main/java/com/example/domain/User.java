package com.example.domain;

/**
 * ユーザ情報を表すドメイン
 * 
 * @author yuma.watanabe
 *
 */
public class User {

	/**
	 * ユーザid
	 */
	private Integer id;
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
	private Integer zipcode;
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
	private Integer telephone;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public Integer getZipcode() {
		return zipcode;
	}

	public void setZipcode(Integer zipcode) {
		this.zipcode = zipcode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getTelephone() {
		return telephone;
	}

	public void setTelephone(Integer telephone) {
		this.telephone = telephone;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", zipcode=" + zipcode + ", address="
				+ address + ", telephone=" + telephone + "]";
	}
	

}
