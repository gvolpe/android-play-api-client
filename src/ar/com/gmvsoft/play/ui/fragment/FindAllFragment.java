package ar.com.gmvsoft.play.ui.fragment;

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
import android.widget.ListView;
import ar.com.gmvsoft.play.Global;
import ar.com.gmvsoft.play.R;
import ar.com.gmvsoft.play.api.ProductsResource;
import ar.com.gmvsoft.play.api.dto.ProductDTO;
import ar.com.gmvsoft.play.ui.adapter.ProductsListAdapter;

@EFragment(R.layout.fragment_find_all)
public class FindAllFragment extends Fragment {

	@RestService
	ProductsResource productsResource;
	
	@ViewById
	ListView productsListView;
	
	@AfterInject
	void setUp() {
		productsResource.setRootUrl(Global.instance().getApiUrl());
	}
	
	@AfterViews
	void refreshView() {
		if (Global.instance().existProducts())
			updateProductsList();
	}
	
	@Click
	void btnFindAllClicked() {
		findProductsInBackground();
	}

	@Background
	void findProductsInBackground() {
		List<ProductDTO> products = Global.instance().getProducts();

		if (products == null) {
			products = productsResource.products();
			Global.instance().setProducts(products);
		}

		updateProductsList();
	}

	@UiThread
	void updateProductsList() {
		List<ProductDTO> products = Global.instance().getProducts();
		ProductsListAdapter adapter = new ProductsListAdapter(this.getActivity(), products);
		productsListView.setAdapter(adapter);
	}
	
}
