package api.affiliate.api.affiliate.mapper;

import api.affiliate.api.affiliate.entity.UserTable;
import api.affiliate.api.affiliate.model.user.UserProfileResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserProfileResponse toUserProfileResponse(UserTable user);

}
