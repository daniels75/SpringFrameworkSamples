package org.daniels.spring.mvc.rest.api.v1.mapper;

import org.daniels.spring.mvc.rest.api.v1.model.VendorDTO;
import org.daniels.spring.mvc.rest.domain.Vendor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface VendorMapper {

    VendorMapper INSTANCE = Mappers.getMapper(VendorMapper.class);

    VendorDTO vendorToVendorDTO(Vendor vendor);

    Vendor vendorDTOtoVendor(VendorDTO vendorDTO);
}
