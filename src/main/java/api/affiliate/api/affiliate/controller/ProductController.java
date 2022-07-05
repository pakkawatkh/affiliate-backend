package api.affiliate.api.affiliate.controller;


import api.affiliate.api.affiliate.business.ProductBusiness;
import api.affiliate.api.affiliate.entity.ProductTable;
import api.affiliate.api.affiliate.exception.BaseException;
import api.affiliate.api.affiliate.model.product.ProductCreateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    public final ProductBusiness productBusiness;

    public ProductController(ProductBusiness productBusiness) {
        this.productBusiness = productBusiness;
    }


    @GetMapping("/getAll-productByStore")
    public ResponseEntity<Object> getProductByStore() throws BaseException {
        List<ProductTable> product = productBusiness.findAllProductByStore();
        return ResponseEntity.ok(product);
    }


    @GetMapping("/getMy-productByStoreIsTrue")
    public ResponseEntity<Object> getMyProductByStoreIsTrue() throws BaseException {
//        List<ProductTable> product = productBusiness.findAllProductByStatusIsTrue();
        List<ProductTable> product = productBusiness.findMyProductByStatusIsTrue();
        return ResponseEntity.ok(product);
    }







    //      POST
    @PostMapping("/create-product")
    public ResponseEntity<Object> createProduct(@RequestBody ProductCreateRequest request) throws BaseException {
        Object create = productBusiness.createProduct(request);
        return ResponseEntity.ok(create);
    }




    //    PUT
    @PutMapping("/update-product/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable Integer id, @RequestBody ProductCreateRequest request) throws BaseException {
        Object update = productBusiness.updateProduct(request, id);
        return ResponseEntity.ok(update);
    }

    @PutMapping("/update-productByStatus/{id}")
    public ResponseEntity<Object> updateProductByStatus(@PathVariable Integer id) throws BaseException {
        Object update = productBusiness.updateProductByStatus(id);
        return ResponseEntity.ok(update);
    }




}
