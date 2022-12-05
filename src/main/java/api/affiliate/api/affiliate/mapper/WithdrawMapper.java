package api.affiliate.api.affiliate.mapper;

import api.affiliate.api.affiliate.entity.StoreTable;
import api.affiliate.api.affiliate.entity.UserTable;
import api.affiliate.api.affiliate.entity.WithdrawTable;
import api.affiliate.api.affiliate.model.store.StoreRequest;
import api.affiliate.api.affiliate.model.user.UserProfileResponse;
import api.affiliate.api.affiliate.model.withdraw.WithdrawResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WithdrawMapper {

    WithdrawResponse toWithdrawResponse(WithdrawTable withdraw);

    StoreRequest toStoreRequest(StoreTable storeTable);

    List<WithdrawResponse> toWithdrawResponse(List<WithdrawTable> withdraw);

}
