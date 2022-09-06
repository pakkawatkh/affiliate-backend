package api.affiliate.api.affiliate.service;

import api.affiliate.api.affiliate.entity.StoreTable;
import api.affiliate.api.affiliate.entity.UserTable;
import api.affiliate.api.affiliate.exception.BaseException;
import api.affiliate.api.affiliate.exception.StoreException;
import api.affiliate.api.affiliate.repository.StoreRepository;
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

    public void register(String user, String store, String bankNameAccount, String bankName, String bankNumber) throws StoreException {
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


    public void updateStatusStore(StoreTable store) throws BaseException {
        store.setStatus(false);
        try {
            storeRepository.save(store);
        } catch (
                Exception e) {
            StoreException.storeRequestInvalid();
        }

    }


    public StoreTable findByUserId(UserTable user) throws BaseException {
        Optional<StoreTable> store = storeRepository.findByUserId(user.getUserId());
        return store.get();
    }


    public StoreTable findByStoreId(Integer storeId) throws BaseException {
        Optional<StoreTable> store = storeRepository.findById(storeId);
        if (store.isEmpty()) {
            throw StoreException.storeIdNull();
        }
        return store.get();
    }


    public StoreTable findByUserId2(UserTable user) throws BaseException {
        Optional<StoreTable> store = storeRepository.findByUserId(user.getUserId());
        if (store.isEmpty()) {
            throw StoreException.storeNameNull();
        }
        return store.get();
    }
}
