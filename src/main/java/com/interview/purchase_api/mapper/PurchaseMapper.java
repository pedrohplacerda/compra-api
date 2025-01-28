package com.interview.purchase_api.mapper;

import com.interview.purchase_api.dto.PurchaseDTO;
import com.interview.purchase_api.entities.Purchase;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PurchaseMapper {

    PurchaseMapper INSTANCE = Mappers.getMapper(PurchaseMapper.class);

    Purchase toCompra(PurchaseDTO purchaseDTO);

}
