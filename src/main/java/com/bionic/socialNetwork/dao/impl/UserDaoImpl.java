package com.bionic.socialNetwork.dao.impl;

import com.bionic.socialNetwork.dao.UserDao;
import com.bionic.socialNetwork.models.User;
import com.bionic.socialNetwork.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * @author Dmytro Troshchuk
 * @version 1.00  14.07.14.
 */
public class UserDaoImpl implements UserDao {
    @Override
    public void addUser(User user) throws Exception {
        Session session;
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public User getUserById(long id) throws Exception {
        Session session;
        session = HibernateUtil.getSessionFactory().openSession();
        User user = (User) session.load(User.class, id);
        session.close();
        return user;
    }

    @Override
    public User getUserByLogin(String login) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Query query = session.createQuery(
                "SELECT id FROM User where login = '" + login + "'");
        int id = query.getFirstResult();
        User user = (User) session.load(User.class, id);
        session.close();
        return user;
    }
}