package com.project.dao;

import com.project.models.Category;
import com.project.models.Discount;

import java.util.List;

public interface IDiscountDAO {
    List<Discount> selectAll();
    Discount findOneById(int id);
}
