package com.bionic.socialNetwork.dao.impl;

import com.bionic.socialNetwork.dao.SessionUserDao;
import com.bionic.socialNetwork.models.SessionUser;
import com.bionic.socialNetwork.util.HibernateUtil;
import org.hibernate.Session;

/**
 * @author Denis
 * @version 1.00  18.07.2014.
 */
public class SessionUserDaoImpl implements SessionUserDao {
    @Override
    public void insert(SessionUser sessionUser) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(sessionUser);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public SessionUser selectById(long id) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        SessionUser sessionUser = (SessionUser) session.get(SessionUser.class, id);
        session.close();
        return sessionUser;
    }

    @Override
    public void delete(SessionUser sessionUser) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(sessionUser);
        session.getTransaction().commit();
        session.close();
    }
}
