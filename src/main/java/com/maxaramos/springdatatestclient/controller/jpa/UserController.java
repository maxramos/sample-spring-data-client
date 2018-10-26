package com.maxaramos.springdatatestclient.controller.jpa;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.maxaramos.springdatatestclient.client.jpa.UserClient;
import com.maxaramos.springdatatestclient.model.User;

@RestController
@RequestMapping("/jpa/users")
public class UserController {

	@Autowired
	private Logger log;

	@Autowired
	private UserClient userClient;

	@GetMapping("/getAll")
	public List<User> getAll() {
		log.info("getAll");
		return userClient.getAllUser();
	}

	@GetMapping("/get")
	public User get(@RequestParam("id") Long id) {
		log.info("get: " + id);
		return userClient.getUser(id);
	}

}
