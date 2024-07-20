package com.project.dto.mapper.order;

import com.project.dao_rework.*;
import com.project.dto.request.order.ProcessOrderDetailsDTO;
import com.project.models_rework.SKUHistory;
import com.project.models_rework.SKUHistory_Handle_Order;
import com.project.models_rework.enums.OrderDetailsStatus;
import com.project.models_rework.enums.SKUChangeType;
import lombok.NoArgsConstructor;
import org.jdbi.v3.core.Handle;
import org.mapstruct.Builder;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;

@Mapper(builder = @Builder(disableBuilder = true))
@NoArgsConstructor
public abstract class ProcessOrderDetailsDTOMapper {
    public static final ProcessOrderDetailsDTOMapper INSTANCE = Mappers.getMapper(ProcessOrderDetailsDTOMapper.class);

    public SKUHistory_Handle_Order mapToModel(@Context Handle handle, ProcessOrderDetailsDTO dto) {
        var stock = handle.attach(StockKeepingDAO.class)
                .getById_all(dto.stockId).get(0);
        var details = handle.attach(OrderDetailsDAO.class)
                .getDetailsByOrderIdAndProductId(dto.orderId, stock.getProductId())
                .get(0);
        var history = SKUHistory.builder()
                .type(SKUChangeType.BAN_CHO_KHACH)
                .stockId(dto.stockId)
                .changeValue(-dto.quantity)
                .prevValue(stock.getInStock())
                .createAt(LocalDateTime.now())
                .build();
        var revenue = (details.getPrice() * dto.quantity / details.getQuantity()) - (stock.getUnitPrice() * dto.quantity);
        Integer historyId = handle.attach(SKUHistoryDAO.class).insert(history);
        handle.attach(StockKeepingDAO.class)
                .updateInStockById(dto.stockId, stock.getInStock() - dto.quantity);
        Integer totalPrevious = handle.attach(SKUHistoryHandleOrderDetailsDAO.class)
                .getTotalQuantity(stock.getProductId(), dto.orderId);
        OrderDetailsStatus status = OrderDetailsStatus.CHUA_XU_LY;
        if (totalPrevious + dto.quantity == details.getQuantity()) {
            status = OrderDetailsStatus.DA_XU_LY;
        }
        handle.attach(OrderDetailsDAO.class)
                .setStatus(dto.orderId, stock.getProductId(), status);
        var handleOrder = SKUHistory_Handle_Order.builder()
                .orderId(dto.orderId)
                .revenue(revenue)
                .stockId(dto.stockId)
                .productId(stock.getProductId())
                .quantity(dto.quantity)
                .skuHistoryId(historyId)
                .build();
        return handleOrder;
    };
}
