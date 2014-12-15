package ar.com.gmvsoft.play.api;

import java.util.List;

import org.androidannotations.annotations.rest.Accept;
import org.androidannotations.annotations.rest.Get;
import org.androidannotations.annotations.rest.Post;
import org.androidannotations.annotations.rest.Rest;
import org.androidannotations.api.rest.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import ar.com.gmvsoft.play.api.dto.ProductDTO;

@Rest(converters = { MappingJackson2HttpMessageConverter.class })
@Accept(MediaType.APPLICATION_JSON)
public interface ProductsResource {

	@Get("/products")
	List<ProductDTO> products();

	@Get("/product/{id}")
	ProductDTO productById(Long id);

	@Post("/products")
	void addProduct(ProductDTO product);

	String getRootUrl();

	void setRootUrl(String url);

}
