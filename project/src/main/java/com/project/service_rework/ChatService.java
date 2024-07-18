package com.project.service_rework;

import com.project.dao_rework.ChatDAO;
import com.project.dao_rework.ProductDAO;
import com.project.dto.mapper.chat.UserChatRowDTOMapper;
import com.project.dto.mapper.product.ProductCardDTOMapper;
import com.project.dto.mapper.product.ProductDetailsDTOMapper;
import com.project.dto.mapper.product.ProductIDNameThumbnailDTOMapper;
import com.project.dto.response.chat.UserChatRowDTO;
import com.project.dto.response.product.ProductCardDTO;
import com.project.dto.response.product.ProductDetailsDTO;
import com.project.dto.response.product.ProductIDNameThumbnailDTO;

import java.util.List;

public class ChatService extends AbstractService {
    public List<UserChatRowDTO> getChatRowOfUserId(Integer userId) {
        var list = handle.attach(ChatDAO.class).getLatestChatGroupByUserIdAndReceiverId(userId);
        return UserChatRowDTOMapper.INSTANCE.mapToDTO(handle, list);
    }
    public List<UserChatRowDTO> getChatsBetween(Integer userId1, Integer userId2) {
        var list = handle.attach(ChatDAO.class).getAllChatBetweenUserIds(userId1, userId2);
        return UserChatRowDTOMapper.INSTANCE.mapToDTO(handle, list);
    }
}
