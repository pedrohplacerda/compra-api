package com.entrevista.compra_api.mapper;

import com.entrevista.compra_api.dto.CompraUsuarioDTO;
import com.entrevista.compra_api.entities.CompraUsuario;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CompraUsuarioMapper {

    CompraUsuarioMapper INSTANCE = Mappers.getMapper(CompraUsuarioMapper.class);

    CompraUsuario toCompraUsuario(CompraUsuarioDTO compraUsuarioDTO);

}
