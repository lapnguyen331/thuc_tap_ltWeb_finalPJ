package com.project.service_rework;

import com.project.dao_rework.ProductDAO;
import com.project.dao_rework.StockKeepingDAO;
import com.project.dto.mapper.product.ProductCardDTOMapper;
import com.project.dto.mapper.product.ProductDetailsDTOMapper;
import com.project.dto.mapper.stock.NewStockKeepingDTOMapper;
import com.project.dto.request.stock.NewStockKeepingDTO;
import com.project.dto.response.product.ProductCardDTO;
import com.project.dto.response.product.ProductDetailsDTO;
import com.project.exceptions.custom_exception.MyServletException;
import com.project.exceptions.custom_exception.ProductException;

import java.util.List;

public class StockKeepingService extends AbstractService {
    public int insertNewStock(NewStockKeepingDTO dto) throws MyServletException {
        if (handle.attach(ProductDAO.class).checkExistByProductId(dto.productId).isEmpty()) {
            throw new ProductException(String.format("Không tồn tại product với id: %d", dto.productId), 404);
        }
        var model = NewStockKeepingDTOMapper.INSTANCE.mapToModel(dto);
        return handle.attach(StockKeepingDAO.class).insert(model);
    }
}
