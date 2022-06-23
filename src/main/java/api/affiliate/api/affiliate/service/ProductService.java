package api.affiliate.api.affiliate.service;

import api.affiliate.api.affiliate.entity.ProductTable;
import api.affiliate.api.affiliate.exception.BaseException;
import api.affiliate.api.affiliate.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    public final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public ProductTable createProduct(String productName, String productDetail, String productPrice) {
        ProductTable product = new ProductTable();
        product.setProductName(productName);
        product.setProductDetail(productDetail);
        product.setProductPrice(productPrice);

        return productRepository.save(product);
    }

    public List<ProductTable> findAllProduct() {
        List<ProductTable> product = productRepository.findAll();
        return product;
    }
//
//    public ProductTable findById(Integer id) {
//        Optional<ProductTable> product = productRepository.findById(id);
//        return product.get();
//    }



    public ProductTable findByProductName(String productName) throws BaseException {
        Optional<ProductTable> product = productRepository.findByProductName(productName);
        return product.get();
    }




//    public ProductTable updateProduct(String productName, String productDetail, String productPrice) {
//        ProductTable product = productRepository.findByProductName();
//
//
//        return productRepository.save(product);
//    }



}
