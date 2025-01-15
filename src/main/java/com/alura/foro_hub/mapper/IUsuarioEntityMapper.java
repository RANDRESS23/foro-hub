package com.alura.foro_hub.mapper;

import com.alura.foro_hub.dto.UsuarioRequest;
import com.alura.foro_hub.dto.UsuarioResponse;
import com.alura.foro_hub.entity.UsuarioEntity;
import com.alura.foro_hub.model.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IUsuarioEntityMapper {
    Usuario toUsuario(UsuarioEntity usuarioEntity);
    Usuario usuarioRequestToUsuario(UsuarioRequest usuarioRequest);
    UsuarioEntity toUsuarioEntity(Usuario usuario);
    UsuarioResponse toUsuarioResponse(Usuario usuario);
}
