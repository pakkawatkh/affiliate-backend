package api.affiliate.api.affiliate.controller;


import api.affiliate.api.affiliate.business.ProductBusiness;
import api.affiliate.api.affiliate.entity.ProductTable;
import api.affiliate.api.affiliate.exception.BaseException;
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

    public ProductController(ProductBusiness productBusiness) {
        this.productBusiness = productBusiness;
    }


    //    GET
    @GetMapping("/getAll-product")
    public List<ProductTable> getAllProduct() {
        List<ProductTable> product = productBusiness.finAllProduct();
        return product;
    }

    @GetMapping("/getAll-productByStoreId/{id}")
    public ResponseEntity<Object> getProductByStoreId(@PathVariable Integer id) throws BaseException {
        List<ProductTable> product = productBusiness.findAllProductByStoreId(id);
        return ResponseEntity.ok(product);
    }

    @GetMapping("/getAllByStatusIsTrue-product")
    public List<ProductTable> findAllByStatusIsTrue() {
        List<ProductTable> product = productBusiness.findAllByStatusIsTrue();
        return product;
    }


    @GetMapping("/getProductById/{id}")
    public ResponseEntity<Object> getProductById(@PathVariable Integer id) throws BaseException{
        Object product = productBusiness.findByProductById(id);
        return ResponseEntity.ok(product);
    }


    //      POST
    @PostMapping("/create-product")
    public ResponseEntity<Object> createProduct(@RequestBody ProductCreateRequest request) throws BaseException {
        Object create = productBusiness.createProduct(request);
        return ResponseEntity.ok(create);
    }


    @PutMapping("/update-product/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable Integer id, @RequestBody ProductCreateRequest request) throws BaseException {
        Object update = productBusiness.updateProduct(request, id);
        return ResponseEntity.ok(update);
    }




}
