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
	/**
	 * ユーザ権限
	 */
	private String role;

	public User() {
	}

	public User(Integer id, String name, String email, String password, String zipcode, String address,
			String telephone, String role) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.zipcode = zipcode;
		this.address = address;
		this.telephone = telephone;
		this.role = role;
	}

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

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
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

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", zipcode=" + zipcode + ", address="
				+ address + ", telephone=" + telephone + "]";
	}

}
