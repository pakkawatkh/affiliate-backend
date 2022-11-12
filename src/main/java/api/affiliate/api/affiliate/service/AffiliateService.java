package api.affiliate.api.affiliate.service;

import api.affiliate.api.affiliate.entity.AffiliateTable;
import api.affiliate.api.affiliate.entity.UserTable;
import api.affiliate.api.affiliate.exception.BaseException;
import api.affiliate.api.affiliate.exception.AffiliateException;
import api.affiliate.api.affiliate.repository.AffiliateRepository;
import lombok.SneakyThrows;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AffiliateService {

    public final AffiliateRepository affiliateRepository;
    public final PasswordEncoder passwordEncoder;

    public AffiliateService(AffiliateRepository affiliateRepository, PasswordEncoder passwordEncoder) {
        this.affiliateRepository = affiliateRepository;
        this.passwordEncoder = passwordEncoder;
    }




    //check password : return boolean
    public boolean matchPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }




    @SneakyThrows
    public void register(String user, String bankNameAccount, String bankName, String bankNumber){
    AffiliateTable affiliate = new AffiliateTable();
        affiliate.setUserId(user);
        affiliate.setBankNameAccount(bankNameAccount);
        affiliate.setBankName(bankName);
        affiliate.setBankNumber(bankNumber);
        try {
            affiliateRepository.save(affiliate);
        }catch (Exception e) {
            throw AffiliateException.affiliateRequestInvalid();
        }
    }


    public void updateProfile(AffiliateTable affiliate,String bankNameAccount, String bankName, String bankNumber) throws BaseException {
        affiliate.setBankNameAccount(bankNameAccount);
        affiliate.setBankName(bankName);
        affiliate.setBankNumber(bankNumber);
        try {
            affiliateRepository.save(affiliate);
        }catch (Exception e) {
            throw AffiliateException.affiliateRequestInvalid();
        }
    }

    @SneakyThrows
    public void updateMyStatusAffiliate(AffiliateTable affiliate){
        affiliate.setStatus(false);
        try {
            affiliateRepository.save(affiliate);
        }catch (Exception e) {
            throw AffiliateException.affiliateRequestInvalid();
        }
    }



    public AffiliateTable findByUser(UserTable user){
        Optional<AffiliateTable> affiliate = affiliateRepository.findByUserId(user.getUserId());
       return affiliate.get();
    }


    public AffiliateTable findByUserId(String userId){
        Optional<AffiliateTable> affiliate = affiliateRepository.findByUserId(userId);
        return affiliate.get();
    }



}
