package com.uppercode.android.libraries.restful.generic.client;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpAuthentication;
import org.springframework.http.HttpBasicAuthentication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class RestClient {

	private RestTemplate rest;
	private HttpAuthentication authHeader;

	public RestClient() {
		rest = new RestTemplate();
		rest.getMessageConverters().add(new StringHttpMessageConverter());
	}

	public void setBasicAuthentication(String user, String pass) {
		authHeader = new HttpBasicAuthentication(user, pass);
	}

	public String get(String url) {
		return rest.exchange(url, HttpMethod.GET, new HttpEntity<String>(getHeaders()),
				String.class).getBody();
	}

	public String post(String url, String json) {
		return rest.exchange(url, HttpMethod.POST, new HttpEntity<String>(json, getHeaders()),
				String.class).getBody();
	}

	public String put(String url, String json) {
		return rest.exchange(url, HttpMethod.PUT, new HttpEntity<String>(json, getHeaders()),
				String.class).getBody();
	}

	private HttpHeaders getHeaders() {
		List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
		acceptableMediaTypes.add(MediaType.APPLICATION_JSON);

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(acceptableMediaTypes);
		headers.setContentType(MediaType.APPLICATION_JSON);

		headers.add("Accept", "application/json");

		if (authHeader != null) {
			headers.setAuthorization(authHeader);
		}

		return headers;
	}
}
