package com.project.services;

import com.project.dao.ICategoryDAO;
import com.project.dao.implement.CategoryDAO;
import com.project.dao.implement.FactoryDAO;
import com.project.db.JDBIConnector;
import com.project.models.Category;
import com.project.models.Product;
import org.jdbi.v3.core.Handle;

import java.util.List;

public class CategoryService extends AbstractService {
    private final ICategoryDAO categoryDAO;

    public CategoryService() {
        this.categoryDAO = FactoryDAO.getDAO(handle, FactoryDAO.DAO_CATEGORY);
    }

    public CategoryService(Handle handle, ICategoryDAO dao) {
        super(handle);
        this.categoryDAO = FactoryDAO.getDAO(handle, FactoryDAO.DAO_CATEGORY);
    }

    public synchronized List<Category> getAll() {
        return categoryDAO.getAll_fullDetails();
    }

    public List<Category> getAll_ID_name() {
        return categoryDAO.getAll_shortDetails();
    }
    public Category findCateById(int id){
        List<Category>em =categoryDAO.getAll_shortDetails();
        for(Category i :em){
            if(i.getId() == id) {
                return i;
            }
        }
        return null;
    }


    public static void main(String[] args) {
        var service = new CategoryService();
//        service.getAll_ID_name().forEach(System.out::println);
//        service.getAll().forEach(System.out::println);
    }
}
