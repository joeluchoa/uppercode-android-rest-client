package com.uppercode.android.libraries.restful.analiselogica;

import com.uppercode.android.libraries.restful.generic.client.RestClient;

public class AlCloudClient {

	private String mUser;
	private String mPass;
	private String mCredentialsJson;

	private String mHost;
	private String mLoginPath;
	private String mGetAllPath;
	private RestClient mRestClient;

	public AlCloudClient(String host, String loginPath, String getAllPath) {
		setHost(host);
		mLoginPath = loginPath;
		mGetAllPath = getAllPath;
		mRestClient = new RestClient();
	}

	private void setHost(String host) {
		mHost = host;

		char lastCharacter = mHost.charAt(mHost.length() - 1);
		if (lastCharacter == '/') {
			mHost.substring(0, mHost.length() - 1);
		}
	}

	public void setCredentials(String user, String pass) {
		this.mUser = user;
		this.mPass = pass;
		mCredentialsJson = new AlCredentialsJsonBuilder(user, pass).build();
		mRestClient.setBasicAuthentication(user, pass);
	}

	public boolean checkCredentials() {
		if (mUser != null && mPass != null && !mUser.equals("") && !mPass.equals("")) {
			String result = mRestClient.post(url(mLoginPath), mCredentialsJson);
			return Boolean.valueOf(result);
		}
		return false;
	}

	public String getAll() {
		if (checkCredentials()) {
			return mRestClient.get(url(mGetAllPath));
		}
		return null;
	}

	public RestClient getRestClient() {
		return mRestClient;
	}

	public String url(String relative) {
		if (relative.length() > 0 && relative.charAt(0) != '/') {
			relative = "/" + relative;
		}
		return mHost + relative;
	}
}
