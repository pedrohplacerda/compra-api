package com.entrevista.compra_api.mapper;

import com.entrevista.compra_api.dto.CompraDTO;
import com.entrevista.compra_api.entities.Compra;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CompraMapper {

    CompraMapper INSTANCE = Mappers.getMapper(CompraMapper.class);

    Compra toCompra(CompraDTO compraDTO);

}
