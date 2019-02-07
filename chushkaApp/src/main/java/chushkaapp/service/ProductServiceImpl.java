package chushkaapp.service;


import chushkaapp.domain.entities.Product;
import chushkaapp.domain.entities.Type;
import chushkaapp.domain.models.service.ProductServiceModel;
import chushkaapp.repository.ProductRepository;
import chushkaapp.util.ModelMapper;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    @Inject
    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void saveProduct(ProductServiceModel productServiceModel) {
        Product product = this.modelMapper.map(productServiceModel, Product.class);
        product.setType(Type.valueOf(productServiceModel.getType()));
        this.productRepository.save(product);
    }

    @Override
    public List<ProductServiceModel> findAllProducts() {
        return this.productRepository.findAll().stream().map(p->{
            ProductServiceModel productServiceModel = this.modelMapper.map(p, ProductServiceModel.class);
            productServiceModel.setType(p.getType().name());
            return productServiceModel;
        }).collect(Collectors.toList());
    }

    @Override
    public ProductServiceModel getProductServiceModelByName(String productName) {
        return this.modelMapper.map(this.productRepository.getProductByName(productName), ProductServiceModel.class);
    }
}
