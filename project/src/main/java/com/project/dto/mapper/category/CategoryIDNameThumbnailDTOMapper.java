package com.project.dto.mapper.category;

import com.project.dao_rework.ImageDAO;
import com.project.dto.response.category.CategoryIDNameDTO;
import com.project.dto.response.category.CategoryIDNameThumbnailDTO;
import com.project.models_rework.Category;
import org.jdbi.v3.core.Handle;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(builder = @Builder(disableBuilder = true))
public abstract class CategoryIDNameThumbnailDTOMapper {
    public static final CategoryIDNameThumbnailDTOMapper INSTANCE = Mappers.getMapper(CategoryIDNameThumbnailDTOMapper.class);
    @Mapping(target = "thumbnail", source = "thumbnail", qualifiedByName = "getThumbnail")
    public abstract CategoryIDNameThumbnailDTO mapToDTO(@Context Handle handle, Category category);
    public abstract List<CategoryIDNameThumbnailDTO> mapToDTO(@Context Handle handle, List<Category> categoryList);
    @Named("getThumbnail")
    protected String getThumbnail(@Context Handle handle, Integer thumbnailId) {
        return handle.attach(ImageDAO.class).getImageById(thumbnailId).get(0).getPath();
    }
}
