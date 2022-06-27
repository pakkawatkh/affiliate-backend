package api.affiliate.api.affiliate.service;

import api.affiliate.api.affiliate.entity.CustomerTable;
import api.affiliate.api.affiliate.entity.UserTable;
import api.affiliate.api.affiliate.exception.BaseException;
import api.affiliate.api.affiliate.exception.CustomerException;
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




    public void register(String customerName, String passWord, String fullName, String email, String tel, String address, String sub
            , String district, String province, String postalCode) throws BaseException {
        CustomerTable customer = new CustomerTable();
        customer.setCustomerName(customerName);
        customer.setPassWord(passwordEncoder.encode(passWord));
        customer.setFullName(fullName);
        customer.setEmail(email);
        customer.setTel(tel);
        customer.setAddress(address);
        customer.setSub(sub);
        customer.setDistrict(district);
        customer.setProvince(province);
        customer.setPostalCode(postalCode);
        if (customerRepository.existsByCustomerName(customer.getCustomerName())) {
            throw CustomerException.createCustomerDuplicated();
        }
        try {
            customerRepository.save(customer);

        }catch (Exception e){
            throw CustomerException.customerRequestInvalid();
        }
    }




    public CustomerTable findByCustomerName(String customerName) throws BaseException {
        Optional<CustomerTable> customer = customerRepository.findByCustomerName(customerName);
        if (customer.isEmpty()) {
            throw CustomerException.customerNameNull();
        }
        return customer.get();
    }




    public void updateRole(CustomerTable customerTable, UserTable.Role role){
        customerTable.setRole(role);
        customerRepository.save(customerTable);
    }

}
