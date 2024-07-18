package com.project.service_rework;

import com.project.dao_rework.ProductDAO;
import com.project.dto.mapper.product.ProductCardDTOMapper;
import com.project.dto.mapper.product.ProductDetailsDTOMapper;
import com.project.dto.mapper.product.ProductIDNameThumbnailDTOMapper;
import com.project.dto.response.product.ProductCardDTO;
import com.project.dto.response.product.ProductDetailsDTO;
import com.project.dto.response.product.ProductIDNameThumbnailDTO;

import java.util.List;

public class ProductService extends AbstractService {
    public List<ProductCardDTO> getProductCardOfCategory(Integer categoryId) {
        ProductDAO productDAO = handle.attach(ProductDAO.class);
        var list = productDAO.getByCategoryId(categoryId);
        return ProductCardDTOMapper.INSTANCE.mapToDTO(handle, list);
    }
    public List<ProductCardDTO> getTop4() {
        ProductDAO productDAO = handle.attach(ProductDAO.class);
        var list = productDAO.getTop(4);
        return ProductCardDTOMapper.INSTANCE.mapToDTO(handle, list);
    }
    public ProductDetailsDTO getDetailsByProductId(Integer productId) {
        var product = handle.attach(ProductDAO.class).getById_all(productId).get(0);
        return ProductDetailsDTOMapper.INSTANCE.mapToDTO(handle, product);
    }
    public List<ProductIDNameThumbnailDTO> getProductLikeName(String name) {
        String query = "'%%'";
        if (name != null && !name.isEmpty()) {
            query = "'%" + name + "%'";
        }
        System.out.println("search with query: "+query);
        var list = handle.attach(ProductDAO.class).getByName_id_name_thumbnail(query);
        return ProductIDNameThumbnailDTOMapper.INSTANCE.mapToDTO(handle, list);
    }
}
