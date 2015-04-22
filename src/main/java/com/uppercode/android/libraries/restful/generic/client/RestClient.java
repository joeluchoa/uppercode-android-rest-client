package com.uppercode.android.libraries.restful.generic.client;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpBasicAuthentication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class RestClient {

	private static final int TIMEOUT = 15 * 60 * 1000;

	private RestTemplate mRest;
	private HttpHeaders mHeaders;

	public RestClient() {
		mRest = new RestTemplate();
		HttpComponentsClientHttpRequestFactory f = new HttpComponentsClientHttpRequestFactory();
		f.setConnectTimeout(TIMEOUT);
		f.setReadTimeout(TIMEOUT);
		mRest.setRequestFactory(f);
		mRest.getMessageConverters().add(new StringHttpMessageConverter());

		initializeHeaders();
	}

	public void setBasicAuthentication(String user, String pass) {
		mHeaders.setAuthorization(new HttpBasicAuthentication(user, pass));
	}

	public String get(String url) {
		return mRest.exchange(url, HttpMethod.GET, entity(null), String.class)
				.getBody();
	}

	public String post(String url, String json) {
		return mRest.exchange(url, HttpMethod.POST, entity(json), String.class)
				.getBody();
	}

	public String put(String url, String json) {
		return mRest.exchange(url, HttpMethod.PUT, entity(json), String.class)
				.getBody();
	}

	public String delete(String url) {
		return mRest.exchange(url, HttpMethod.DELETE, entity(null),
				String.class).getBody();
	}

	private HttpEntity<String> entity(String json) {
		if (json == null) {
			return new HttpEntity<String>(mHeaders);
		}
		return new HttpEntity<String>(json, mHeaders);
	}

	private void initializeHeaders() {
		List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
		acceptableMediaTypes.add(MediaType.APPLICATION_JSON);

		mHeaders = new HttpHeaders();
		mHeaders.setAccept(acceptableMediaTypes);
		mHeaders.setContentType(MediaType.APPLICATION_JSON);
		// mHeaders.add("Accept-Encoding", "gzip");
	}
}
