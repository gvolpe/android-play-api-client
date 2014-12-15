package ar.com.gmvsoft.play.ui.fragment;

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
import android.widget.ListView;
import android.widget.Toast;
import ar.com.gmvsoft.play.Global;
import ar.com.gmvsoft.play.R;
import ar.com.gmvsoft.play.api.ProductsResource;
import ar.com.gmvsoft.play.api.dto.ProductDTO;
import ar.com.gmvsoft.play.api.error.APIErrorHandler;
import ar.com.gmvsoft.play.ui.adapter.ProductsListAdapter;
import ar.com.gmvsoft.play.ui.dialog.DialogHelper;

@EFragment(R.layout.fragment_find_all)
public class FindAllFragment extends Fragment {

	private ProgressDialog progress;
	
	@RestService
	ProductsResource productsResource;
	
	@Bean
	APIErrorHandler restErrorHandler;
	
	@ViewById
	ListView productsListView;
	
	@AfterInject
	void setUp() {
		productsResource.setRootUrl(Global.instance().getApiUrl());
		productsResource.setRestErrorHandler(restErrorHandler);
		progress = DialogHelper.createProgressBar(getActivity());
	}
	
	@AfterViews
	void refreshView() {
		if (Global.instance().existProducts())
			updateProductsList();
	}
	
	@Click
	void btnFindAllClicked() {
		progress.show();
		findProductsInBackground();
	}

	@Background
	void findProductsInBackground() {
		List<ProductDTO> products = productsResource.products();
		Global.instance().setProducts(products);

		progress.dismiss();
		updateProductsList();
	}

	@UiThread
	void updateProductsList() {
		List<ProductDTO> products = Global.instance().getProducts();
		if (products != null) {
			ProductsListAdapter adapter = new ProductsListAdapter(this.getActivity(), products);
			productsListView.setAdapter(adapter);
		} else {
			Toast.makeText(getActivity(), "Error finding Products.", Toast.LENGTH_SHORT).show();
		}
	}
	
}
