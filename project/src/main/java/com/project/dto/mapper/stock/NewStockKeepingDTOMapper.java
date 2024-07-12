package com.project.dto.mapper.stock;

import com.project.dao_rework.DiscountDAO;
import com.project.dao_rework.ImageDAO;
import com.project.dto.request.stock.NewStockKeepingDTO;
import com.project.dto.response.product.ProductCardDTO;
import com.project.models_rework.Product;
import com.project.models_rework.StockKeeping;
import org.jdbi.v3.core.Handle;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(builder = @Builder(disableBuilder = true))
public abstract class NewStockKeepingDTOMapper {
    public static final NewStockKeepingDTOMapper INSTANCE = Mappers.getMapper(NewStockKeepingDTOMapper.class);

    public abstract StockKeeping mapToModel(NewStockKeepingDTO dto);
    public abstract List<StockKeeping> mapToModel(List<NewStockKeepingDTO> dto);
}
