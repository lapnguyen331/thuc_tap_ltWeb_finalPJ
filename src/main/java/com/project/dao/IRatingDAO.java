package com.project.dao;

import com.project.models.Product;
import com.project.models.Rating;
import java.util.List;

public interface IRatingDAO {
    List<Rating> getAll();
    List<Rating> getAllOf(Product prodct);
    int insert(Rating r);
}
