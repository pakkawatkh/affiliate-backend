package api.affiliate.api.affiliate.service;

import api.affiliate.api.affiliate.entity.CustomerTable;
import api.affiliate.api.affiliate.entity.UserTable;
import api.affiliate.api.affiliate.exception.BaseException;
import api.affiliate.api.affiliate.exception.CustomerException;
import api.affiliate.api.affiliate.exception.StoreException;
import api.affiliate.api.affiliate.repository.CustomerRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    public final CustomerRepository customerRepository;
    public final PasswordEncoder passwordEncoder;

    public CustomerService(CustomerRepository customerRepository, PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
    }




    //check password : return boolean
    public boolean matchPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }




    public void register(String user,String bankNameAccount, String bankName, String bankNumber) throws BaseException {
    CustomerTable ctm = new CustomerTable();
        ctm.setUserId(user);
        ctm.setBankNameAccount(bankNameAccount);
        ctm.setBankName(bankName);
        ctm.setBankNumber(bankNumber);
        try {
            customerRepository.save(ctm);
        }catch (Exception e) {
            throw CustomerException.customerRequestInvalid();
        }
    }



public CustomerTable findByUser(UserTable user){
    Optional<CustomerTable> customer = customerRepository.findByUserId(user.getUserId());
   return customer.get();
}



}
