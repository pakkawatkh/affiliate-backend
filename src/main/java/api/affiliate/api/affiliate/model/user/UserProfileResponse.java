package api.affiliate.api.affiliate.model.user;

import api.affiliate.api.affiliate.entity.CustomerTable;
import api.affiliate.api.affiliate.entity.StoreTable;
import api.affiliate.api.affiliate.entity.UserTable;
import lombok.Data;

@Data
public class UserProfileResponse {

    private UserTable.Role role = UserTable.Role.USER;

    private String userName;

    private String fullName;

    private String email;

    private String tel;

    private String address;

    private String sub;

    private String district;

    private String province;

    private String postalCode;

    private CustomerTable customer;

    private StoreTable store;
}
