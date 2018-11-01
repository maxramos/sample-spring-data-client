package com.maxaramos.samplespringdataclient.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;
import com.maxaramos.samplespringdataclient.jsonview.BasicView;
import com.maxaramos.samplespringdataclient.jsonview.DepartmentView;

public class Department {

	@JsonView({ BasicView.class, DepartmentView.class })
	private Long id;

	@JsonView({ BasicView.class, DepartmentView.class })
	private String name;

	@JsonView({ BasicView.class, DepartmentView.class })
	private String headFullName;

	@JsonView({ DepartmentView.class })
	private List<String> memberFullNames;

	@JsonView({ BasicView.class, DepartmentView.class })
	private int departmentSize;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHeadFullName() {
		return headFullName;
	}

	public void setHeadFullName(String headFullName) {
		this.headFullName = headFullName;
	}

	public List<String> getMemberFullNames() {
		return memberFullNames;
	}

	public void setMemberFullNames(List<String> memberFullNames) {
		this.memberFullNames = memberFullNames;
	}

	public int getDepartmentSize() {
		return departmentSize;
	}

	public void setDepartmentSize(int departmentSize) {
		this.departmentSize = departmentSize;
	}

}
