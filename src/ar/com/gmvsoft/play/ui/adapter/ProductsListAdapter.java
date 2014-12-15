package ar.com.gmvsoft.play.ui.adapter;

import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import ar.com.gmvsoft.play.R;
import ar.com.gmvsoft.play.api.dto.ProductDTO;

public class ProductsListAdapter extends ArrayAdapter<ProductDTO> {

	private Context context;
	private List<ProductDTO> products;

	public ProductsListAdapter(Context context, List<ProductDTO> products) {
		super(context, R.layout.products_lists_row, products);
		this.context = context;
		this.products = products;
	}
	
	@SuppressLint("ViewHolder")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ProductDTO product = products.get(position);

		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.products_lists_row, parent, false);

		ImageView placeIcon = (ImageView) rowView.findViewById(R.id.productRowIcon);
		TextView productNameView = (TextView) rowView.findViewById(R.id.firstLine);
		TextView productDescriptionView = (TextView) rowView.findViewById(R.id.secondLine);
		TextView productPriceView = (TextView) rowView.findViewById(R.id.price);

		placeIcon.setImageResource(R.drawable.ic_launcher);
		productDescriptionView.setText("Play! Framework API");
		String productName = product.getName();
		if (productName.length() > 26) {
			productName = productName.substring(0, 22);
			productName += "...";
		}
		productNameView.setText(productName);
		if (product.getPrice() != null)
			productPriceView.setText("$ " + product.getPrice());

		return rowView;
	}

}
