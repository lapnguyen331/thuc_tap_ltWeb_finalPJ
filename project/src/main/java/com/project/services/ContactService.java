package com.project.services;

import com.project.dao.IContactDAO;
import com.project.dao.IRatingDAO;
import com.project.dao.implement.FactoryDAO;
import com.project.models.Contact;
import com.project.models.Product;
import com.project.models.Rating;
import org.jdbi.v3.core.Handle;

import java.util.List;

public class ContactService extends AbstractService {
    private final IContactDAO contactDAO;

    public ContactService() {
        this.contactDAO = FactoryDAO.getDAO(this.handle, FactoryDAO.DAO_CONTACT);
    }


    public ContactService(Handle handle) {
        super(handle);
        this.contactDAO = FactoryDAO.getDAO(this.handle, FactoryDAO.DAO_CONTACT);
    }

    public List<Contact> getALL() {
        return contactDAO.getAll();
    }
    public int getUncheckNoti(){
        return contactDAO.getUncheckNoti().size();
    }
    public int insertContact(Contact contact) {
        return contactDAO.insert(contact);
    }

    public static void main(String[] args) {
        ContactService c =new ContactService();
       System.out.println(c.getUncheckNoti());
    }
}
