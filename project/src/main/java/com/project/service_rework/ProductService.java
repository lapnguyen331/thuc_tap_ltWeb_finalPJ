package com.project.service_rework;

import com.project.dao_rework.ProductDAO;
import com.project.dto.mapper.product.ProductCardDTOMapper;
import com.project.dto.response.product.ProductCardDTO;
import java.util.List;

public class ProductService extends AbstractService {
    public List<ProductCardDTO> getProductCardOfCategory(Integer categoryId) {
        ProductDAO productDAO = getHandle().attach(ProductDAO.class);
        var list = productDAO.getByCategoryId(categoryId);
        return ProductCardDTOMapper.INSTANCE.mapToDTO(getHandle(), list);
    }
    public List<ProductCardDTO> getTop4() {
        ProductDAO productDAO = getHandle().attach(ProductDAO.class);
        var list = productDAO.getTop(4);
        return ProductCardDTOMapper.INSTANCE.mapToDTO(getHandle(), list);
    }
}
