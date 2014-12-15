package ar.com.gmvsoft.play.hardware;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import ar.com.gmvsoft.play.Global;
import ar.com.gmvsoft.play.ui.DebugOptionsActivity_;

public class ShakeSensorListener implements SensorEventListener {

	private float accel; // acceleration apart from gravity
	private float accelCurrent; // current acceleration including gravity
	private float accelLast; // last acceleration including gravity
	private Context context;

	public ShakeSensorListener() {
		accel = 0.00f;
		accelCurrent = SensorManager.GRAVITY_EARTH;
		accelLast = SensorManager.GRAVITY_EARTH;
	}

	public ShakeSensorListener(Context context) {
		this.context = context;
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
	}

	@Override
	public void onSensorChanged(SensorEvent se) {
		float x = se.values[0];
		float y = se.values[1];
		float z = se.values[2];
		accelLast = accelCurrent;
		accelCurrent = (float) Math.sqrt((double) (x * x + y * y + z * z));
		float delta = accelCurrent - accelLast;
		accel = accel * 0.9f + delta; // perform low-cut filter

		if (deviceShaked()) {
			Intent intent = new Intent(context, DebugOptionsActivity_.class);
			context.startActivity(intent);
		}
	}

	public boolean deviceShaked() {
		return (accel > 12) && Global.instance().getShakeIt();
	}

}
