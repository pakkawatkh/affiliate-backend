package api.affiliate.api.affiliate.controller;

import api.affiliate.api.affiliate.business.UserBusiness;
import api.affiliate.api.affiliate.exception.BaseException;
import api.affiliate.api.affiliate.model.user.UserRegisterRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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


    @PutMapping("/update-profile")
    public ResponseEntity<Object> updateProfile(@RequestParam(value = "file", required = false) MultipartFile file,
                                                @RequestParam(value = "profile") Object profile) throws BaseException {
        Object update = userBusiness.updateProfile(file, profile);
        return ResponseEntity.ok(update);
    }



}
