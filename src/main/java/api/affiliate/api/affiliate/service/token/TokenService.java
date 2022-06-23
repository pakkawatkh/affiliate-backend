package api.affiliate.api.affiliate.service.token;

import api.affiliate.api.affiliate.entity.StoreTable;
import api.affiliate.api.affiliate.entity.UserTable;
import api.affiliate.api.affiliate.exception.BaseException;
import api.affiliate.api.affiliate.exception.CustomerException;
import api.affiliate.api.affiliate.exception.UserException;
import api.affiliate.api.affiliate.repository.StoreRepository;
import api.affiliate.api.affiliate.repository.UserRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@Service
public class TokenService {

    private final UserRepository userRepository;
    private final StoreRepository storeRepository;

    @Value("${app.token.secret}")
    private String secret;

    @Value("${app.token.issuer}")
    private String issuer;

    public TokenService(UserRepository userRepository, StoreRepository storeRepository) {
        this.userRepository = userRepository;
        this.storeRepository = storeRepository;
    }

    public String tokenizeUser(UserTable user) {

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, 60);
        Date expiresAt = calendar.getTime();

        return JWT.create()
                .withIssuer(issuer)
                .withClaim("principal", user.getUserId())
                .withClaim("role", "USER")
                .withExpiresAt(expiresAt).sign(algorithm());
    }


    public DecodedJWT verify(String token) {
        try {
            JWTVerifier verifier = JWT.require(algorithm()).withIssuer(issuer).build(); //Reusable verifier instance
            return verifier.verify(token);

        } catch (Exception e) {
            return null;
        }
    }

    private Algorithm algorithm() {
        return Algorithm.HMAC256(secret);
    }


    //generate token for user
    public UserTable getUserByToken() throws BaseException {
        String userId = this.userId();  // id from token

        Optional<UserTable> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw UserException.userNameNull();
        }

        return user.get();
    }


    //generate token for user
//    public StoreTable getStoreByToken() throws BaseException {
//        String storeId = this.userId();  // id from token
//
//        Optional<StoreTable> store = storeRepository.findById(storeId);
//        if (store.isEmpty()) {
//            throw CustomerException.customerNameNull();
//        }
//
//        return store.get();
//    }


    public String userId() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        System.out.println(authentication);
        return (String) authentication.getPrincipal();
    }

    public String role() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        return authentication.getAuthorities().toString();
    }
}
