package api.affiliate.api.affiliate.business;

import api.affiliate.api.affiliate.entity.CustomerTable;
import api.affiliate.api.affiliate.entity.UserTable;
import api.affiliate.api.affiliate.exception.BaseException;
import api.affiliate.api.affiliate.model.Response;
import api.affiliate.api.affiliate.model.customer.CustomerRegisterRequest;
import api.affiliate.api.affiliate.service.CustomerService;
import api.affiliate.api.affiliate.service.UserService;
import api.affiliate.api.affiliate.service.token.TokenService;
import org.springframework.stereotype.Service;

@Service
public class CustomerBusiness {


    public final CustomerService customerService;
    public final UserService userService;
    private final TokenService tokenService;

    public CustomerBusiness(TokenService tokenService, CustomerService customerService, UserService userService) {
        this.tokenService = tokenService;
        this.customerService = customerService;
        this.userService = userService;
    }


    public Object register(CustomerRegisterRequest request) throws BaseException {
        UserTable user = tokenService.getUserByToken();

        UserTable.Role role = user.getRole();
        if (role.equals(UserTable.Role.ADMIN)||role.equals(UserTable.Role.ST_CTM)) {
            //TODO: Throw error
        }
        request.valid();

        customerService.register(user.getUserId(), request.getBankNameAccount(), request.getBankName(), request.getBankNumber());

        if (role.equals(UserTable.Role.USER)) {
            role = UserTable.Role.CUSTOMER;
        } else {
            role = UserTable.Role.ST_CTM;
        }
        userService.updateRole(user, role);
        return new Response().success("register success");
    }


//    public Object getProfile() throws BaseException {
//        CustomerTable customer = tokenService.getCustomerByToken();
//        return new Response().ok("", "profile", customer);
//    }
}
