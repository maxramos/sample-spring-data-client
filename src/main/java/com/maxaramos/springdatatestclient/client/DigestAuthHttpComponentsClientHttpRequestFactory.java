package com.maxaramos.springdatatestclient.client;

import java.io.IOException;
import java.net.URI;

import javax.servlet.http.HttpServletResponse;

import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.auth.AUTH;
import org.apache.http.auth.MalformedChallengeException;
import org.apache.http.client.AuthCache;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.auth.DigestScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.message.BasicHttpRequest;
import org.apache.http.protocol.HttpContext;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

public class DigestAuthHttpComponentsClientHttpRequestFactory extends HttpComponentsClientHttpRequestFactory {

	public DigestAuthHttpComponentsClientHttpRequestFactory(HttpClient httpClient) {
		super(httpClient);
	}

	@Override
	protected HttpContext createHttpContext(HttpMethod httpMethod, URI uri) {
		HttpHost httpHost = new HttpHost(uri.getHost(), uri.getPort(), uri.getScheme());
		HttpRequest httpRequest = new BasicHttpRequest(httpMethod.toString(), uri.toString());
		ResponseHandler<Header> responseHandler = response -> {
			if (response.getStatusLine().getStatusCode() == HttpServletResponse.SC_UNAUTHORIZED) {
				return response.getFirstHeader(AUTH.WWW_AUTH);
			}

			return null;
		};

		try {
			Header challengeHeader = getHttpClient().execute(httpHost, httpRequest, responseHandler);
			DigestScheme digestScheme = new DigestScheme();

			if (challengeHeader != null) {
				// Process initial challenge to get the required realm name and nonce for the succeeding request.
				digestScheme.processChallenge(challengeHeader);
			}

			AuthCache authCache = new BasicAuthCache();
			authCache.put(httpHost, digestScheme);

			HttpClientContext httpContext = HttpClientContext.create();
			httpContext.setAuthCache(authCache);
			return httpContext;
		} catch (IOException | MalformedChallengeException e) {
			throw new RuntimeException(e);
		}
	}

}
