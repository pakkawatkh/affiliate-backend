package api.affiliate.api.affiliate.business;

import api.affiliate.api.affiliate.entity.*;
import api.affiliate.api.affiliate.exception.BaseException;
import api.affiliate.api.affiliate.exception.OrderException;
import api.affiliate.api.affiliate.model.Response;
import api.affiliate.api.affiliate.model.order.OrderRequest;
import api.affiliate.api.affiliate.model.product.ProductRequest;
import api.affiliate.api.affiliate.service.*;
import api.affiliate.api.affiliate.service.token.TokenService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailBusiness {


    public final OrderListService orderService;
    public final UserService userService;
    public final CartService cartService;
    private final StoreService storeService;
    private final OrderDetailService orderDetailService;
    private final TokenService tokenService;
    private final ProductService productService;
    private final FileService fileService;

    public OrderDetailBusiness(TokenService tokenService, OrderListService orderService, UserService userService, CartService cartService, StoreService storeService, OrderDetailService orderDetailService, ProductService productService, FileService fileService) {
        this.tokenService = tokenService;
        this.orderService = orderService;
        this.userService = userService;
        this.cartService = cartService;
        this.storeService = storeService;
        this.orderDetailService = orderDetailService;
        this.productService = productService;
        this.fileService = fileService;
    }


    public List<OrderDetailTable> getAllOrder() throws BaseException {
        List<OrderDetailTable> order = orderDetailService.findAllOrder();
        if (order.isEmpty()) {
            throw OrderException.orderNull();
        }
        return order;
    }


    public List<OrderDetailTable> findAllByStoreId() throws BaseException {
        UserTable user = tokenService.getUserByToken();
        StoreTable store = storeService.findByUserId2(user);
        List<OrderDetailTable> order = orderDetailService.findAllByStoreId(store.getStoreId());
        return order;
    }


    public Object addProduct(OrderRequest order) throws BaseException {
        UserTable user = tokenService.getUserByToken();
        List<ProductRequest> products = order.getProducts();
        System.out.println(products);
        System.out.println("USER : " + user.getUserId());
        for (ProductRequest product : products) {
            ProductTable pd = productService.findByProductId(product.getProductId());
            System.out.println("product id :" + pd);
            System.out.println("product amount : " + product.getProductAmount());
            Integer amount = product.getProductAmount();
            Integer price = pd.getProductPrice();
            Integer total = amount * price;
            System.out.println("ToTal " + total);
            System.out.println("Store : " + pd.getStoreId());
            orderDetailService.addProductIsOrder(pd.getProductId(), pd.getProductPrice(), product.getProductAmount(), total,
                    pd.getStoreId(), user.getUserId(), order.getFullName(), Integer.valueOf(order.getTel()), order.getAddress(), order.getSub(),
                    order.getDistrict(), order.getProvince(), order.getPostalCode());
//            System.out.println("Create : " +addProductIsOrder );
        }
        return new Response().success("add product to order success");
    }


}