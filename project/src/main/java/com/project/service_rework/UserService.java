package com.project.service_rework;

import com.project.dao_rework.UserDAO;
import com.project.dto.mapper.UserDTOMapper;
import com.project.dto.response.user.UserDetailsDTO;
import com.project.exceptions.custom_exception.MyServletException;
import com.project.models_rework.User;
import org.jdbi.v3.core.Handle;

import java.util.Arrays;
import java.util.List;

public class UserService extends AbstractService {
    public UserService() {
    }

    public UserService(Handle handle) {
        super(handle);
    }

    public List<UserDetailsDTO> getUserById(Integer id) throws MyServletException {
        UserDAO userDAO = getHandle().attach(UserDAO.class);
        User user = userDAO.getUserById(id).get(0);
        var dto = UserDTOMapper.INSTANCE.mapToDTO(getHandle(), user);
        return Arrays.asList(dto);
    }

    public List<UserDetailsDTO> getUsersByUsername(String username) throws MyServletException {
        UserDAO userDAO = this.handle.attach(UserDAO.class);
        List<User> users = userDAO.getUserByUsername("'%"+username+"%'");
        var dtos = UserDTOMapper.INSTANCE.mapToDTO(this.handle, users);
        return dtos;
    }
}
