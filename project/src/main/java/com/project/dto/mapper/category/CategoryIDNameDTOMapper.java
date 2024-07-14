package com.project.dto.mapper.category;

import com.project.dto.response.category.CategoryIDNameDTO;
import com.project.models_rework.Category;
import lombok.NoArgsConstructor;
import org.jdbi.v3.core.Handle;
import org.mapstruct.Builder;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(builder = @Builder(disableBuilder = true))
@NoArgsConstructor
public abstract class CategoryIDNameDTOMapper {
    public static final CategoryIDNameDTOMapper INSTANCE = Mappers.getMapper(CategoryIDNameDTOMapper.class);
    public abstract CategoryIDNameDTO mapToDTO(@Context Handle handle, Category category);
    public abstract List<CategoryIDNameDTO> mapToDTO(@Context Handle handle, List<Category> categories);
}
