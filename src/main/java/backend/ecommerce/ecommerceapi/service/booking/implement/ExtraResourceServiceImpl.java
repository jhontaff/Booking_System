package backend.ecommerce.ecommerceapi.service.booking.implement;

import backend.ecommerce.ecommerceapi.config.exception.BookingException;
import backend.ecommerce.ecommerceapi.entity.booking.ExtraResource;
import backend.ecommerce.ecommerceapi.repository.booking.ExtraResourceRepository;
import backend.ecommerce.ecommerceapi.service.booking.ExtraResourceService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExtraResourceServiceImpl implements ExtraResourceService {

    private final ExtraResourceRepository extraResourceRepository;

    private static final String EXTRARESOURCENOTFOUNDMESSAGE = "Extra resource with id %d not found";

    public ExtraResourceServiceImpl(ExtraResourceRepository extraResourceRepository) {
        this.extraResourceRepository = extraResourceRepository;
    }

    @Override
    public List<ExtraResource> getAllExtraResources() {
        return this.extraResourceRepository.findAll();
    }

    @Override
    public ExtraResource getExtraResourceById(Long extraResourceId) {
        return this.extraResourceRepository.findById(extraResourceId).orElseThrow(
                () -> new RuntimeException(EXTRARESOURCENOTFOUNDMESSAGE.formatted(extraResourceId))
        );
    }

    @Override
    public void minusUpdateExtraResource(Long extraResourceId) {
        ExtraResource extraResource = this.extraResourceRepository.findById(extraResourceId).orElseThrow(
                () -> new RuntimeException(EXTRARESOURCENOTFOUNDMESSAGE.formatted(extraResourceId))
        );
        if (isExtraResourceAvailable(extraResourceId).equals(Boolean.TRUE)) {
            extraResource.setQuantity(extraResource.getQuantity() - 1);
            this.extraResourceRepository.save(extraResource);
        } else {
            throw new BookingException("Extra resource is fully booked");
        }
    }

    @Override
    public void plusUpdateExtraResource(Long extraResourceId) {
        ExtraResource extraResource = this.extraResourceRepository.findById(extraResourceId).orElseThrow(
                () -> new RuntimeException(EXTRARESOURCENOTFOUNDMESSAGE.formatted(extraResourceId))
        );
        extraResource.setQuantity(extraResource.getQuantity() + 1);
        this.extraResourceRepository.save(extraResource);
    }

    @Override
    public Boolean isExtraResourceAvailable(Long extraResourceId) {
        ExtraResource extraResource = this.extraResourceRepository.findById(extraResourceId).orElseThrow(
                () -> new RuntimeException(EXTRARESOURCENOTFOUNDMESSAGE.formatted(extraResourceId))
        );
        if (extraResource.getQuantity() > 0) {
            return Boolean.TRUE;
        } else {
            throw new BookingException("No extra resource available: " + extraResource.getResourceName());
        }
    }
}
