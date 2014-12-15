package ar.com.gmvsoft.play.ui.fragment;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.rest.RestService;

import android.support.v4.app.Fragment;
import ar.com.gmvsoft.play.R;
import ar.com.gmvsoft.play.api.ProductsResource;

@EFragment(R.layout.fragment_add_new)
public class AddNewFragment extends Fragment {

	@RestService
	ProductsResource productsResource;
	
}
