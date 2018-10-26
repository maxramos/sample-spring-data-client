package com.maxaramos.springdatatestclient.client.jpa;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.maxaramos.springdatatestclient.model.GenderType;
import com.maxaramos.springdatatestclient.model.User;

@Component
public class UserClient {

	@Value("${sdtc.ws.jpa.base-url}")
	private String jpaWsBaseUrl;

	@Autowired
	private Logger log;

	@Autowired
	private RestTemplate restTemplate;

	public List<User> getAllUser() {
		String response = null;

		try {
			response = restTemplate.getForObject(jpaWsBaseUrl + "/api/users", String.class);
		} catch (RestClientException e) {
			log.debug("Try again request: " + e.getMessage());
			response = restTemplate.getForObject(jpaWsBaseUrl + "/api/users", String.class);
		}

		JSONArray result = new JSONArray(response);
		List<User> users = new ArrayList<>();

		for (int i = 0; i < result.length(); i++) {
			JSONObject element = result.getJSONObject(i);
			User user = new User();
			user.setId(element.getLong("id"));
			user.setUsername(element.getString("username"));
			user.setRole(element.getString("role"));
			user.setEnabled(element.getBoolean("enabled"));
			user.setEmail(element.getString("email"));
			user.setFirstName(element.getString("firstName"));
			user.setLastName(element.getString("lastName"));
			user.setAge(element.getInt("age"));
			user.setBirthday(LocalDate.parse(element.getString("birthday")));
			user.setGender(GenderType.valueOf(element.getString("gender")));
			user.setContactNumber(element.getString("contactNumber"));
			users.add(user);
		}

		return users;
	}

	public User getUser(Long id) {
		return null;
	}

}
