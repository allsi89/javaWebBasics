package chushkaapp.service;

import chushkaapp.domain.models.service.ProductServiceModel;

import java.util.List;

public interface ProductService {
    void saveProduct(ProductServiceModel productServiceModel);

    List<ProductServiceModel> findAllProducts();

    ProductServiceModel getProductServiceModelByName(String productName);
}
