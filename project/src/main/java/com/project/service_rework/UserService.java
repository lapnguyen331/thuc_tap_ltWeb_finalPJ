package com.project.service_rework;

import com.project.dao_rework.UserDAO;
import com.project.dto.mapper.UserDTOMapper;
import com.project.dto.response.user.UserDetailsDTO;
import com.project.models_rework.User;

import java.util.Arrays;
import java.util.List;

public class UserService extends AbstractService {
    List<UserDetailsDTO> getUserById(Integer id) {
        UserDAO userDAO = getHandle().attach(UserDAO.class);
        User user = userDAO.getUserById(id).get(0);
        var dto = UserDTOMapper.INSTANCE.mapToDTO(getHandle(), user);
        return Arrays.asList(dto);
    }

    public static void main(String[] args) {
        UserService userService = new UserService();
        System.out.println(userService.getUserById(1));
    }
}
