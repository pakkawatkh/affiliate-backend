package api.affiliate.api.affiliate.business;


import api.affiliate.api.affiliate.entity.ProductTable;
import api.affiliate.api.affiliate.entity.StoreTable;
import api.affiliate.api.affiliate.entity.UserTable;
import api.affiliate.api.affiliate.exception.BaseException;
import api.affiliate.api.affiliate.exception.ProductException;
import api.affiliate.api.affiliate.model.MapObject;
import api.affiliate.api.affiliate.model.Response;
import api.affiliate.api.affiliate.model.product.ProductCreateRequest;
import api.affiliate.api.affiliate.service.FileService;
import api.affiliate.api.affiliate.service.ProductService;
import api.affiliate.api.affiliate.service.StoreService;
import api.affiliate.api.affiliate.service.UserService;
import api.affiliate.api.affiliate.service.token.TokenService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ProductBusiness {


    private final ProductService productService;
    private final TokenService tokenService;
    private final StoreService storeService;
    private final FileService fileService;


    public ProductBusiness(ProductService productService, TokenService tokenService, StoreService storeService, UserService userService, FileService fileService) {
        this.productService = productService;
        this.tokenService = tokenService;
        this.storeService = storeService;
        this.fileService = fileService;
    }


    public List<ProductTable> finAllProduct() throws ProductException {
        List<ProductTable> product = productService.findAllProduct();
        if (product.isEmpty()){
            throw ProductException.productNull();
        }
        return product;
    }



    public List<ProductTable> findAllProductByStore() throws BaseException {
        UserTable user = tokenService.getUserByToken();
        checkRoleIsStore(user);
        StoreTable store = storeService.findByUserId2(user);
        List<ProductTable> product = productService.findAllProductByStoreId(store.getStoreId());
        return product;
    }



    public List<ProductTable> findMyProductByStatusIsTrue() throws BaseException {
        UserTable user = tokenService.getUserByToken();
        checkRoleIsStore(user);
        StoreTable store = storeService.findByUserId2(user);
        System.out.println(store);
        List<ProductTable> product = productService.findByStatusIsTrueAndStoreId(store.getStoreId());
        return product;

    }




    public List<ProductTable> findAllByStatusIsTrue() {
        List<ProductTable> product = productService.findAllByStatusIsTrue();
        return product;
    }

    public List<ProductTable> findAllProductByStoreId(Integer id){
        List<ProductTable> product = productService.findAllProductByStoreId(id);
        return product;
    }



    public ProductTable findByProductById(Integer productId) throws BaseException{
        ProductTable product = productService.findByProductId(productId);
        System.out.println(product);
       return product;
    }




    public Object createProduct(MultipartFile file, Object product) throws BaseException {
        UserTable user = tokenService.getUserByToken();
        checkRoleIsStore(user);
        StoreTable store = storeService.findByUserId(user);
        MapObject object = new MapObject();
        ProductCreateRequest request = object.toCreateProduct(product);
        request.valid();
        System.out.println(user.getRole());
        String img = fileService.saveImg(file, "/uploads/products");
        productService.createProduct(store.getStoreId(), request.getProductName(), request.getProductDetail(), Integer.valueOf(request.getProductPrice()), img);
        return new Response().success("create product success");

    }



    public Object updateProduct(MultipartFile file, Object product, Integer productId) throws BaseException {
        UserTable user = tokenService.getUserByToken();
        checkRoleIsStore(user);
        StoreTable store = storeService.findByUserId2(user);
        MapObject object = new MapObject();
        ProductCreateRequest request = object.toCreateProduct(product);
        ProductTable pd = productService.getByProductIdAndStore(store, productId);
        String img ;
        img = file != null?  fileService.saveImg(file, "/uploads/products") : pd.getImage();
        productService.updateProduct(pd, request.getProductName(), request.getProductDetail(), Integer.valueOf(request.getProductPrice()), img);
        return new Response().success("update product success");
    }

    public Object updateProductByStatus(Integer productId) throws BaseException {
        UserTable user = tokenService.getUserByToken();
        checkRoleIsStore(user);
        StoreTable store = storeService.findByUserId2(user);
        ProductTable product = productService.getByProductIdAndStore(store, productId);
        productService.updateProductByStatus(product);
        return new Response().success("delete product success");
    }


    public void checkRoleIsStore(UserTable user) throws BaseException {
        UserTable.Role role = user.getRole();
        boolean checkRole = role.equals(UserTable.Role.USER)
                || role.equals(UserTable.Role.AFFILIATE)
                || role.equals(UserTable.Role.ADMIN);
        if (checkRole){
            throw ProductException.roleUserNotAllowed();
        }
    }







}
