package com.bionic.socialNetwork.dao.impl;

import com.bionic.socialNetwork.dao.BackOfficeAdminDao;
import com.bionic.socialNetwork.models.BackOfficeAdmin;
import com.bionic.socialNetwork.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

/**
 * @author Dmytro Troshchuk
 * @version 1.00  16.07.14.
 */
public class BackOfficeAdminDaoImpl implements BackOfficeAdminDao {

    @Override
    public void insert(BackOfficeAdmin backOfficeAdmin) throws Exception {
        Session session;
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(backOfficeAdmin);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(BackOfficeAdmin backOfficeAdmin) throws Exception {
        Session session;
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(backOfficeAdmin);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<BackOfficeAdmin> selectAll() throws Exception {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("FROM BackOfficeAdmin");
            List<BackOfficeAdmin> list = query.list();
            session.close();
            return list;
        }
        finally {
            if(session!= null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public BackOfficeAdmin selectById(long id) throws Exception {
        Session session =null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            BackOfficeAdmin backOfficeAdmin =
                    (BackOfficeAdmin) session.get(BackOfficeAdmin.class, id);
            session.close();
            return backOfficeAdmin;
        }
        finally {
            if (session!= null && session.isOpen()){
                session.close();
            }
        }
    }
}
