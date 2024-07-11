package com.project.service_rework;

import com.project.dao_rework.CategoryDAO;
import com.project.dto.mapper.category.CategoryIDNameDTOMapper;
import com.project.dto.mapper.category.CategoryIDNameThumbnailDTOMapper;
import com.project.dto.response.category.CategoryIDNameDTO;
import com.project.dto.response.category.CategoryIDNameThumbnailDTO;

import java.util.List;

public class CategoryService extends AbstractService {
    public List<CategoryIDNameDTO> getAll_id_name() {
        var list = getHandle().attach(CategoryDAO.class).getAll_id_name();
        return CategoryIDNameDTOMapper.INSTANCE.mapToDTO(getHandle(), list);
    }
    public List<CategoryIDNameThumbnailDTO> getAll_id_name_thumbnail() {
        var list = getHandle().attach(CategoryDAO.class).getAll_id_name_thumbnail();
        return CategoryIDNameThumbnailDTOMapper.INSTANCE.mapToDTO(getHandle(), list);
    }
}
