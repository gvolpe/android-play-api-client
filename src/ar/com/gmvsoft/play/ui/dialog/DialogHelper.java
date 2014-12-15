package ar.com.gmvsoft.play.ui.dialog;

import android.app.ProgressDialog;
import android.content.Context;

public class DialogHelper {

	public static ProgressDialog createProgressBar(Context context) {
		ProgressDialog progress = new ProgressDialog(context);
		progress.setTitle("Request Status");
		progress.setTitle("Loading...");
		progress.setCancelable(true);
		return progress;
	}
	
}
