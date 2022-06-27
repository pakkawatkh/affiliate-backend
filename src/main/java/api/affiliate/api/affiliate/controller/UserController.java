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


    //    POST
    @PostMapping("/user-register")
    public ResponseEntity<Object> register(@RequestBody UserRegisterRequest request) throws BaseException {
        Object register = userBusiness.register(request);
        return ResponseEntity.ok(register);
    }


    @PostMapping("/user-login")
    public ResponseEntity<Object> login(@RequestBody UserLoginRequest request) throws BaseException {
        Object login = userBusiness.login(request);
        return ResponseEntity.ok(login);
    }




    //    GET
    @GetMapping("/user-checkLogin")
    public ResponseEntity<UserTable> checkLogin(@RequestBody UserLoginRequest request) throws BaseException {
        UserTable login = userBusiness.checkLogin(request);
        return ResponseEntity.ok(login);
    }


    @GetMapping("/getAll-user")
    public List<UserTable> getAllUser() {
        List<UserTable> user = userBusiness.findAllUser();
        return user;
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
