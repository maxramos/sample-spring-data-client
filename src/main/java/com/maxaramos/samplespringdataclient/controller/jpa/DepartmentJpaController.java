package com.maxaramos.samplespringdataclient.controller.jpa;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.maxaramos.samplespringdataclient.client.jpa.DepartmentJpaClient;
import com.maxaramos.samplespringdataclient.jsonview.BasicView;
import com.maxaramos.samplespringdataclient.jsonview.DepartmentView;
import com.maxaramos.samplespringdataclient.model.Department;

@RestController
@RequestMapping("/jpa/departments")
public class DepartmentJpaController {

	@Autowired
	private Logger log;

	@Autowired
	private DepartmentJpaClient departmentJpaClient;

	@GetMapping("/getAll")
	@JsonView(BasicView.class)
	public List<Department> getAll() {
		log.info("getAll");
		return departmentJpaClient.getAllDepartment();
	}

	@GetMapping("/get")
	@JsonView(DepartmentView.class)
	public Department get(@RequestParam("id") Long id) {
		log.info("get: " + id);
		return departmentJpaClient.getDepartment(id);
	}

}
