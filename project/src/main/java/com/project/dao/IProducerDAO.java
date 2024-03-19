package com.project.dao;
import com.project.models.Producer;

import java.util.List;

public interface IProducerDAO {
    List<Producer> selectAll();
    Producer findOneById(int id);
}
