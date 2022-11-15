package api.affiliate.api.affiliate.service;

import api.affiliate.api.affiliate.entity.OrderListTable;
import api.affiliate.api.affiliate.entity.StoreTable;
import api.affiliate.api.affiliate.entity.UserTable;
import api.affiliate.api.affiliate.repository.OrderListRepository;
import api.affiliate.api.affiliate.repository.StoreRepository;
import api.affiliate.api.affiliate.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


interface AdminReq {

    String userName = "admin";
    String passWord = "123456";

    String fullName = "admin";

    String email = "admin@gmail.com";

    String tel = "0123456789";

    String address = "";

    String sub = "";

    String district = "";

    String province = "";

    Integer postalCode = 40270;
}

@Service
public class AdminService {

    public final PasswordEncoder passwordEncoder;
    public final UserRepository userRepository;
    public final OrderListRepository orderListRepository;

    public final StoreRepository storeRepository;

    public AdminService(PasswordEncoder passwordEncoder, UserRepository userRepository, OrderListRepository orderListRepository, StoreRepository storeRepository) {

        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.orderListRepository = orderListRepository;
        this.storeRepository = storeRepository;
        register();
    }

    public boolean matchPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }


    public void register(){
        Optional<UserTable> byUserName = userRepository.findByUserName(AdminReq.userName);
        if (byUserName.isEmpty()) {
            UserTable user = new UserTable();
            user.setUserName(AdminReq.userName);
            user.setPassWord(passwordEncoder.encode(AdminReq.passWord));
            user.setFullName(AdminReq.fullName);
            user.setEmail(AdminReq.email);
            user.setTel(AdminReq.tel);
            user.setAddress(AdminReq.address);
            user.setSub(AdminReq.sub);
            user.setDistrict(AdminReq.district);
            user.setProvince(AdminReq.province);
            user.setPostalCode(AdminReq.postalCode);
            user.setRole(UserTable.Role.ADMIN);
            userRepository.save(user);
        }
    }

//    public List<UserTable> getAllOrder() {
//        List<UserTable> user = userRepository.();
//        return user;
//    }

    public List<UserTable> getAllRole(UserTable.Role roles, UserTable.Role role) {
        List<UserTable> user = userRepository.getAllRole(roles.toString(), role.toString());
        return user;
    }


    public List<UserTable> getAllRole(UserTable.Role roles) {
        List<UserTable> user = userRepository.getAllRole(roles.toString());
        return user;
    }


    public List<OrderListTable> findOrderByUserId(String userId) {
        List<OrderListTable> order = orderListRepository.findAllOrderByUserId(userId);
        return order;
    }


    public void saveTotalPrice(StoreTable store){
        try {
            storeRepository.save(store);
        }catch (Exception e){
//            TODO: ERROR
        }
    }








}
//
//    public List<UserTable> getAllUser() {
//        List<UserTable> user = userRepository.getAllUserByAdmin(UserTable.Role.ADMIN.toString());
//        return user;
//    }
