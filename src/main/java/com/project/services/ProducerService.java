package com.project.services;

import com.project.dao.IProducerDAO;
import com.project.dao.IProductDAO;
import com.project.dao.implement.FactoryDAO;
import com.project.models.Producer;
import org.jdbi.v3.core.Handle;

import java.util.List;

public class ProducerService extends AbstractService {
    private final IProducerDAO producerDAO;

    public ProducerService() {
        this.producerDAO = FactoryDAO.getDAO(this.handle, FactoryDAO.DAO_PRODUCER);
    }

    public ProducerService(Handle handle) {
        super(handle);
        this.producerDAO = FactoryDAO.getDAO(this.handle, FactoryDAO.DAO_PRODUCER);
    }

    public List<Producer> getAll() {
        return producerDAO.selectAll();
    }
    public Producer getById(int id) {
        return producerDAO.findOneById(id);
    }

    public static void main(String[] args) {
        var service = new ProducerService();
        System.out.println(service.getById(2));
    }
}
