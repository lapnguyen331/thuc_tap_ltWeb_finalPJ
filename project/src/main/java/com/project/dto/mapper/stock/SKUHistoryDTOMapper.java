package com.project.dto.mapper.stock;

import com.project.dao_rework.*;
import com.project.dto.response.stock.SKUHistoryDTO;
import com.project.dto.response.stock.SKURowDTO;
import com.project.exceptions.custom_exception.MyServletException;
import com.project.exceptions.custom_exception.ProducerException;
import com.project.exceptions.custom_exception.ProductException;
import com.project.exceptions.custom_exception.StockKeepingException;
import com.project.models_rework.OrderItem;
import com.project.models_rework.SKUHistory;
import com.project.models_rework.StockKeeping;
import com.project.service_rework.UploadService;
import org.jdbi.v3.core.Handle;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Mapper(builder = @Builder(disableBuilder = true))
public abstract class SKUHistoryDTOMapper {
    public static final SKUHistoryDTOMapper INSTANCE = Mappers.getMapper(SKUHistoryDTOMapper.class);

    @Mapping(target = "product", source = "stockId", qualifiedByName = "mapToProductDTO")
    @Mapping(target = "orderItem", source = "id", qualifiedByName = "mapToOrderItemDTO")
    public abstract SKUHistoryDTO mapToDTO(@Context Handle handle, SKUHistory model) throws MyServletException;
    public abstract List<SKUHistoryDTO> mapToDTO(@Context Handle handle, List<SKUHistory> model) throws MyServletException;

    @Named("mapToProductDTO")
    protected SKUHistoryDTO.Product mapToProduct(@Context Handle handle, Integer stockId) throws MyServletException {
        if (handle.attach(StockKeepingDAO.class).checkExistsById(stockId).isEmpty()) {
            throw new StockKeepingException(String.format("Không tồn tại sku với id: %d", stockId), 404);
        }
        Integer productId = handle.attach(StockKeepingDAO.class).getProductIdById(stockId);
        if (handle.attach(ProductDAO.class).checkExistByProductId(productId).isEmpty()) {
            throw new ProductException(String.format("Không tồn tại product với id: %d", productId), 404);
        }
        var product = handle.attach(ProductDAO.class).getById_id_name_thumbnail(productId).get(0);
        var listThumbnail = handle.attach(ImageDAO.class).getImageById(product.getThumbnail());
        String thumbnail_path = listThumbnail.isEmpty() ? null : listThumbnail.get(0).getPath();
        var uploadService = new UploadService();
        String thumbnail_link = null;
        try {
            thumbnail_link = uploadService.getURL(thumbnail_path);
        } catch (Exception e) {
            throw new MyServletException("Lỗi server khi cố lấy thông tin ảnh", 500);
        }
        return SKUHistoryDTO.Product.builder()
                .thumbnail(thumbnail_link)
                .name(product.getName())
                .id(product.getId())
                .build();
    }

    @Named("mapToOrderItemDTO")
    protected SKUHistoryDTO.OrderItem mapToOrderItem(@Context Handle handle, Integer skuHistoryId) throws MyServletException {
        Integer orderItemId = handle.attach(SKUHistoryDAO.class).getRelatedOrderItemById(skuHistoryId);
        if (orderItemId == null) return null;
        OrderItem orderItem = handle.attach(OrderItemDAO.class).getById_all(orderItemId).get(0);
        return SKUHistoryDTO.OrderItem.builder()
                .orderId(orderItem.getOrderId())
                .id(orderItemId)
                .build();
    }
}
