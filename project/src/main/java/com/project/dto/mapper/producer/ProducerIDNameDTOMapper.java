package com.project.dto.mapper.producer;

import com.project.dto.response.category.CategoryIDNameDTO;
import com.project.dto.response.producer.ProducerIDNameDTO;
import com.project.models_rework.Category;
import com.project.models_rework.Producer;
import lombok.NoArgsConstructor;
import org.jdbi.v3.core.Handle;
import org.mapstruct.Builder;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(builder = @Builder(disableBuilder = true))
@NoArgsConstructor
public abstract class ProducerIDNameDTOMapper {
    public static final ProducerIDNameDTOMapper INSTANCE = Mappers.getMapper(ProducerIDNameDTOMapper.class);
    public abstract ProducerIDNameDTO mapToDTO(@Context Handle handle, Producer model);
    public abstract List<ProducerIDNameDTO> mapToDTO(@Context Handle handle, List<Producer> model);
}
