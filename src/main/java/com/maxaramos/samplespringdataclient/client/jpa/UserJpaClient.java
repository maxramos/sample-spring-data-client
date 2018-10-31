package com.maxaramos.samplespringdataclient.client.jpa;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.maxaramos.samplespringdataclient.model.Address;
import com.maxaramos.samplespringdataclient.model.GenderType;
import com.maxaramos.samplespringdataclient.model.User;

@Component
public class UserJpaClient {

	@Value("${ssdc.ws.jpa.base-url}")
	private String jpaWsBaseUrl;

	@Autowired
	private RestTemplate restTemplate;

	public List<User> getAllUser() {
		String response = restTemplate.getForObject(jpaWsBaseUrl + "/api/users", String.class);
		JSONArray usersResult = new JSONArray(response);
		List<User> users = new ArrayList<>();

		for (int i = 0; i < usersResult.length(); i++) {
			JSONObject element = usersResult.getJSONObject(i);
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
		String response = restTemplate.getForObject(jpaWsBaseUrl + "/api/users?id=" + id, String.class);
		JSONObject userResult = new JSONObject(response);
		JSONObject addressResult = userResult.getJSONObject("address");

		Address address = new Address();
		address.setId(addressResult.getLong("id"));
		address.setAddress1(addressResult.getString("address1"));
		address.setAddress2(addressResult.getString("address2"));
		address.setCity(addressResult.getString("city"));
		address.setState(addressResult.getString("state"));
		address.setCountry(addressResult.getString("country"));
		address.setZipCode(addressResult.getString("zipCode"));

		User user = new User();
		user.setId(userResult.getLong("id"));
		user.setUsername(userResult.getString("username"));
		user.setPassword(userResult.getString("password"));
		user.setRole(userResult.getString("role"));
		user.setEnabled(userResult.getBoolean("enabled"));
		user.setEmail(userResult.getString("email"));
		user.setFirstName(userResult.getString("firstName"));
		user.setLastName(userResult.getString("lastName"));
		user.setAge(userResult.getInt("age"));
		user.setBirthday(LocalDate.parse(userResult.getString("birthday")));
		user.setGender(GenderType.valueOf(userResult.getString("gender")));
		user.setContactNumber(userResult.getString("contactNumber"));
		user.setAddress(address);
		user.setSupervisorFullName(userResult.getString("supervisorFullName"));
		user.setDepartmentName(userResult.getString("departmentName"));

		return user;
	}

	public User add(User user) {
		Address address = user.getAddress();

		JSONObject addressBody = new JSONObject();
		addressBody.put("address1", address.getAddress1());
		addressBody.put("address2", address.getAddress2());
		addressBody.put("city", address.getCity());
		addressBody.put("state", address.getState());
		addressBody.put("country", address.getCountry());
		addressBody.put("zipCode", address.getZipCode());

		JSONObject userBody = new JSONObject();
		userBody.put("username", user.getUsername());
		userBody.put("rawPassword", user.getRawPassword());
		userBody.put("email", user.getEmail());
		userBody.put("firstName", user.getFirstName());
		userBody.put("lastName", user.getLastName());
		userBody.put("age", user.getAge());
		userBody.put("birthday", user.getBirthday());
		userBody.put("gender", user.getGender());
		userBody.put("contactNumber", user.getContactNumber());
		userBody.put("address", addressBody);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<>(userBody.toString(), headers);
		String response = restTemplate.postForObject(jpaWsBaseUrl + "/api/users", request, String.class);
		JSONObject userResult = new JSONObject(response);
		JSONObject addressResult = userResult.getJSONObject("address");

		Address addedAddress = new Address();
		addedAddress.setId(addressResult.getLong("id"));
		addedAddress.setAddress1(addressResult.getString("address1"));
		addedAddress.setAddress2(addressResult.getString("address2"));
		addedAddress.setCity(addressResult.getString("city"));
		addedAddress.setState(addressResult.getString("state"));
		addedAddress.setCountry(addressResult.getString("country"));
		addedAddress.setZipCode(addressResult.getString("zipCode"));

		User addedUser = new User();
		addedUser.setId(userResult.getLong("id"));
		addedUser.setUsername(userResult.getString("username"));
		addedUser.setPassword(userResult.getString("password"));
		addedUser.setRole(userResult.getString("role"));
		addedUser.setEnabled(userResult.getBoolean("enabled"));
		addedUser.setEmail(userResult.getString("email"));
		addedUser.setFirstName(userResult.getString("firstName"));
		addedUser.setLastName(userResult.getString("lastName"));
		addedUser.setAge(userResult.getInt("age"));
		addedUser.setBirthday(LocalDate.parse(userResult.getString("birthday")));
		addedUser.setGender(GenderType.valueOf(userResult.getString("gender")));
		addedUser.setContactNumber(userResult.getString("contactNumber"));
		addedUser.setAddress(addedAddress);
		addedUser.setSupervisorFullName(userResult.optString("supervisorFullName", null));
		addedUser.setDepartmentName(userResult.optString("departmentName", null));

		return addedUser;
	}

}
