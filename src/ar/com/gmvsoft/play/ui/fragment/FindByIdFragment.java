package ar.com.gmvsoft.play.ui.fragment;

import java.util.ArrayList;
import java.util.List;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.rest.RestService;

import android.app.ProgressDialog;
import android.support.v4.app.Fragment;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import ar.com.gmvsoft.play.Global;
import ar.com.gmvsoft.play.R;
import ar.com.gmvsoft.play.api.ProductsResource;
import ar.com.gmvsoft.play.api.dto.ProductDTO;
import ar.com.gmvsoft.play.api.error.APIErrorHandler;
import ar.com.gmvsoft.play.ui.adapter.ProductsListAdapter;
import ar.com.gmvsoft.play.ui.dialog.DialogHelper;

@EFragment(R.layout.fragment_find_by_id)
public class FindByIdFragment extends Fragment {

	private ProgressDialog progress;
	
	@RestService
	ProductsResource productsResource;
	
	@Bean
	APIErrorHandler restErrorHandler;
	
	@ViewById
	EditText txtFindById;
	
	@ViewById
	ListView productByIdListView;
	
	@AfterInject
	void setUp() {
		productsResource.setRootUrl(Global.instance().getApiUrl());
		productsResource.setRestErrorHandler(restErrorHandler);
		progress = DialogHelper.createProgressBar(getActivity());
	}

	@AfterViews
	void refreshView() {
		if (Global.instance().existProductById()) 
			updateProductView();
	}
	
	@Click
	void btnFindByIdClicked() {
		String id = txtFindById.getText().toString();
		progress.show();
		findProductByIdInBackground(Long.valueOf(id));
	}
	
	@Background
	void findProductByIdInBackground(Long id) {
		ProductDTO product = productsResource.productById(id);
		Global.instance().setProductById(product);
		
		progress.dismiss();
		updateProductView();
	}
	
	@UiThread
	void updateProductView() {
		ProductDTO product = Global.instance().getProductById();
		if (product != null) {
			List<ProductDTO> list = new ArrayList<ProductDTO>();
			list.add(product);
			ProductsListAdapter adapter = new ProductsListAdapter(this.getActivity(), list);
			productByIdListView.setAdapter(adapter);
		} else {
			Toast.makeText(getActivity(), "Product not found.", Toast.LENGTH_SHORT).show();
		}
	}
	
}
