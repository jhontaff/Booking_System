package backend.ecommerce.ecommerceapi.service.booking;

import backend.ecommerce.ecommerceapi.entity.booking.ExtraResource;

import java.util.List;

public interface ExtraResourceService {

    public List<ExtraResource> getAllExtraResources();
    public ExtraResource getExtraResourceById(Long extraResourceId);
    public void plusUpdateExtraResource(Long extraResourceId);
    public void minusUpdateExtraResource(Long extraResourceId);
    public Boolean isExtraResourceAvailable(Long extraResourceId);

}
