package api.affiliate.api.affiliate.business;

import api.affiliate.api.affiliate.entity.AffiliateTable;
import api.affiliate.api.affiliate.entity.ProductTable;
import api.affiliate.api.affiliate.entity.LinkTable;
import api.affiliate.api.affiliate.entity.UserTable;
import api.affiliate.api.affiliate.exception.AffiliateException;
import api.affiliate.api.affiliate.exception.StoreException;
import api.affiliate.api.affiliate.exception.UserException;
import api.affiliate.api.affiliate.model.Response;
import api.affiliate.api.affiliate.model.affiliate.AffiliateRegisterRequest;
import api.affiliate.api.affiliate.model.link.DetailLinkResponse;
import api.affiliate.api.affiliate.model.link.LinkResponse;
import api.affiliate.api.affiliate.service.AffiliateService;
import api.affiliate.api.affiliate.service.ProductService;
import api.affiliate.api.affiliate.service.LinkService;
import api.affiliate.api.affiliate.service.UserService;
import api.affiliate.api.affiliate.service.token.TokenService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AffiliateBusiness {


    public final AffiliateService affiliateService;
    public final UserService userService;
    private final TokenService tokenService;
    private final ProductService productService;
    private final LinkService linkService;


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


    public DetailLinkResponse getShareLinkSuccessByStore(Integer productId) {
        UserTable user = tokenService.getUserByToken();
        checkRoleIsStore(user);
        List<LinkResponse> link = linkService.getShareLinkSuccessByProductId(productId);
        DetailLinkResponse detail = linkService.getDetailShareLinkSuccessByProductId(productId);
        detail.setLinks(link);
        link.forEach(x->detail.setLinkAmount(detail.getLinkAmount()+x.getAmount()));
        return detail;
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
        AffiliateTable affiliate = affiliateService.findByUser(user);
        affiliateService.updateMyStatusAffiliate(affiliate);
        return new Response().success("delete affiliate success");
    }


    public Object shareProduct(Integer productId) {
        UserTable user = tokenService.getUserByToken();
        checkRoleIsAffiliate(user);
        AffiliateTable affiliate = affiliateService.findByUser(user);
        ProductTable pd = productService.findByProductId(productId);
        LinkTable link = linkService.createLink(pd.getProductId(), pd.getStoreId(), affiliate.getAffiliateId(),
                pd.getPriceForShare());

        return new Response().ok("create link success", "link", link.getLinkId());
    }


    @SneakyThrows
    public void checkRoleIsAffiliate(UserTable user) {
        UserTable.Role role = user.getRole();
        boolean checkRole = role.equals(UserTable.Role.STORE)
                || role.equals(UserTable.Role.ADMIN)
                || role.equals(UserTable.Role.USER);
        if (checkRole) {
            throw UserException.roleUserNotAllowed();
        }
    }


    @SneakyThrows
    public void checkRoleIsStore(UserTable user) {
        UserTable.Role role = user.getRole();
        boolean checkRole = role.equals(UserTable.Role.USER) || role.equals(UserTable.Role.AFFILIATE) || role.equals(UserTable.Role.ADMIN);
        if (checkRole) {
            throw StoreException.roleUserNotAllowed();
        }
    }

}