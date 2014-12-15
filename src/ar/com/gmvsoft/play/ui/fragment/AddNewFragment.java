package ar.com.gmvsoft.play.ui.fragment;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.rest.RestService;

import android.support.v4.app.Fragment;
import android.widget.EditText;
import android.widget.Toast;
import ar.com.gmvsoft.play.Global;
import ar.com.gmvsoft.play.R;
import ar.com.gmvsoft.play.api.ProductsResource;
import ar.com.gmvsoft.play.api.dto.BaseProductDTO;

@EFragment(R.layout.fragment_add_new)
public class AddNewFragment extends Fragment {

	@RestService
	ProductsResource productsResource;
	
	@ViewById
	EditText txtName;
	
	@ViewById
	EditText txtPrice;
	
	@AfterInject
	void setUp() {
		productsResource.setRootUrl(Global.instance().getApiUrl());
	}
	
	@Click
	void btnAddNewClicked() {
		saveProductInBackground();
	}
	
	@Background
	void saveProductInBackground() {
		String name = txtName.getText().toString();
		Double price = Double.parseDouble(txtPrice.getText().toString());
		BaseProductDTO product = new BaseProductDTO(name, price);
		productsResource.addProduct(product);
		showInfo();
	}
	
	@UiThread
	void showInfo() {
		Toast.makeText(getActivity(), "Product saved successfully!", Toast.LENGTH_SHORT).show();
	}
	
}
