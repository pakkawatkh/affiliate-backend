package api.affiliate.api.affiliate.business;


import api.affiliate.api.affiliate.entity.ProductTable;
import api.affiliate.api.affiliate.model.product.ProductByIdRequest;
import api.affiliate.api.affiliate.model.product.ProductCreateRequest;
import api.affiliate.api.affiliate.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductBusiness {


    private final ProductService productService;


    public ProductBusiness(ProductService productService) {
        this.productService = productService;
    }


    public ProductTable createProduct(ProductCreateRequest request) {
        ProductTable product = productService.createProduct(request.getProductName(), request.getProductDetail(), request.getProductPrice());

        return product;
    }


   public List<ProductTable> finAllProduct() {
        List<ProductTable> product = productService.findAllProduct();
        return product;
   }


//    public ProductTable findById(ProductByIdRequest request) {
//        ProductTable product = productService.findById(Integer.valueOf(request.getProductId()));
//        return product;
//    }


    public ProductTable updateProduct(ProductCreateRequest request) {
        ProductTable product = productService.createProduct(request.getProductName(), request.getProductDetail(), request.getProductPrice());

        return product;
    }





}
