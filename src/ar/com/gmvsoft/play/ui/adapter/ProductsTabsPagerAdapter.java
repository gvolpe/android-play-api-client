package ar.com.gmvsoft.play.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import ar.com.gmvsoft.play.ui.fragment.AddNewFragment;
import ar.com.gmvsoft.play.ui.fragment.FindAllFragment;
import ar.com.gmvsoft.play.ui.fragment.FindByIdFragment;

public class ProductsTabsPagerAdapter extends FragmentPagerAdapter {

	public ProductsTabsPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int index) {
		switch (index) {
		case 0:
			return new FindAllFragment();
		case 1:
			return new FindByIdFragment();
		case 2:
			return new AddNewFragment();
		}
		return null;
	}

	@Override
	public int getCount() {
		return 3;
	}

}
