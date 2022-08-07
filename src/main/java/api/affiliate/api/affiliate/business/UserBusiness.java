package api.affiliate.api.affiliate.business;

import api.affiliate.api.affiliate.entity.AffiliateTable;
import api.affiliate.api.affiliate.entity.StoreTable;
import api.affiliate.api.affiliate.entity.UserTable;
import api.affiliate.api.affiliate.exception.BaseException;
import api.affiliate.api.affiliate.exception.UserException;
import api.affiliate.api.affiliate.model.MapObject;
import api.affiliate.api.affiliate.mapper.UserMapper;
import api.affiliate.api.affiliate.model.Response;
import api.affiliate.api.affiliate.model.user.UserLoginRequest;
import api.affiliate.api.affiliate.model.user.UserProfileResponse;
import api.affiliate.api.affiliate.model.user.UserRegisterRequest;
import api.affiliate.api.affiliate.service.AffiliateService;
import api.affiliate.api.affiliate.service.FileService;
import api.affiliate.api.affiliate.service.StoreService;
import api.affiliate.api.affiliate.service.UserService;
import api.affiliate.api.affiliate.service.token.TokenService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class UserBusiness {

    private final TokenService tokenService;
    private final UserService userService;
    private final UserMapper userMapper;
    private final StoreService storeService;
    private final AffiliateService affiliateService;

    private final FileService fileService;

    public UserBusiness(TokenService tokenService, UserService userService, UserMapper userMapper, StoreService storeService, AffiliateService affiliateService, FileService fileService) {
        this.tokenService = tokenService;
        this.userService = userService;
        this.userMapper = userMapper;
        this.storeService = storeService;
        this.affiliateService = affiliateService;
        this.fileService = fileService;
    }


    //file = img , profile = data profile
    public Object register(UserRegisterRequest request) throws BaseException {
        System.out.println(request);
        request.valid();
        userService.register(request.getUserName(), request.getPassWord(), request.getFullName(), request.getEmail(), request.getTel(), request.getAddress(),
                request.getSub(), request.getDistrict(), request.getProvince(), request.getPostalCode());
        return new Response().success("register success");
    }


    public Object login(UserLoginRequest request) throws BaseException {
        request.valid();
        UserTable user = userService.findByUserName(request.getUserName());
        if (!userService.matchPassword(request.getPassWord(), user.getPassWord())) {
            throw UserException.passWordInvalid();
        }
        String tokenize = tokenService.tokenizeUser(user);
        return new Response().ok(user.getRole().toString(), "token", tokenize);
    }


    public UserTable checkLogin() throws BaseException {
        UserTable user = tokenService.getUserByToken();
        return user;
    }


    public List<UserTable> findAllUser() {
        List<UserTable> store = userService.findAllUser();
        return store;
    }


    public Object getProfile() throws BaseException {
        UserTable user = tokenService.getUserByToken();
        UserProfileResponse response = userMapper.toUserProfileResponse(user);
        UserTable.Role role = user.getRole();
        if (role.equals(UserTable.Role.STORE)) {
            StoreTable store = storeService.findByUserId(user);
            response.setStore(store);
        } else if (role.equals(UserTable.Role.AFFILIATE)) {
            AffiliateTable affiliate = affiliateService.findByUser(user);
            response.setAffiliate(affiliate);
        } else if (role.equals(UserTable.Role.ST_AF)) {
            StoreTable store = storeService.findByUserId(user);
            response.setStore(store);
            AffiliateTable affiliate = affiliateService.findByUser(user);
            response.setAffiliate(affiliate);
        }
        return new Response().ok("", "profile", response);
    }


    public Object updateProfile(MultipartFile file, Object profile) throws BaseException {
        UserTable user = tokenService.getUserByToken();
        System.out.println(user.getImage());
        MapObject object = new MapObject();
        UserRegisterRequest request = object.toRegister(profile);
        System.out.println(file);
        String img = file != null ? fileService.saveImg(file, "/uploads/profile") : user.getImage();
        System.out.println(file);
        userService.updateProfile(user, request.getFullName(), request.getEmail(),
                request.getTel(), request.getAddress(), request.getSub(), request.getDistrict(),
                request.getProvince(), request.getPostalCode(), img);
        return new Response().success("update profile success");
    }


}
