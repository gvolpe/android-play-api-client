package ar.com.gmvsoft.play.api.error;

import org.androidannotations.annotations.EBean;
import org.androidannotations.api.rest.RestErrorHandler;
import org.springframework.web.client.RestClientException;

@EBean
public class APIErrorHandler implements RestErrorHandler {

	@Override
	public void onRestClientExceptionThrown(RestClientException e) {
		// How to show a message here?
		//Toast.makeText(context, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
	}

}
