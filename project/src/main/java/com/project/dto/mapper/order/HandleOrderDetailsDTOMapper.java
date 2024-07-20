package com.project.dto.mapper.order;

import com.project.dao_rework.ImageDAO;
import com.project.dao_rework.ProductDAO;
import com.project.dao_rework.SKUHistoryDAO;
import com.project.dao_rework.StockKeepingDAO;
import com.project.dto.response.order.HandleOrderDetailsDTO;
import com.project.models_rework.SKUHistory_Handle_Order;
import com.project.service_rework.UploadService;
import org.jdbi.v3.core.Handle;
import org.mapstruct.Builder;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(builder = @Builder(disableBuilder = true))
public abstract class HandleOrderDetailsDTOMapper {
    public static final HandleOrderDetailsDTOMapper INSTANCE = Mappers.getMapper(HandleOrderDetailsDTOMapper.class);
    public HandleOrderDetailsDTO mapToDTO(@Context Handle handle, List<SKUHistory_Handle_Order> list) {
        var instance = list.get(0);
        var product = handle.attach(ProductDAO.class)
                .getById_id_name_thumbnail(instance.getProductId()).get(0);
        var history = handle.attach(SKUHistoryDAO.class)
                .getById_all(instance.getSkuHistoryId()).get(0);
        var expiredDate = handle.attach(StockKeepingDAO.class)
                .getExpiredDateById(history.getStockId());
        var path = handle.attach(ImageDAO.class)
                .getImageById(product.getThumbnail()).get(0).getPath();
        var uploadService = new UploadService();
        String thumbnail = null;
        try {
            thumbnail = uploadService.getURL(path);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return HandleOrderDetailsDTO.builder()
                .orderId(instance.getOrderId())
                .stockId(history.getStockId())
                .quantity(list.size())
                .thumbnail(thumbnail)
                .productName(product.getName())
                .expiredDate(expiredDate)
                .build();
    }
}
