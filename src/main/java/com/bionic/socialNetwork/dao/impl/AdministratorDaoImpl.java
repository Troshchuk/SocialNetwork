package com.bionic.socialNetwork.dao.impl;

import com.bionic.socialNetwork.dao.AdministratorDao;
import com.bionic.socialNetwork.models.Administrator;
import com.bionic.socialNetwork.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

/**
 * @author Dmytro Troshchuk
 * @version 1.00  16.07.14.
 */
public class AdministratorDaoImpl implements AdministratorDao {
    @Override
    public void insert(Administrator administrator) {
        Session session;
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(administrator);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(Administrator administrator) {
        Session session;
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(administrator);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Administrator> selectAll() throws Exception {
            Session session  = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("FROM Administrator");
            List<Administrator> list = query.list();
            return list;
        }finally {
            if(session!= null && session.isOpen()){
                session.close();
            }
        }
    }

    @Override
    public Administrator selectById(long id) throws Exception {
        Session session;
        session = HibernateUtil.getSessionFactory().openSession();
        Administrator administrator =
                (Administrator) session.get(Administrator.class, id);
        session.close();
        return administrator;
    }
}
