package api.affiliate.api.affiliate.service;

import api.affiliate.api.affiliate.entity.LinkTable;
import api.affiliate.api.affiliate.exception.LinkException;
import api.affiliate.api.affiliate.exception.ProductException;
import api.affiliate.api.affiliate.model.link.DetailLinkResponse;
import api.affiliate.api.affiliate.model.link.LinkResponse;
import api.affiliate.api.affiliate.repository.LinkRepository;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LinkService {

    private final LinkRepository linkRepository;

    @SneakyThrows
    public LinkTable findByLinkId(String linkId) {
        Optional<LinkTable> link = linkRepository.findById(linkId);
        if (link.isEmpty()){
            throw LinkException.linkIdIsNull();
        }
        return link.get();
    }


    public List<LinkResponse> getShareLinkSuccessByProductId(Integer productId) {
        List<LinkResponse> link = linkRepository.getShareLinkSuccessByProductId(productId);
        return link;
    }

    public DetailLinkResponse getDetailShareLinkSuccessByProductId(Integer productId) {
        DetailLinkResponse link = linkRepository.getDetailShareLinkSuccessByProductId(productId);
        return link;
    }



    @SneakyThrows
    public LinkTable createLink(Integer productId, Integer storeId, Integer affiliateId, Float price){
        Optional<LinkTable> pdAndAffiliate = linkRepository.findByProductIdAndAffiliateIdAndStatusIsTrue(productId, affiliateId);
        if (pdAndAffiliate.isEmpty()){
            LinkTable shareTable = new LinkTable();
            shareTable.setProductId(productId);
            shareTable.setStoreId(storeId);
            shareTable.setAffiliateId(affiliateId);
            shareTable.setPrice(price);
            try {
                return linkRepository.save(shareTable);
            }catch (Exception e){
                throw ProductException.productNull();
            }
        }else {
            return pdAndAffiliate.get();
        }

    }



}
