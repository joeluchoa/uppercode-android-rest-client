package com.uppercode.android.libraries.restful.analiselogica;

public class AlCredentialsJsonBuilder {
	private String KEY_USERNAME = "UserName";
	private String KEY_PASSWORD = "Password";
	private String RESULT_FORMAT = "{\"%s\":\"%s\",\"%s\":\"%s\"}";

	private String mUsername;
	private String mPassword;

	public AlCredentialsJsonBuilder(String username, String password) {
		mUsername = username;
		mPassword = password;
	}

	public String build() {
		return String.format(RESULT_FORMAT, KEY_USERNAME, mUsername, KEY_PASSWORD, mPassword);
	}
}
