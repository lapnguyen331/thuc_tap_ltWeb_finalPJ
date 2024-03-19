package com.project.services;

import com.project.dao.IRatingDAO;
import com.project.dao.implement.FactoryDAO;
import com.project.models.Product;
import com.project.models.Rating;
import org.jdbi.v3.core.Handle;

import java.util.List;

public class RatingService extends AbstractService {
    private final IRatingDAO ratingDAO;

    public RatingService() {
        this.ratingDAO = FactoryDAO.getDAO(this.handle, FactoryDAO.DAO_RATING);
    }

    public RatingService(Handle handle) {
        super(handle);
        this.ratingDAO = FactoryDAO.getDAO(this.handle, FactoryDAO.DAO_RATING);
    }

    public List<Rating> getRatingOf(Product product) {
        return ratingDAO.getAllOf(product);
    }

    public int insertRating(Rating rating) {
        return ratingDAO.insert(rating);
    }
}
