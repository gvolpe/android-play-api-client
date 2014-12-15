package ar.com.gmvsoft.play;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Global {

	private static Global instance;

	private List<String> urlList = new LinkedList<String>(Arrays.asList("http://localhost:9000",
			"http://10.155.8.90:9000", "https://products-api.herokuapp.com"));
	private String apiUrl = "http://localhost:9000";
	private Boolean shakeIt = Boolean.FALSE;

	private Global() {
	}

	public String getApiUrl() {
		return apiUrl;
	}

	public void setApiUrl(String apiUrl) {
		this.apiUrl = apiUrl;
		int selectedIndex = urlList.indexOf(apiUrl);
		urlList.remove(selectedIndex);
		urlList.add(0, apiUrl);
	}

	public static synchronized Global instance() {
		if (instance == null) {
			instance = new Global();
		}
		return instance;
	}

	public List<String> getUrlList() {
		return urlList;
	}

	public Boolean getShakeIt() {
		return shakeIt;
	}

	public void setShakeIt(Boolean shakeIt) {
		this.shakeIt = shakeIt;
	}

}
