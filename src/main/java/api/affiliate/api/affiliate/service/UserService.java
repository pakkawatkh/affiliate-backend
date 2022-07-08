package api.affiliate.api.affiliate.service;

import api.affiliate.api.affiliate.entity.UserTable;
import api.affiliate.api.affiliate.exception.BaseException;
import api.affiliate.api.affiliate.exception.UserException;
import api.affiliate.api.affiliate.repository.UserRepository;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    public final UserRepository userRepository;
    public final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }




    //check password : return boolean
    public boolean matchPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }




    public void register(String userName, String passWord, String fullName, String email, String tel, String address, String sub
            , String district, String province, String postalCode, String img) throws UserException {
        UserTable user = new UserTable();
        user.setUserName(userName);
        user.setPassWord(passwordEncoder.encode(passWord));
        user.setFullName(fullName);
        user.setEmail(email);
        user.setTel(tel);
        user.setAddress(address);
        user.setSub(sub);
        user.setDistrict(district);
        user.setProvince(province);
        user.setPostalCode(postalCode);
        user.setImage(img);
        if (userRepository.existsByUserName(user.getUserName())) {
            throw UserException.createUserNameDuplicated();
        }
        try {
         userRepository.save(user);

        }catch (Exception e){
            throw UserException.userRequestInvalid();
        }
    }





    public UserTable findByUserName(String userName) throws BaseException {
        Optional<UserTable> user = userRepository.findByUserName(userName);
        if (user.isEmpty()) {
            throw UserException.userNameNull();
        }
        return user.get();
    }





    public List<UserTable> findAllUser() {
        List<UserTable> user = userRepository.findAll();
        return user;
    }





    public UserTable findById(String id) throws BaseException {
        Optional<UserTable> opt = userRepository.findById(id);
        if (opt.isEmpty()) {
            throw UserException.userNameNull();
        }

        return opt.get();
    }



    public void updateRole(UserTable userTable, UserTable.Role role){
        userTable.setRole(role);
        userRepository.save(userTable);
    }


    public void updateProfile(UserTable user, String fullName, String email, String tel, String address, String sub
            , String district, String province, String postalCode, String img) throws UserException {
        user.setFullName(fullName);
        user.setEmail(email);
        user.setTel(tel);
        user.setAddress(address);
        user.setSub(sub);
        user.setDistrict(district);
        user.setProvince(province);
        user.setPostalCode(postalCode);
        user.setImage(img);

        try {
            userRepository.save(user);
        } catch (Exception e) {
            throw UserException.userNameNull();
        }
    }

}
