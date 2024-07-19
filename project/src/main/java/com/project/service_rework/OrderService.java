package com.project.service_rework;

import com.project.dao_rework.*;
import com.project.dto.mapper.category.CategoryIDNameDTOMapper;
import com.project.dto.mapper.category.CategoryIDNameThumbnailDTOMapper;
import com.project.dto.mapper.order.HandleOrderDetailsDTOMapper;
import com.project.dto.response.category.CategoryIDNameDTO;
import com.project.dto.response.category.CategoryIDNameThumbnailDTO;
import com.project.dto.response.order.HandleOrderDetailsDTO;
import com.project.dto.response.stock.SKURowDTO;
import com.project.models_rework.Order;
import com.project.models_rework.SKUHistory_Handle_Order;

import java.util.ArrayList;
import java.util.List;

public class OrderService extends AbstractService {
    public List<HandleOrderDetailsDTO> getHandleOrderDetailsDTO(Integer orderId) {
        var handles = handle.attach(SKUHistoryHandleOrderDetailsDAO.class)
                .getByOrderId_orderId_productId_stockId(orderId);
        List<HandleOrderDetailsDTO> list = new ArrayList<>();
        for (var h : handles) {
            Integer productId = h.getProductId();
            Integer stockId = h.getStockId();
            var r = handle.attach(SKUHistoryHandleOrderDetailsDAO.class)
                    .getByOrderIdAndProductIdAndStockId_all(orderId, productId, stockId);
            list.add(HandleOrderDetailsDTOMapper.INSTANCE.mapToDTO(handle, r));
        }
        return list;
}
    public static void main(String[] args) {
        var instance = new OrderService();
        System.out.println(instance.getHandleOrderDetailsDTO(34));
    }
}
