package ar.com.gmvsoft.play.ui.fragment;

import java.util.ArrayList;
import java.util.List;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.rest.RestService;

import android.support.v4.app.Fragment;
import android.widget.EditText;
import android.widget.ListView;
import ar.com.gmvsoft.play.Global;
import ar.com.gmvsoft.play.R;
import ar.com.gmvsoft.play.api.ProductsResource;
import ar.com.gmvsoft.play.api.dto.ProductDTO;
import ar.com.gmvsoft.play.ui.adapter.ProductsListAdapter;

@EFragment(R.layout.fragment_find_by_id)
public class FindByIdFragment extends Fragment {

	@RestService
	ProductsResource productsResource;
	
	@ViewById
	EditText txtFindById;
	
	@ViewById
	ListView productByIdListView;
	
	@AfterInject
	void setUp() {
		productsResource.setRootUrl(Global.instance().getApiUrl());
	}
	
	@AfterViews
	void refreshView() {
		if (Global.instance().existProductById()) 
			updateProductView();
	}
	
	@Click
	void btnFindByIdClicked() {
		String id = txtFindById.getText().toString();
		findProductByIdInBackground(Long.valueOf(id));
	}
	
	@Background
	void findProductByIdInBackground(Long id) {
		ProductDTO product = productsResource.productById(id);
		Global.instance().setProductById(product);
		
		updateProductView();
	}
	
	@UiThread
	void updateProductView() {
		ProductDTO product = Global.instance().getProductById();
		List<ProductDTO> list = new ArrayList<ProductDTO>();
		list.add(product);
		ProductsListAdapter adapter = new ProductsListAdapter(this.getActivity(), list);
		productByIdListView.setAdapter(adapter);
	}
	
}
