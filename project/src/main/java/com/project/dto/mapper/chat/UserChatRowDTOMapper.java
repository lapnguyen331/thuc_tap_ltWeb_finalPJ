package com.project.dto.mapper.chat;
import com.project.dao_rework.ImageDAO;
import com.project.dao_rework.UserDAO;
import com.project.dto.response.chat.UserChatRowDTO;
import com.project.exceptions.custom_exception.MyServletException;
import com.project.models_rework.Chat;
import com.project.models_rework.log.Logger;
import com.project.service_rework.UploadService;
import org.jdbi.v3.core.Handle;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(builder = @Builder(disableBuilder = true))
public abstract class UserChatRowDTOMapper {
    public static final UserChatRowDTOMapper INSTANCE = Mappers.getMapper(UserChatRowDTOMapper.class);
    @Mapping(target = "sender", source = "senderId", qualifiedByName = "getSender")
    @Mapping(target = "receiver", source = "receiverId", qualifiedByName = "getReceiver")
    public abstract UserChatRowDTO mapToDTO(@Context Handle handle, Chat chat);
    public abstract List<UserChatRowDTO> mapToDTO(@Context Handle handle, List<Chat> chat);

    @Named("getSender")
    protected UserChatRowDTO.Sender getUsername(@Context Handle handle, Integer senderId) {
        var user = handle.attach(UserDAO.class).getUserById(senderId).get(0);
        Integer avatarId = user.getAvatar();
        var img = handle.attach(ImageDAO.class).getImageById(avatarId);
        var upload = new UploadService();
        String link = null;
        try {
            link = upload.getURL(img.get(0).getPath());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return new UserChatRowDTO.Sender(user.getId(), user.getUsername(), link);
    }

    @Named("getReceiver")
    protected UserChatRowDTO.Receiver getThumbnail(@Context Handle handle, Integer receiverId) {
        var user = handle.attach(UserDAO.class).getUserById(receiverId).get(0);
        Integer avatarId = user.getAvatar();
        var img = handle.attach(ImageDAO.class).getImageById(avatarId);
        var upload = new UploadService();
        String link = null;
        try {
            link = upload.getURL(img.get(0).getPath());
        } catch (Exception e) {
            Logger.warning("Upload image to cloudinary failed");
            throw new RuntimeException(e.getMessage());
        }
        return new UserChatRowDTO.Receiver(user.getId(), user.getUsername(), link);
    }
}
