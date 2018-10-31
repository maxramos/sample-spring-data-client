package com.maxaramos.samplespringdataclient.controller.jpa;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.maxaramos.samplespringdataclient.client.jpa.UserClient;
import com.maxaramos.samplespringdataclient.jsonview.BasicView;
import com.maxaramos.samplespringdataclient.jsonview.UserView;
import com.maxaramos.samplespringdataclient.model.User;

@RestController
@RequestMapping("/jpa/users")
public class UserController {

	@Autowired
	private Logger log;

	@Autowired
	private UserClient userClient;

	@GetMapping("/getAll")
	@JsonView(BasicView.class)
	public List<User> getAll() {
		log.info("getAll");
		return userClient.getAllUser();
	}

	@GetMapping("/get")
	@JsonView(UserView.class)
	public User get(@RequestParam("id") Long id) {
		log.info("get: " + id);
		return userClient.getUser(id);
	}

	@PostMapping("/add")
	@JsonView(UserView.class)
	public User add(@RequestBody User user) {
		log.info("add");
		return userClient.add(user);
	}

}
