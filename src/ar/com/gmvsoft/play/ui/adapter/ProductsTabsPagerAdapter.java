package ar.com.gmvsoft.play.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import ar.com.gmvsoft.play.ui.fragment.AddNewFragment_;
import ar.com.gmvsoft.play.ui.fragment.FindAllFragment_;
import ar.com.gmvsoft.play.ui.fragment.FindByIdFragment_;

public class ProductsTabsPagerAdapter extends FragmentPagerAdapter {

	public ProductsTabsPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int index) {
		switch (index) {
		case 0:
			return new FindAllFragment_();
		case 1:
			return new FindByIdFragment_();
		case 2:
			return new AddNewFragment_();
		}
		return null;
	}

	@Override
	public int getCount() {
		return 3;
	}

}
