package com.project.dto.mapper.cart;

import com.project.dao_rework.CategoryDAO;
import com.project.dao_rework.DiscountDAO;
import com.project.dao_rework.ProductDAO;
import com.project.dto.response.cart.CartItemDTO;
import com.project.models_rework.CartItem;
import com.project.models_rework.Product;
import lombok.NoArgsConstructor;
import org.jdbi.v3.core.Handle;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(builder = @Builder(disableBuilder = true))
@NoArgsConstructor
public abstract class CartItemDTOMapper {
    public static final CartItemDTOMapper INSTANCE = Mappers.getMapper(CartItemDTOMapper.class);
    @Mapping(target = "price", ignore = true)
    @Mapping(target = "formatDiscountPrice", ignore = true)
    @Mapping(target = "discountPrice", ignore = true)
    @Mapping(target = "formatPrice", ignore = true)
    @Mapping(target = "product", source = "productId")
    public abstract CartItemDTO mapToDTO(@Context Handle handle, CartItem item);

    protected Product getProduct(@Context Handle handle, Integer productId) {
        return handle.attach(ProductDAO.class).getById_all(productId).get(0);
    }

    @Mapping(target = "categoryName", source = "categoryId", qualifiedByName = "getCategoryName")
    protected abstract CartItemDTO.Product getProduct(@Context Handle handle, Product product);

    @Named("getDiscountPercent")
    protected Float getDiscountPercent(@Context Handle handle, Integer discountId) {
        return handle.attach(DiscountDAO.class).getDiscountPercent(discountId).get(0);
    }

    @Named("getCategoryName")
    protected String getCategoryName(@Context Handle handle, Integer categoryId) {
        return handle.attach(CategoryDAO.class).getById_All(categoryId).get(0).getName();
    }

    @Named("formatPrice")
    protected String formatPrice(Float price) {
        String r = (int) price.floatValue() + "";
        r = r.replaceAll("(?<=\\d)(?=(\\d{3})+(?!\\d))", ".");
        return r;
    }
}
