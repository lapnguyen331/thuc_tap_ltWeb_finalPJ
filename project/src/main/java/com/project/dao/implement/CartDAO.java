package com.project.dao.implement;

import com.project.dao.AbstractDAO;
import com.project.dao.ICartDAO;
import com.project.dao.IUserDAO;
import com.project.mappers.ImageRowMapper;
import com.project.mappers.UserRowMapper;
import com.project.models.Cart;
import com.project.models.Image;
import com.project.models.User;
import org.jdbi.v3.core.Handle;

import javax.websocket.Session;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class CartDAO extends AbstractDAO<Cart> implements ICartDAO {
    public CartDAO(Handle handle) {
        super(handle);
    }


    @Override
    public List<Cart> getAll() {
        return null;
    }

    @Override
    public static Cart updateCart(int id) {
        return null;
    }
}
