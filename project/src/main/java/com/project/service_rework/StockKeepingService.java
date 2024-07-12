package com.project.service_rework;

import com.project.dao_rework.ProductDAO;
import com.project.dao_rework.SKUHistoryDAO;
import com.project.dao_rework.StockKeepingDAO;
import com.project.dto.mapper.product.ProductCardDTOMapper;
import com.project.dto.mapper.product.ProductDetailsDTOMapper;
import com.project.dto.mapper.stock.NewStockKeepingDTOMapper;
import com.project.dto.request.stock.NewStockKeepingDTO;
import com.project.dto.response.product.ProductCardDTO;
import com.project.dto.response.product.ProductDetailsDTO;
import com.project.exceptions.custom_exception.MyServletException;
import com.project.exceptions.custom_exception.ProductException;
import com.project.models_rework.SKUHistory;
import com.project.models_rework.enums.SKUChangeType;

import java.time.LocalDateTime;
import java.util.List;

public class StockKeepingService extends AbstractService {
    public int insertNewStock(NewStockKeepingDTO dto) throws MyServletException {
        if (handle.attach(ProductDAO.class).checkExistByProductId(dto.productId).isEmpty()) {
            throw new ProductException(String.format("Không tồn tại product với id: %d", dto.productId), 404);
        }
        var model = NewStockKeepingDTOMapper.INSTANCE.mapToModel(dto);
        Integer stockId = handle.attach(StockKeepingDAO.class).insert(model);
        if (stockId > 0) {
            var history = SKUHistory.builder()
                    .stockId(stockId).type(SKUChangeType.THEM_HANG)
                    .changeValue(dto.inStock).createAt(LocalDateTime.now())
                    .build();
            handle.attach(SKUHistoryDAO.class).insert(history);
        }
        return stockId;
    }
}
