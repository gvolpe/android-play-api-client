package ar.com.gmvsoft.play.ui;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.SystemService;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.rest.RestService;

import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import ar.com.gmvsoft.play.Global;
import ar.com.gmvsoft.play.R;
import ar.com.gmvsoft.play.api.ProductsResource;
import ar.com.gmvsoft.play.hardware.ShakeSensorListener;
import ar.com.gmvsoft.play.ui.adapter.ProductsTabsPagerAdapter;

@EActivity(R.layout.activity_main)
@OptionsMenu(R.menu.main)
public class MainActivity extends FragmentActivity implements TabListener {

	private String[] tabs = { "Find All", "Find By Id", "Add New" };

	private ProductsTabsPagerAdapter tabsPagerAdapter;

	@ViewById
	ViewPager pager;

	@SystemService
	SensorManager sensorManager;

	@RestService
	ProductsResource productsResource;

	private ShakeSensorListener sensorListener;

	@AfterViews
	public void setUp() {
		Global.instance().setShakeIt(true);
		sensorListener = new ShakeSensorListener(this);
		registerSensorListener();
		createTabs();
		productsResource.setRootUrl(Global.instance().getApiUrl());
	}

	private void registerSensorListener() {
		sensorManager.registerListener(sensorListener, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
				SensorManager.SENSOR_DELAY_NORMAL);
	}

	private void createTabs() {
		tabsPagerAdapter = new ProductsTabsPagerAdapter(getSupportFragmentManager());
		pager.setAdapter(tabsPagerAdapter);
		getActionBar().setHomeButtonEnabled(false);
		getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		for (String tabName : tabs) {
			Tab tab = getActionBar().newTab().setText(tabName).setTabListener(this);
			getActionBar().addTab(tab);
		}

		createViewPagerListener();
	}

	@OptionsItem(R.id.action_debug_options)
	void debugOptionsSelected() {
		Intent intent = new Intent(this, DebugOptionsActivity_.class);
		startActivity(intent);
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		pager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
	}

	private void createViewPagerListener() {
		pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				getActionBar().setSelectedNavigationItem(position);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});
	}

	@Override
	protected void onResume() {
		super.onResume();
		registerSensorListener();
	}

	@Override
	protected void onPause() {
		super.onPause();
		sensorManager.unregisterListener(sensorListener);
	}

}
