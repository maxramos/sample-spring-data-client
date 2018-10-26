package com.maxaramos.springdatatestclient.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonView;
import com.maxaramos.springdatatestclient.jsonview.BasicView;
import com.maxaramos.springdatatestclient.jsonview.UserView;

public class User {

	@JsonView({ BasicView.class, UserView.class })
	private Long id;

	@JsonView({ BasicView.class, UserView.class })
	private String username;

	@JsonView(UserView.class)
	private String password;

	private String rawPassword;

	@JsonView({ BasicView.class, UserView.class })
	private String role;

	@JsonView({ BasicView.class, UserView.class })
	private Boolean enabled;

	@JsonView({ BasicView.class, UserView.class })
	private String email;

	@JsonView({ BasicView.class, UserView.class })
	private String firstName;

	@JsonView({ BasicView.class, UserView.class })
	private String lastName;

	@JsonView({ BasicView.class, UserView.class })
	private Integer age;

	@JsonView({ BasicView.class, UserView.class })
	private LocalDate birthday;

	@JsonView({ BasicView.class, UserView.class })
	private GenderType gender;

	@JsonView({ BasicView.class, UserView.class })
	private String contactNumber;

	@JsonView(UserView.class)
	private Address address;

	@JsonView(UserView.class)
	private String supervisorFullName;

	@JsonView(UserView.class)
	private String departmentName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRawPassword() {
		return rawPassword;
	}

	public void setRawPassword(String rawPassword) {
		this.rawPassword = rawPassword;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	public GenderType getGender() {
		return gender;
	}

	public void setGender(GenderType gender) {
		this.gender = gender;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getSupervisorFullName() {
		return supervisorFullName;
	}

	public void setSupervisorFullName(String supervisorFullName) {
		this.supervisorFullName = supervisorFullName;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

}
