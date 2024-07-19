package com.project.service_rework;

import com.project.dao_rework.CategoryDAO;
import com.project.dto.mapper.category.CategoryIDNameDTOMapper;
import com.project.dto.mapper.category.CategoryIDNameThumbnailDTOMapper;
import com.project.dto.response.category.CategoryIDNameDTO;
import com.project.dto.response.category.CategoryIDNameThumbnailDTO;
import com.project.dto.response.stock.SKURowDTO;
import com.project.models_rework.Order;

import java.util.List;

public class OrderService extends AbstractService {
    public List<SKURowDTO> getHandleOrderDetailsDTO() {
        return null;
    }
}
