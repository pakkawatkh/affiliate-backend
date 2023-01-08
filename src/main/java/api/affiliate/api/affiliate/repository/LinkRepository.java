package api.affiliate.api.affiliate.repository;

import api.affiliate.api.affiliate.entity.LinkTable;
import api.affiliate.api.affiliate.model.link.DetailLinkResponse;
import api.affiliate.api.affiliate.model.link.LinkResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface LinkRepository extends JpaRepository<LinkTable, String> {

    Optional<LinkTable> findById(String id);

    Optional<LinkTable> findByProductIdAndAffiliateIdAndStatusIsTrue(Integer productId, Integer affiliateId);


    @Query("""
            select new api.affiliate.api.affiliate.model.link.LinkResponse(u.fullName,l.amount,l.price,l.creatDate,l.linkId)
            from OrderDetailTable as d
                inner join OrderListTable as ol on ol.orderListId = d.orderListId
                inner join LinkTable l on d.linkId = l.linkId
                inner join AffiliateTable a on a.affiliateId = l.affiliateId
                inner join UserTable u on u.userId = a.userId
            where ol.dlvStatus is true
              and ol.status in ('success','withdraw money')
              and d.productId =:product_id
            group by d.linkId
            """)
    List<LinkResponse> getShareLinkSuccessByProductId(@Param("product_id") Integer productId);

    @Query("""
            select new api.affiliate.api.affiliate.model.link.DetailLinkResponse(sum(d.amount), sum(d.productPrice * d.amount), sum(d.productPrice * l.amount))
            from OrderDetailTable as d
                inner join OrderListTable as ol on ol.orderListId = d.orderListId
                left join LinkTable l on d.linkId = l.linkId
                left join AffiliateTable a on a.affiliateId = l.affiliateId
                left join UserTable u on u.userId = a.userId
            where ol.dlvStatus is true
              and ol.status in ('success','withdraw money')
              and d.productId =:product_id
            group by d.productId
            """)
    DetailLinkResponse getDetailShareLinkSuccessByProductId(@Param("product_id") Integer productId);

}
