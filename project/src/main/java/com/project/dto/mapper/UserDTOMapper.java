package com.project.dto.mapper;

import com.project.dao_rework.ImageDAO;
import com.project.dto.response.user.UserDetailsDTO;
import com.project.exceptions.custom_exception.MyServletException;
import com.project.models_rework.User;
import com.project.service_rework.UploadService;
import org.jdbi.v3.core.Handle;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public abstract class UserDTOMapper {
    public static final UserDTOMapper INSTANCE = Mappers.getMapper(UserDTOMapper.class);
    @Mapping(target = "avatar", source = "avatar", qualifiedByName = "getAvatar")
    public abstract UserDetailsDTO mapToDTO(@Context Handle handle, User user) throws MyServletException;
    public abstract List<UserDetailsDTO> mapToDTO(@Context Handle handle, List<User> user) throws MyServletException;

    @Named("getAvatar")
    protected String getAvatar(@Context Handle handle, Integer avatarId) throws MyServletException {
        var avt = handle.attach(ImageDAO.class).getImageById(avatarId);
        var service = new UploadService();
        String link = null;
        try {
            link = service.getURL(avt.get(0).getPath());
        } catch (Exception e) {
            throw new MyServletException("Lỗi xảy ra khi lấy hình ảnh", 500);
        }
        return link;
    }
}
