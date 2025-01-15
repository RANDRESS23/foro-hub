package com.alura.foro_hub.mapper;

import com.alura.foro_hub.dto.TopicoRequest;
import com.alura.foro_hub.dto.TopicoResponse;
import com.alura.foro_hub.entity.TopicoEntity;
import com.alura.foro_hub.model.CustomPage;
import com.alura.foro_hub.model.Topico;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ITopicoEntityMapper {
    Topico toTopico(TopicoEntity topicoEntity);
    Topico topicoRequestToTopico(TopicoRequest topicoRequest);
    TopicoEntity toTopicoEntity(Topico topico);
    TopicoResponse toTopicoResponse(Topico topico);

    default Page<Topico> toPageOfTopicos(Page<TopicoEntity> pageOfBrandsEntity) {
        List<Topico> dtoList = pageOfBrandsEntity.getContent().stream()
                .map(this::toTopico)
                .toList();
        return new PageImpl<>(dtoList, pageOfBrandsEntity.getPageable(), pageOfBrandsEntity.getTotalElements());
    }

    default CustomPage<TopicoResponse> toPageOfTopicoResponse(CustomPage<Topico> pageBrands) {
        List<TopicoResponse> dtoList = pageBrands.getContent().stream()
                .map(this::toTopicoResponse)
                .toList();
        CustomPage<TopicoResponse> customPage = new CustomPage<>();
        customPage.setPageNumber(pageBrands.getPageNumber());
        customPage.setPageSize(pageBrands.getPageSize());
        customPage.setTotalElements(pageBrands.getTotalElements());
        customPage.setTotalPages(pageBrands.getTotalPages());
        customPage.setContent(dtoList);

        return customPage;
    }
}
