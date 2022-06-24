package api.affiliate.api.affiliate.business;

import api.affiliate.api.affiliate.entity.UserTable;
import api.affiliate.api.affiliate.exception.BaseException;
import api.affiliate.api.affiliate.exception.UserException;
import api.affiliate.api.affiliate.model.Response;
import api.affiliate.api.affiliate.model.user.UserLoginRequest;
import api.affiliate.api.affiliate.model.user.UserRegisterRequest;
import api.affiliate.api.affiliate.service.UserService;
import api.affiliate.api.affiliate.service.token.TokenService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserBusiness {

    private final TokenService tokenService;
    private final UserService userService;


    public UserBusiness(TokenService tokenService, UserService userService) {
        this.tokenService = tokenService;
        this.userService = userService;
    }

    public Object register(UserRegisterRequest request) throws BaseException {
        request.valid();
        userService.register(request.getUserName(), request.getPassWord(), request.getFullName(), request.getEmail(), request.getTel(), request.getAddress(), request.getSub(), request.getDistrict(), request.getProvince(), request.getPostalCode());
        return new Response().success("register success");
    }

    public Object login(UserLoginRequest request) throws BaseException {
        request.valid();
        UserTable user = userService.findByUserName(request.getUserName());

        if (!userService.matchPassword(request.getPassWord(), user.getPassWord())) {
            throw UserException.passWordInvalid();
        }

        String tokenize = tokenService.tokenizeUser(user);

        return new Response().ok(user.getRole().toString(),"token",tokenize);
    }

    public UserTable checkLogin(UserLoginRequest request) throws BaseException {
        request.valid();
        UserTable user = userService.findByUserName(request.getUserName());
        if (!user.getPassWord().equals(request.getPassWord())) {
            throw UserException.passWordInvalid();
        }
        return user;
    }

    public List<UserTable> findAllUser() {
        List<UserTable> store = userService.findAllUser();
        return store;
    }

    public Object getProfile() throws BaseException {
        UserTable user = tokenService.getUserByToken();

        return new Response().ok("","profile",user);
    }


//    public String uploadProfilePicture(MultipartFile file) throws FileException {
//        if ( file == null) {
//            throw FileException.fileNull();
//        }
//        if ( file.getSize() > 1048576 * 2) {
//            // throw error
//        }
//
//        String contentType = file.getContentType();
//        if ( contentType == null ) {
//            throw FileException.fileMaxSize();
//        }
//
//        List<String> supportTypes = Arrays.asList("image/jpeg", "image/png");
//        if ( !supportTypes.contains(contentType)) {
//            throw FileException.unsupported();
//        }
//
//        try {
//            byte[] pytes = file.getBytes();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return "";
//    }
}
