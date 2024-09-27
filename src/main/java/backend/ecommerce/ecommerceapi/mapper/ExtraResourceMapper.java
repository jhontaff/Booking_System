package backend.ecommerce.ecommerceapi.mapper;

import backend.ecommerce.ecommerceapi.dto.booking.ExtraResourceDto;
import backend.ecommerce.ecommerceapi.entity.booking.ExtraResource;
import backend.ecommerce.ecommerceapi.service.booking.ExtraResourceService;

import java.util.Optional;

public class ExtraResourceMapper {

    private final ExtraResourceService extraResourceService;

    public ExtraResourceMapper(ExtraResourceService extraResourceService) {
        this.extraResourceService = extraResourceService;
    }

    public ExtraResourceDto toDto(ExtraResource extraResource) {
        ExtraResourceDto extraResourceDto = new ExtraResourceDto();
        extraResourceDto.setExtraResourceId(extraResource.getExtraResourceId());
        return extraResourceDto;
    }

    public ExtraResource toEntity(ExtraResourceDto extraResourceDto) {
        return this.extraResourceService.getExtraResourceById(extraResourceDto.getExtraResourceId());
    }

}
