package ar.com.gmvsoft.play.ui;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import android.app.Activity;
import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import ar.com.gmvsoft.play.Global;
import ar.com.gmvsoft.play.R;

@EActivity(R.layout.activity_debug_options)
public class DebugOptionsActivity extends Activity {

	private ArrayAdapter<String> urlAdapter;

	@ViewById
	Spinner spinnerUrls;

	@AfterViews
	void setUp() {
		Global.instance().setShakeIt(false);
		createSpinnerUrls();
	}

	@Click
	void btnOkClicked() {
		String selectedUrl = (String) spinnerUrls.getSelectedItem();
		Global.instance().setApiUrl(selectedUrl);
		Intent main = new Intent(this, MainActivity_.class);
		startActivity(main);
	}

	private void createSpinnerUrls() {
		urlAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Global.instance()
				.getUrlList());
		urlAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerUrls.setAdapter(urlAdapter);
	}

}
