package com.project.dto.mapper;

import com.project.dao_rework.ImageDAO;
import com.project.dto.response.user.UserDetailsDTO;
import com.project.models_rework.User;
import org.jdbi.v3.core.Handle;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class UserDTOMapper {
    public static final UserDTOMapper INSTANCE = Mappers.getMapper(UserDTOMapper.class);
    @Mapping(target = "avatar", source = "avatar", qualifiedByName = "getAvatar")
    public abstract UserDetailsDTO mapToDTO(@Context Handle handle, User user);
    @Named("getAvatar")
    protected String getAvatar(@Context Handle handle, Integer avatarId) {
        var avt = handle.attach(ImageDAO.class).getImageById(avatarId);
        return avt.get(0).getPath();
    }
}
