package api.affiliate.api.affiliate.mapper;

import api.affiliate.api.affiliate.entity.OrderListTable;
import api.affiliate.api.affiliate.entity.UserTable;
import api.affiliate.api.affiliate.model.order.OrderResponse;
import api.affiliate.api.affiliate.model.user.UserProfileResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderListMapper {

    OrderResponse toOrderResponse(OrderListTable orderListTable);
    List<OrderResponse> toOrderResponse(List<OrderListTable> orderListTable);




}
