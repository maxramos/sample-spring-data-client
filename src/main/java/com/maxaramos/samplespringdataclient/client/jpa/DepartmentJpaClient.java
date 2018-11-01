package com.maxaramos.samplespringdataclient.client.jpa;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.maxaramos.samplespringdataclient.model.Department;

@Component
public class DepartmentJpaClient {

	@Value("${ssdc.ws.jpa.base-url}")
	private String jpaWsBaseUrl;

	@Autowired
	private RestTemplate restTemplate;

	public List<Department> getAllDepartment() {
		String response = restTemplate.getForObject(jpaWsBaseUrl + "/api/departments", String.class);
		JSONArray departmentsResult = new JSONArray(response);
		List<Department> departments = new ArrayList<>();

		for (int i = 0; i < departmentsResult.length(); i++) {
			JSONObject element = departmentsResult.getJSONObject(i);
			Department department = new Department();
			department.setId(element.getLong("id"));
			department.setName(element.getString("name"));
			department.setHeadFullName(element.getString("headFullName"));
			department.setDepartmentSize(element.getInt("departmentSize"));
			departments.add(department);
		}

		return departments;
	}

	public Department getDepartment(Long id) {
		String response = restTemplate.getForObject(jpaWsBaseUrl + "/api/departments/" + id, String.class);
		JSONObject departmentResult = new JSONObject(response);
		JSONArray memberFullNamesResult = departmentResult.getJSONArray("memberFullNames");
		List<String> memberFullNames = new ArrayList<>();

		for (int i = 0; i < memberFullNamesResult.length(); i++) {
			memberFullNames.add(memberFullNamesResult.getString(i));
		}

		Department department = new Department();
		department.setId(departmentResult.getLong("id"));
		department.setName(departmentResult.getString("name"));
		department.setHeadFullName(departmentResult.getString("headFullName"));
		department.setMemberFullNames(memberFullNames);
		department.setDepartmentSize(departmentResult.getInt("departmentSize"));

		return department;
	}

}
