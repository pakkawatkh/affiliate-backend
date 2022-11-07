package api.affiliate.api.affiliate.business;

import api.affiliate.api.affiliate.entity.*;
import api.affiliate.api.affiliate.exception.OrderException;
import api.affiliate.api.affiliate.exception.ProductException;
import api.affiliate.api.affiliate.model.order.OrderRequest;
import api.affiliate.api.affiliate.model.product.ProductRequest;
import api.affiliate.api.affiliate.service.*;
import api.affiliate.api.affiliate.service.token.TokenService;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderDetailBusiness {

    public final OrderListService orderService;
    public final UserService userService;
    private final StoreService storeService;
    private final OrderDetailService orderDetailService;
    private final TokenService tokenService;
    private final ProductService productService;
    private final FileService fileService;


    public OrderDetailBusiness(OrderListService orderService, UserService userService, StoreService storeService,
                               OrderDetailService orderDetailService, TokenService tokenService, ProductService
                                       productService, FileService fileService) {
        this.orderService = orderService;
        this.userService = userService;
        this.storeService = storeService;
        this.orderDetailService = orderDetailService;
        this.tokenService = tokenService;
        this.productService = productService;
        this.fileService = fileService;
    }


    @SneakyThrows
    public List<OrderDetailTable> findAllOrderDetail() {
        List<OrderDetailTable> order = orderDetailService.findAllOrderDetail();
        if (order.isEmpty()) {
            throw OrderException.orderNull();
        }
        return order;
    }


    @SneakyThrows
    public Object addProduct(OrderRequest order) {
        UserTable user = tokenService.getUserByToken();
        List<ProductRequest> products = order.getProducts();
        StoreTable store = storeService.findByStoreId(order.getStoreId());
        OrderListTable orderList = orderService.createOrder(user.getUserId(), order.getFullName(), order.getTel(),
                order.getAddress(), order.getSub(), order.getDistrict(), order.getProvince(), order.getPostalCode(),
                order.getStoreId());
        List<OrderDetailTable> orderDetail = new ArrayList<>();
        try {
            for (ProductRequest product : products) {
                ProductTable pd = productService.getByProductIdAndStore(store, product.getProductId());
                Integer amount = product.getAmount();
                Integer price = pd.getProductPrice();
                Integer total = amount * price;
                OrderDetailTable detail = orderDetailService.addProductIsOrder(pd.getProductId(),
                        pd.getProductName(), pd.getProductPrice(), product.getAmount(), total, orderList.getOrderListId());
                orderDetail.add(detail);
            }
        } catch (Exception e) {
            orderService.deleteOrderList(orderList);
            throw ProductException.productNull();
        }
        orderDetailService.saveAll(orderList, orderDetail);
        int total = 0;
        for (OrderDetailTable product : orderDetail) {
            total = total + product.getProductTotal();
        }
        orderList.setTotalPrice(total);
        orderService.saveTotalPrice(orderList);
        return orderList;
    }


}