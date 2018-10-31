package com.maxaramos.springdatatestclient.client;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ClientConfig {

	@Value("${sdtc.ws.username}")
	private String username;

	@Value("${sdtc.ws.password}")
	private String password;

	@Autowired
	private LoggingClientHttpRequestInterceptor loggingClientHttpRequestInterceptor;

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		Credentials credentials = new UsernamePasswordCredentials(username, password);
		CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY, credentials);

		HttpClient httpClient = HttpClients
				.custom()
				.setDefaultCredentialsProvider(credentialsProvider)
				.build();

		return builder
//				.basicAuthentication(username, password)
				.requestFactory(() -> new HttpComponentsClientHttpRequestFactory(httpClient))
				.interceptors(loggingClientHttpRequestInterceptor)
		 		.build();
	}

}
