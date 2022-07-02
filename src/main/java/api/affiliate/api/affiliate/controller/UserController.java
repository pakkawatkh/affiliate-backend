package api.affiliate.api.affiliate.controller;

import api.affiliate.api.affiliate.business.UserBusiness;
import api.affiliate.api.affiliate.entity.UserTable;
import api.affiliate.api.affiliate.exception.BaseException;
import api.affiliate.api.affiliate.model.user.UserLoginRequest;
import api.affiliate.api.affiliate.model.user.UserRegisterRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    public final UserBusiness userBusiness;

    public UserController(UserBusiness userBusiness) {
        this.userBusiness = userBusiness;
    }







    //    GET
    @GetMapping("/user-checkLogin")
    public ResponseEntity<Object> checkLogin() throws BaseException {
        Object login = userBusiness.checkLogin();
        return ResponseEntity.ok(login);
    }





    @GetMapping("/getProfile")
    public ResponseEntity<Object> profile() throws BaseException {
        Object profile = userBusiness.getProfile();
        return ResponseEntity.ok(profile);
    }


//    @PostMapping
//    public ResponseEntity<String> uploadProfilePicture (@RequestPart MultipartFile file) throws FileException {
//        String response = userBusiness.uploadProfilePicture(file);
//        return  ResponseEntity.ok(response);
//    }

}
