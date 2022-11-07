package api.affiliate.api.affiliate.business;

import api.affiliate.api.affiliate.entity.AffiliateTable;
import api.affiliate.api.affiliate.entity.UserTable;
import api.affiliate.api.affiliate.exception.AffiliateException;
import api.affiliate.api.affiliate.model.Response;
import api.affiliate.api.affiliate.model.affiliate.AffiliateRegisterRequest;
import api.affiliate.api.affiliate.service.AffiliateService;
import api.affiliate.api.affiliate.service.UserService;
import api.affiliate.api.affiliate.service.token.TokenService;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service
public class AffiliateBusiness {


    public final AffiliateService affiliateService;
    public final UserService userService;
    private final TokenService tokenService;

    public AffiliateBusiness(AffiliateService affiliateService, UserService userService, TokenService tokenService) {
        this.affiliateService = affiliateService;
        this.userService = userService;
        this.tokenService = tokenService;
    }


    @SneakyThrows
    public Object getMyAffiliate(){
        UserTable user = tokenService.getUserByToken();
        UserTable.Role role = user.getRole();
        if ((role.equals(UserTable.Role.STORE) || (role.equals(UserTable.Role.USER)
                || (role.equals(UserTable.Role.ADMIN))))){
            throw AffiliateException.roleUserNotAllowed();
        }

        AffiliateTable affiliate = affiliateService.findByUser(user);
        return affiliate;
    }


    @SneakyThrows
    public Object register(AffiliateRegisterRequest request){
        UserTable user = tokenService.getUserByToken();
        UserTable.Role role = user.getRole();
        if (role.equals(UserTable.Role.ADMIN) || role.equals(UserTable.Role.ST_AF)) {
            throw AffiliateException.affiliateRequestInvalid();
        }
        affiliateService.register(user.getUserId(), request.getBankNameAccount(), request.getBankName(), request.getBankNumber());
        if (role.equals(UserTable.Role.USER)) {
            role = UserTable.Role.AFFILIATE;
        } else {
            role = UserTable.Role.ST_AF;
        }
        userService.updateRole(user, role);
        return new Response().success("register success");
    }


    @SneakyThrows
    public Object updateProfile(AffiliateRegisterRequest request){
        UserTable user = tokenService.getUserByToken();
        UserTable.Role role = user.getRole();
        if (role.equals(UserTable.Role.ADMIN) || role.equals(UserTable.Role.STORE) || role.equals(UserTable.Role.USER))  {
            throw AffiliateException.roleUserNotAllowed();
        }
        AffiliateTable affiliate = affiliateService.findByUser(user);
        affiliateService.updateProfile(affiliate, request.getBankNameAccount(), request.getBankName(), request.getBankNumber());
        return new Response().success("update success");
    }



    public Object updateMyStatusAffiliate(){
        UserTable user = tokenService.getUserByToken();
        UserTable.Role role = user.getRole();
        AffiliateTable affiliate = affiliateService.findByUser(user);
        affiliateService.updateMyStatusAffiliate(affiliate);
        return new Response().success("delete affiliate success");
    }

}