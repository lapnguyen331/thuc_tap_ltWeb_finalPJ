package com.project.service_rework;

import com.project.dao_rework.ProductDAO;
import com.project.dao_rework.SKUHistoryDAO;
import com.project.dao_rework.StockKeepingDAO;
import com.project.dto.mapper.product.ProductCardDTOMapper;
import com.project.dto.mapper.product.ProductDetailsDTOMapper;
import com.project.dto.mapper.stock.NewStockKeepingDTOMapper;
import com.project.dto.mapper.stock.SKUHistoryDTOMapper;
import com.project.dto.mapper.stock.SKURowDTOMapper;
import com.project.dto.request.stock.ChangeInStockDTO;
import com.project.dto.request.stock.NewStockKeepingDTO;
import com.project.dto.response.product.ProductCardDTO;
import com.project.dto.response.product.ProductDetailsDTO;
import com.project.dto.response.stock.SKUHistoryDTO;
import com.project.dto.response.stock.SKURowDTO;
import com.project.exceptions.custom_exception.MyServletException;
import com.project.exceptions.custom_exception.ProductException;
import com.project.exceptions.custom_exception.StockKeepingException;
import com.project.models_rework.SKUHistory;
import com.project.models_rework.enums.SKUChangeType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class StockKeepingService extends AbstractService {
    public Integer insertNewStock(NewStockKeepingDTO dto) throws MyServletException {
        if (handle.attach(ProductDAO.class).checkExistByProductId(dto.productId).isEmpty()) {
            throw new ProductException(String.format("Không tồn tại product với id: %d", dto.productId), 404);
        }
        var model = NewStockKeepingDTOMapper.INSTANCE.mapToModel(dto);
        Integer stockId = handle.attach(StockKeepingDAO.class).insert(model);
        if (stockId > 0) {
            var history = SKUHistory.builder()
                    .stockId(stockId).type(SKUChangeType.THEM_SKU_MOI)
                    .prevValue(0).changeValue(dto.inStock)
                    .createAt(LocalDateTime.now())
                    .build();
            handle.attach(SKUHistoryDAO.class).insert(history);
        }
        return stockId;
    }

    public SKURowDTO getSKURowDTO(Integer stockId) throws MyServletException {
        if (handle.attach(StockKeepingDAO.class).checkExistsById(stockId).isEmpty()) {
            throw new StockKeepingException(String.format("Không tồn tại sku với id: %d", stockId), 404);
        }
        var model = handle.attach(StockKeepingDAO.class).getById_all(stockId).get(0);
        return SKURowDTOMapper.INSTANCE.mapToDTO(handle, model);
    }

    public List<SKURowDTO> getAllSKURowDTO() throws MyServletException {
        var list = handle.attach(StockKeepingDAO.class).getAll_all();
        return SKURowDTOMapper.INSTANCE.mapToDTO(handle, list);
    }

    public Integer changeInStock(ChangeInStockDTO dto) throws MyServletException {
        if (handle.attach(StockKeepingDAO.class).checkExistsById(dto.stockId).isEmpty()) {
            throw new StockKeepingException(String.format("Không tồn tại sku với id: %d", dto.stockId), 404);
        }
        Integer currentInStock = handle.attach(StockKeepingDAO.class).getInStockById(dto.stockId);
        Integer changeValue = dto.inStock - currentInStock;
        switch (dto.type) {
            case THEM_HANG -> {
                if (changeValue < 0)
                    throw new StockKeepingException(String.format("Loại thay đổi [%s] không phù hợp với số lượng thay đổi: %d"
                    , dto.type, changeValue), 400);
            }
            case HET_HAN -> {
                if (changeValue > 0)
                    throw new StockKeepingException(String.format("Loại thay đổi [%s] không phù hợp với số lượng thay đổi: %d"
                            , dto.type, changeValue), 400);
                // Kiểm tra xem lô đã hết hạn thật chưa?
                LocalDate expiredDate = handle.attach(StockKeepingDAO.class)
                        .getExpiredDateById(dto.stockId);
                if (expiredDate.isAfter(LocalDate.now()))
                    throw new StockKeepingException(String.format("SKU này chưa hết hạn! Ngày hết hạn: %s", expiredDate), 400);
            }
            default -> {
                throw new StockKeepingException(String.format("Loại thay đổi [%s] không phù hợp!", dto.type), 400);
            }
        }
        handle.attach(StockKeepingDAO.class).updateInStockById(dto.stockId, dto.inStock);
        var history = SKUHistory.builder()
                .stockId(dto.stockId).type(dto.type).prevValue(currentInStock)
                .changeValue(changeValue).createAt(LocalDateTime.now())
                .build();
        return handle.attach(SKUHistoryDAO.class).insert(history);
    }

    public List<SKUHistoryDTO> getStockHistoryInMonthOfStockKeeping(Integer stockId, Integer month) throws MyServletException {
        var modelList = handle.attach(SKUHistoryDAO.class)
                .getByStockIdAndCreateAtInMonth(stockId, month);
        return SKUHistoryDTOMapper.INSTANCE.mapToDTO(handle, modelList);
    }

    public List<SKUHistoryDTO> getAllSKUHistory() throws MyServletException {
        var modelList = handle.attach(SKUHistoryDAO.class)
                .getAllSKUHistory_all();
        return SKUHistoryDTOMapper.INSTANCE.mapToDTO(handle, modelList);
    }
}
