package com.maxaramos.springdatatestclient.client;

import java.io.IOException;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

@Component
public class LoggingClientHttpRequestInterceptor implements ClientHttpRequestInterceptor {

	@Autowired
	private Logger log;

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
		log.debug("Header: " + request.getHeaders().toString());
		log.debug("Body: " + new String(body));
		return execution.execute(request, body);
	}

}
