package api.affiliate.api.affiliate.service;

import api.affiliate.api.affiliate.entity.StoreTable;
import api.affiliate.api.affiliate.entity.UserTable;
import api.affiliate.api.affiliate.exception.BaseException;
import api.affiliate.api.affiliate.exception.StoreException;
import api.affiliate.api.affiliate.model.store.StoreRequest;
import api.affiliate.api.affiliate.repository.StoreRepository;
import lombok.SneakyThrows;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StoreService {

    public final StoreRepository storeRepository;
    public final PasswordEncoder passwordEncoder;

    public StoreService(StoreRepository storeRepository, PasswordEncoder passwordEncoder) {
        this.storeRepository = storeRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public List<StoreTable> findAllStore() {
        List<StoreTable> store = storeRepository.findAll();
        return store;
    }

    @SneakyThrows
    public void register(String user, String store, String bankNameAccount, String bankName, String bankNumber){
        StoreTable st = new StoreTable();
        st.setUserId(user);
        st.setStore(store);
        st.setBankNameAccount(bankNameAccount);
        st.setBankName(bankName);
        st.setBankNumber(bankNumber);
        if (storeRepository.existsByStore((st.getStore()))) {
            throw StoreException.createStoreNameDuplicated();
        }
        try {
            storeRepository.save(st);
        } catch (Exception e) {
            throw StoreException.storeRequestInvalid();
        }
    }


    public void updateStore(StoreTable st, String store, String bankNameAccount, String bankName, String bankNumber) {
        st.setStore(store);
        st.setBankNameAccount(bankNameAccount);
        st.setBankName(bankName);
        st.setBankNumber(bankNumber);
        try {
            storeRepository.save(st);
        } catch (Exception e) {
            StoreException.storeRequestInvalid();
        }
    }


    public void updateStatusStore(StoreTable store) {
        store.setStatus(false);
        try {
            storeRepository.save(store);
        } catch (Exception e) {
            StoreException.storeRequestInvalid();
        }

    }


    public StoreTable findByUserId(UserTable user) {
        Optional<StoreTable> store = storeRepository.findByUserId(user.getUserId());
        return store.get();
    }


    public StoreTable findByUserId(String userId) {
        Optional<StoreTable> store = storeRepository.findByUserId(userId);
        return store.get();
    }


    @SneakyThrows
    public StoreTable findByStoreId(Integer storeId) {
        Optional<StoreTable> store = storeRepository.findById(storeId);
        if (store.isEmpty()) {
            throw StoreException.storeIdNull();
        }
        return store.get();
    }

    @SneakyThrows
    public StoreTable findByUserId2(UserTable user) {
        Optional<StoreTable> store = storeRepository.findByUserId(user.getUserId());
        if (store.isEmpty()) {
            throw StoreException.storeNameNull();
        }
        return store.get();
    }


    @SneakyThrows
    public StoreTable getWithdrawIdAndStoreId(Integer withdrawId, Integer storeId){
        Optional<StoreTable> withdraw = storeRepository.getWithdrawIdAndStoreId(withdrawId, storeId);
        return withdraw.get();
    }


    @SneakyThrows
    public StoreTable getStoreIdAndUserId(Integer storeId, String userId){
        Optional<StoreTable> withdraw = storeRepository.getStoreIdAndUserId(storeId, userId);
//        if (withdraw.isEmpty()){
//            throw WithdrawException.withdrawNull();
//        }
        return withdraw.get();
    }

}
