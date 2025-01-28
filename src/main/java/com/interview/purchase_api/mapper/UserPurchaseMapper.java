package com.interview.purchase_api.mapper;

import com.interview.purchase_api.dto.UserPurchaseDTO;
import com.interview.purchase_api.entities.UserPurchase;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserPurchaseMapper {

    UserPurchaseMapper INSTANCE = Mappers.getMapper(UserPurchaseMapper.class);

    UserPurchase mapToBuyUser(UserPurchaseDTO userPurchaseDTO);

}
