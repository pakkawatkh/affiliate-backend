package api.affiliate.api.affiliate.service;

import api.affiliate.api.affiliate.entity.UserTable;
import api.affiliate.api.affiliate.exception.UserException;
import api.affiliate.api.affiliate.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    public final UserRepository userRepository;
    public final PasswordEncoder passwordEncoder;


    public CustomerService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    //check password : return boolean
    public boolean matchPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }


    public void register(String userName, String passWord, String fullName, String email, String tel, String address, String sub
            , String district, String province, String postalCode) throws UserException {
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
        if (userRepository.existsByUserName(user.getUserName())) {
            throw UserException.createUserNameDuplicated();
        }
        try {
            userRepository.save(user);

        }catch (Exception e){
            throw UserException.userRequestInvalid();
        }
    }

}
