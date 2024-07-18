package com.project.dto.mapper.product;

import com.project.dao_rework.DiscountDAO;
import com.project.dao_rework.ImageDAO;
import com.project.dto.response.product.ProductCardDTO;
import com.project.models_rework.Product;
import lombok.NoArgsConstructor;
import org.jdbi.v3.core.Handle;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(builder = @Builder(disableBuilder = true))
@NoArgsConstructor
public abstract class ProductCardDTOMapper {
    public static final ProductCardDTOMapper INSTANCE = Mappers.getMapper(ProductCardDTOMapper.class);

    @Mapping(target = "thumbnail", source = "thumbnail", qualifiedByName = "getImageLink")
    @Mapping(target = "discountPercent", source = "discountId", qualifiedByName = "getDiscountPercent")
    @Mapping(target = "priceFormat", source = "price", qualifiedByName = "formatPrice")
    @Mapping(target = "discountPriceFormat", ignore = true)
    @Mapping(target = "discountPrice", ignore = true)
    public abstract ProductCardDTO mapToDTO(@Context Handle handle, Product product);
    public abstract List<ProductCardDTO> mapToDTO(@Context Handle handle, List<Product> products);

    @Named("getImageLink")
    protected String getImageLink(@Context Handle handle, Integer thumbnail) {
        return handle.attach(ImageDAO.class)
                .getImageById(thumbnail).get(0)
                .getPath();
    }

    @Named("getDiscountPercent")
    protected Float getDiscountPercent(@Context Handle handle, Integer discountId) {
        return handle.attach(DiscountDAO.class).getDiscountPercent(discountId).get(0);
    }

    @Named("formatPrice")
    protected String formatPrice(Float price) {
        String r = (int) price.floatValue() + "";
        r = r.replaceAll("(?<=\\d)(?=(\\d{3})+(?!\\d))", ".");
        return r;
    }

    @AfterMapping
    protected void afterMap(@MappingTarget ProductCardDTO obj) {
        float discountPrice = obj.price * (100 - obj.discountPercent) / 100;
        obj.discountPrice = discountPrice;
        obj.discountPriceFormat = formatPrice(discountPrice);
    }
}
