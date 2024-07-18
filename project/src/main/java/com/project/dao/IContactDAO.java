package com.project.dao;

import com.project.models.Contact;
import com.project.models.Product;
import com.project.models.Rating;

import java.util.List;

public interface IContactDAO {
    List<Contact> getAll();
    List<Contact> getUncheckNoti();
    int insert(Contact r);
}
