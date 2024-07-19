package com.project.dto.mapper.product;

import com.project.dao_rework.ImageDAO;
import com.project.dto.response.category.CategoryIDNameThumbnailDTO;
import com.project.dto.response.product.ProductIDNameThumbnailDTO;
import com.project.exceptions.custom_exception.MyServletException;
import com.project.models_rework.Category;
import com.project.models_rework.Product;
import com.project.models_rework.log.Logger;
import com.project.service_rework.UploadService;
import lombok.NoArgsConstructor;
import org.jdbi.v3.core.Handle;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(builder = @Builder(disableBuilder = true))
@NoArgsConstructor
public abstract class ProductIDNameThumbnailDTOMapper {
    public static final ProductIDNameThumbnailDTOMapper INSTANCE = Mappers.getMapper(ProductIDNameThumbnailDTOMapper.class);
    @Mapping(target = "thumbnail", source = "thumbnail", qualifiedByName = "getThumbnail")
    public abstract ProductIDNameThumbnailDTO mapToDTO(@Context Handle handle, Product product);
    public abstract List<ProductIDNameThumbnailDTO> mapToDTO(@Context Handle handle, List<Product> productList);
    @Named("getThumbnail")
    protected String getThumbnail(@Context Handle handle, Integer thumbnailId) throws MyServletException {
        var path = handle.attach(ImageDAO.class).getImageById(thumbnailId).get(0).getPath();
        var uploadService = new UploadService();
        try {
            return uploadService.getURL(path);
        } catch (Exception e) {
            Logger.warning("can not load image from cloudinary");
            throw new MyServletException("Lỗi server khi cố lấy thông tin ảnh", 500);
        }
    }
}
