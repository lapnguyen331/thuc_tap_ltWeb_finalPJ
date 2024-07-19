package com.project.service_rework;

import com.project.dao_rework.*;
import com.project.dto.mapper.order.HandleOrderDetailsDTOMapper;
import com.project.dto.mapper.order.ProcessOrderDetailsDTOMapper;
import com.project.dto.mapper.stock.NewStockKeepingDTOMapper;
import com.project.dto.mapper.stock.SKUHistoryDTOMapper;
import com.project.dto.mapper.stock.SKURowDTOMapper;
import com.project.dto.request.order.ProcessOrderDetailsDTO;
import com.project.dto.request.stock.ChangeInStockDTO;
import com.project.dto.request.stock.NewStockKeepingDTO;
import com.project.dto.request.stock.data_table.DataTableFilterDTO;
import com.project.dto.response.dataTable.DataTableDTO;
import com.project.dto.response.order.HandleOrderDetailsDTO;
import com.project.dto.response.stock.SKUHistoryDTO;
import com.project.dto.response.stock.SKURowDTO;
import com.project.exceptions.custom_exception.MyServletException;
import com.project.exceptions.custom_exception.ProductException;
import com.project.exceptions.custom_exception.StockKeepingException;
import com.project.models_rework.SKUHistory;
import com.project.models_rework.enums.SKUChangeType;
import org.jdbi.v3.core.Handle;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class StockKeepingService extends AbstractService {

    public StockKeepingService() {
    }

    public StockKeepingService(Handle handle) {
        super(handle);
    }

    public List<HandleOrderDetailsDTO> getHandleOrderDetailsDTO(Integer orderId) {
        var productIds = handle.attach(OrderDetailsDAO.class)
                .getById_productId(orderId);
        List<HandleOrderDetailsDTO> list = new ArrayList<>();
        for (var id : productIds) {
            var r = handle.attach(SKUHistoryHandleOrderDetailsDAO.class)
                    .getByOrderIdAndProductId_all(orderId, id);
            list.add(HandleOrderDetailsDTOMapper.INSTANCE.mapToDTO(handle, r));
        }
        return list;
    }

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

    public Integer insertHandleOrderDetails(ProcessOrderDetailsDTO dto) {
        var handleOrder = ProcessOrderDetailsDTOMapper.INSTANCE.mapToModel(handle, dto);
        return handle.attach(SKUHistoryHandleOrderDetailsDAO.class).insert(handleOrder);
    }

    public DataTableDTO getAllSKUHistory(DataTableFilterDTO filterDTO) throws MyServletException {
        Map<Integer, String> orderMap = new LinkedHashMap<>();
        filterDTO.order.forEach(order -> {
            var column = filterDTO.columns.get(order.getColumn());
            orderMap.put(order.column, order.dir);
        });
        String ORDER_BY = "";
        if (!orderMap.isEmpty()) {
            String sequence = "";
            for (var entry : orderMap.entrySet()) {
                Integer index = entry.getKey();
                String dir = entry.getValue();
                String columnName = SKUHistoryDAO.dataTable_ToColumns.get(filterDTO.columns.get(index).name);
                sequence += columnName + " " + dir + ", ";
            }
            sequence = sequence.substring(0, sequence.length() - 2); //remove ", "
            ORDER_BY = "ORDER BY "+sequence;
        }
        var list = handle.attach(SKUHistoryDAO.class).getAllSKUHistory_all(ORDER_BY, filterDTO.length, filterDTO.start);
        var dto = SKUHistoryDTOMapper.INSTANCE.mapToDTO(handle, list);
        var toTal = handle.attach(SKUHistoryDAO.class)
                .getTotalCount();
        var filterTotal = handle.attach(SKUHistoryDAO.class)
                .getCountFilter("");
        return DataTableDTO.builder()
                .draw(filterDTO.draw)
                .data(dto).recordsFiltered(filterTotal)
                .recordsTotal(toTal).build();
    }
}
