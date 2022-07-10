package api.affiliate.api.affiliate.business;

import api.affiliate.api.affiliate.entity.AffiliateTable;
import api.affiliate.api.affiliate.entity.UserTable;
import api.affiliate.api.affiliate.exception.AffiliateException;
import api.affiliate.api.affiliate.exception.BaseException;
import api.affiliate.api.affiliate.model.MapObject;
import api.affiliate.api.affiliate.model.Response;
import api.affiliate.api.affiliate.model.affiliate.AffiliateRegisterRequest;
import api.affiliate.api.affiliate.model.store.StoreRegisterRequest;
import api.affiliate.api.affiliate.service.AffiliateService;
import api.affiliate.api.affiliate.service.FileService;
import api.affiliate.api.affiliate.service.UserService;
import api.affiliate.api.affiliate.service.token.TokenService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class AffiliateBusiness {


    public final AffiliateService affiliateService;
    public final UserService userService;
    private final TokenService tokenService;

    private final FileService fileService;

    public AffiliateBusiness(TokenService tokenService, AffiliateService affiliateService, UserService userService, FileService fileService) {
        this.tokenService = tokenService;
        this.affiliateService = affiliateService;
        this.userService = userService;
        this.fileService = fileService;
    }


    public Object getMyAffiliate()throws BaseException{
        UserTable user = tokenService.getUserByToken();
        UserTable.Role role = user.getRole();
        if ((role.equals(UserTable.Role.STORE) || (role.equals(UserTable.Role.USER)
                || (role.equals(UserTable.Role.ADMIN))))){
            throw AffiliateException.roleUserNotAllowed();
        }

        AffiliateTable affiliate = affiliateService.findByUser(user);
        return affiliate;
    }


    public Object register(MultipartFile file, Object profile) throws BaseException {
        UserTable user = tokenService.getUserByToken();
        UserTable.Role role = user.getRole();
        if (role.equals(UserTable.Role.ADMIN) || role.equals(UserTable.Role.ST_AF)) {
            throw AffiliateException.affiliateRequestInvalid();
        }
        MapObject object = new MapObject();
        AffiliateRegisterRequest request = object.toRegisterAffiliate(profile);
        request.valid();
        String img = fileService.saveImg(file, "/uploads/profile");
        affiliateService.register(user.getUserId(), request.getBankNameAccount(), request.getBankName(), request.getBankNumber(), img);
        if (role.equals(UserTable.Role.USER)) {
            role = UserTable.Role.AFFILIATE;
        } else {
            role = UserTable.Role.ST_AF;
        }
        userService.updateRole(user, role);
        return new Response().success("register success");
    }


    public Object updateProfile(MultipartFile file, Object profile) throws BaseException {
        UserTable user = tokenService.getUserByToken();
        UserTable.Role role = user.getRole();
        if (role.equals(UserTable.Role.ADMIN) || role.equals(UserTable.Role.STORE) || role.equals(UserTable.Role.USER))  {
            throw AffiliateException.roleUserNotAllowed();
        }
        AffiliateTable affiliate = affiliateService.findByUser(user);
        MapObject object = new MapObject();
        AffiliateRegisterRequest request = object.toRegisterAffiliate(profile);
        request.valid();
        String img = fileService.saveImg(file, "/uploads/profile");
        affiliateService.updateProfile(affiliate, request.getBankNameAccount(), request.getBankName(), request.getBankNumber(), img);
        return new Response().success("update success");
    }



    public Object updateMyStatusAffiliate() throws BaseException {
        UserTable user = tokenService.getUserByToken();
        UserTable.Role role = user.getRole();
        AffiliateTable affiliate = affiliateService.findByUser(user);
        affiliateService.updateMyStatusAffiliate(affiliate);
        return new Response().success("delete affiliate success");
    }

}