package ar.com.gmvsoft.play.ui;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import ar.com.gmvsoft.play.Global;
import ar.com.gmvsoft.play.R;

@EActivity(R.layout.activity_debug_options)
public class DebugOptionsActivity extends Activity {

	private ArrayAdapter<String> urlAdapter;

	@ViewById
	EditText txtCustomUrl;
	
	@ViewById
	RadioButton radioUrlSubtitle;
	
	@ViewById
	RadioButton radioCustomUrl;
	
	@ViewById
	Spinner spinnerUrls;

	@AfterViews
	void setUp() {
		Global.instance().setShakeIt(false);
		createSpinnerUrls();
		
		radioUrlSubtitle.setChecked(true);
		txtCustomUrl.setEnabled(false);
		
		createRadioButtonListeners();
	}

	@Click
	void btnOkClicked() {
		String selectedUrl = txtCustomUrl.getText().toString().trim();
		Global.instance().setApiUrl(selectedUrl);
		
		if (selectedUrl == null || selectedUrl.isEmpty()) {
			selectedUrl = (String) spinnerUrls.getSelectedItem();
			Global.instance().setApiUrlAndUpdateList(selectedUrl);
		}
		
		Intent main = new Intent(this, MainActivity_.class);
		startActivity(main);
	}

	private void createSpinnerUrls() {
		urlAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Global.instance()
				.getUrlList());
		urlAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerUrls.setAdapter(urlAdapter);
	}

	private void createRadioButtonListeners() {
		radioCustomUrl.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				radioUrlSubtitle.setChecked(false);
				spinnerUrls.setEnabled(false);
				txtCustomUrl.setEnabled(true);
			}
			
		});
		
		radioUrlSubtitle.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				radioCustomUrl.setChecked(false);
				txtCustomUrl.setEnabled(false);
				spinnerUrls.setEnabled(true);
			}
			
		});
	}
	
}
