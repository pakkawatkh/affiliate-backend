package api.affiliate.api.affiliate.business;

import api.affiliate.api.affiliate.entity.*;
import api.affiliate.api.affiliate.exception.BaseException;
import api.affiliate.api.affiliate.exception.OrderException;
import api.affiliate.api.affiliate.model.Response;
import api.affiliate.api.affiliate.service.FileService;
import api.affiliate.api.affiliate.service.OrderService;
import api.affiliate.api.affiliate.service.UserService;
import api.affiliate.api.affiliate.service.token.TokenService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class OrderBusiness {


    public final OrderService orderService;
    public final UserService userService;
    private final TokenService tokenService;
    private final FileService fileService;

    public OrderBusiness(TokenService tokenService, OrderService orderService, UserService userService, FileService fileService) {
        this.tokenService = tokenService;
        this.orderService = orderService;
        this.userService = userService;
        this.fileService = fileService;
    }



    public List<OrderListTable> getAllOrder() throws BaseException {
        List<OrderListTable> order = orderService.findAllOrder();
        if (order.isEmpty()){
            throw OrderException.orderNull();
        }
        return order;
    }



    public Object createOrder(MultipartFile file) throws BaseException {
        UserTable user = tokenService.getUserByToken();
        UserTable.Role role = user.getRole();
        if  (role.equals(UserTable.Role.ADMIN)){
            throw OrderException.roleUserNotAllowed();
        }
        String img = fileService.saveImg(file, "/uploads/orders");
        orderService.createOrder(user.getUserId(), img);
        return new Response().success("create order success");
    }




}