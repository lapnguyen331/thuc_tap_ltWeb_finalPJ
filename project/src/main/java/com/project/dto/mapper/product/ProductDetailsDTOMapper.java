package com.project.dto.mapper.product;

import com.project.dao_rework.BlogDAO;
import com.project.dao_rework.DiscountDAO;
import com.project.dao_rework.ImageDAO;
import com.project.dto.response.product.ProductCardDTO;
import com.project.dto.response.product.ProductDetailsDTO;
import com.project.models_rework.Image;
import com.project.models_rework.Product;
import jdk.jfr.Name;
import lombok.NoArgsConstructor;
import org.jdbi.v3.core.Handle;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(builder = @Builder(disableBuilder = true))
@NoArgsConstructor
public abstract class ProductDetailsDTOMapper {
    public static final ProductDetailsDTOMapper INSTANCE = Mappers.getMapper(ProductDetailsDTOMapper.class);

    @Mapping(target = "discount", source = "discountId", qualifiedByName = "getDiscount")
    @Mapping(target = "gallery", source = "id", qualifiedByName = "getGallery")
    @Mapping(target = "priceFormat", source = "price", qualifiedByName = "formatPrice")
    @Mapping(target = "discountPriceFormat", ignore = true)
    @Mapping(target = "blogPath", source = "blogId", qualifiedByName = "getBlogURL")
    public abstract ProductDetailsDTO mapToDTO(@Context Handle handle, Product product);

    @Named("getDiscount")
    protected ProductDetailsDTO.Discount getDiscount(@Context Handle handle, Integer discountId) {
        var list = handle.attach(DiscountDAO.class).getDiscountById_id_name_percent(discountId);
        if (list.isEmpty()) {
            return ProductDetailsDTO.Discount.builder()
                    .id(null)
                    .name("Không có")
                    .percent(0f).build();
        }
        var discount = list.get(0);
        return ProductDetailsDTO.Discount.builder()
                .id(discountId)
                .name(discount.getName())
                .percent(discount.getDiscountPercent()).build();
    }

    @Named("getGallery")
    protected List<String> getGallery(@Context Handle handle, Integer productId) {
        return handle.attach(ImageDAO.class).getImagesByProductId(productId).stream()
                .map(Image::getPath).toList();
    }

    @Named("getBlogURL")
    protected String getBlogURL(@Context Handle handle, Integer blogId) {
        return handle.attach(BlogDAO.class).getById_all(blogId).get(0).getPath();
    }

    @Named("formatPrice")
    protected String formatPrice(Float price) {
        String r = (int) price.floatValue() + "";
        r = r.replaceAll("(?<=\\d)(?=(\\d{3})+(?!\\d))", ".");
        return r;
    }

    @AfterMapping
    protected void afterMap(@MappingTarget ProductDetailsDTO obj) {
        float discountPrice = obj.price * (100 - obj.discount.percent) / 100;
        obj.discountPriceFormat = formatPrice(discountPrice);
    }
}
