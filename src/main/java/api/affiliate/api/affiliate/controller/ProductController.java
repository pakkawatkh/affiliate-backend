package api.affiliate.api.affiliate.controller;


import api.affiliate.api.affiliate.business.ProductBusiness;
import api.affiliate.api.affiliate.entity.ProductTable;
import api.affiliate.api.affiliate.model.product.ProductByIdRequest;
import api.affiliate.api.affiliate.model.product.ProductCreateRequest;
import api.affiliate.api.affiliate.repository.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

    public final ProductBusiness productBusiness;
    public final ProductRepository productRepository;

    public ProductController(ProductBusiness productBusiness, ProductRepository productRepository) {
        this.productBusiness = productBusiness;
        this.productRepository = productRepository;
    }


//    GET
    @GetMapping("/getAll-product")
    public List<ProductTable> getAllProduct() {
    List<ProductTable> product = productBusiness.finAllProduct();
    return product;
    }

    @GetMapping("/get-productById/{id}")
    public ProductTable getProductById(@PathVariable("id") Integer id)   {
        ProductTable product = productRepository.findById(id).get();
        return product;
    }



//      POST
    @PostMapping("/create-product")
    public ResponseEntity<ProductTable> createProduct(@RequestBody ProductCreateRequest request) {
        ProductTable product = productBusiness.createProduct(request);
        return ResponseEntity.ok(product);
    }







//      PUT
    @PutMapping("/update-product/{id}")
    public ProductTable updateProduct(@PathVariable("id") Integer id,
                                  @RequestBody ProductCreateRequest request){
    ProductTable product = productRepository.findById(id).get();
    product.setProductName(request.getProductName());
    product.setProductPrice(request.getProductPrice());
    product.setProductDetail(request.getProductDetail());
    productRepository.save(product);
    return product;
    }








//      DELETE
    @DeleteMapping("/delete-product/{id}")
    public boolean deleteProduct(@PathVariable("id") Integer id){
        if (!productRepository.findById(id).equals((Optional.empty()))){
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }






}
