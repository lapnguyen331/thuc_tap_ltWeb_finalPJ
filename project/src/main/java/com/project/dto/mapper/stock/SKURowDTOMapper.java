package com.project.dto.mapper.stock;

import com.project.dao_rework.ImageDAO;
import com.project.dao_rework.ProducerDAO;
import com.project.dao_rework.ProductDAO;
import com.project.dao_rework.SKUHistoryDAO;
import com.project.dto.request.stock.NewStockKeepingDTO;
import com.project.dto.response.stock.SKURowDTO;
import com.project.exceptions.custom_exception.MyServletException;
import com.project.exceptions.custom_exception.ProducerException;
import com.project.exceptions.custom_exception.ProductException;
import com.project.models_rework.Product;
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
public abstract class SKURowDTOMapper {
    public static final SKURowDTOMapper INSTANCE = Mappers.getMapper(SKURowDTOMapper.class);

    @Mapping(target = "stockId", source = "id")
    @Mapping(target = "availableQuantity", source = "inStock")
    @Mapping(target = "product", source = "productId", qualifiedByName = "mapToProductDTO")
    @Mapping(target = "producer", source= "producerId", qualifiedByName = "mapToProducerDTO")
    @Mapping(target = "stat", source = "id", qualifiedByName = "mapToStatDTO")
    @Mapping(target = "lastChange", source = "id", qualifiedByName = "getLatestDate")
    public abstract SKURowDTO mapToDTO(@Context Handle handle, StockKeeping model) throws MyServletException;
    public abstract List<SKURowDTO> mapToDTO(@Context Handle handle, List<StockKeeping> models) throws MyServletException;

    @Named("mapToProductDTO")
    protected SKURowDTO.Product mapToProduct(@Context Handle handle, Integer productId) throws MyServletException {
        if (handle.attach(ProductDAO.class).checkExistByProductId(productId).isEmpty()) {
            throw new ProductException(String.format("Không tồn tại product với id: %d", productId), 404);
        }
        var product = handle.attach(ProductDAO.class).getById_id_name_thumbnail(productId).get(0);
        var listThumbnail = handle.attach(ImageDAO.class).getImageById(product.getThumbnail());
        String thumbnail_path = listThumbnail.isEmpty() ? null : listThumbnail.get(0).getPath();
        String thumbnail_link = null;
        var uploadService = new UploadService();
        try {
            thumbnail_link = uploadService.getURL(thumbnail_path);
        } catch (Exception e) {
            throw new MyServletException("Lỗi server khi cố lấy thông tin ảnh", 500);
        }
        return SKURowDTO.Product.builder()
                .thumbnail(thumbnail_link)
                .name(product.getName())
                .id(product.getId())
                .build();
    }

    @Named("mapToProducerDTO")
    protected SKURowDTO.Producer mapToProducer(@Context Handle handle, Integer producerId) throws MyServletException {
        if (handle.attach(ProducerDAO.class).checkExistsById(producerId).isEmpty()) {
            throw new ProducerException(String.format("Không tồn tại nhà cung cấp với id: %d", producerId), 404);
        }
        var producer = handle.attach(ProducerDAO.class).getById_all(producerId).get(0);
        return SKURowDTO.Producer.builder()
                .name(producer.getName())
                .id(producer.getId())
                .build();
    }

    @Named("mapToStatDTO")
    protected SKURowDTO.MonthStat mapToStat(@Context Handle handle, Integer stockId) {
        Integer month = LocalDate.now().getMonthValue();
        List<SKUHistory> historyList = handle.attach(SKUHistoryDAO.class)
                .getByRelatedWithOrderItemAndByStockIdAndInMonth_all(stockId, month);
        Float revenue = 0f;
        Integer sellQuantity = 0;
        if (!historyList.isEmpty()) {
            revenue = handle.attach(SKUHistoryDAO.class)
                    .getTotalRevenueBySkuHistoryIds(historyList.stream().mapToInt(SKUHistory::getId).boxed().toList());
            sellQuantity = handle.attach(SKUHistoryDAO.class)
                    .getTotalChangeValueBySkuHistoryIds(historyList.stream().mapToInt(SKUHistory::getId).boxed().toList());
        }
        return SKURowDTO.MonthStat.builder()
                .month(month).sellQuantity(sellQuantity)
                .totalRevenue(revenue).build();
    }

    @Named("getLatestDate")
    protected LocalDateTime getLatest(@Context Handle handle, Integer stockId) {
        return handle.attach(SKUHistoryDAO.class).getLatestHistoryByStockId(stockId).getCreateAt();
    }
}
