package com.maxaramos.springdatatestclient.model;

import java.util.List;

public class Department {

	private Long id;
	private String name;
	private User head;
	private List<User> members;

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User getHead() {
		return head;
	}

	public void setHead(User head) {
		this.head = head;
	}

	public List<User> getMembers() {
		return members;
	}

}
