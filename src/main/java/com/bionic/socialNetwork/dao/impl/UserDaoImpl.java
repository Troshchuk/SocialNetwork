package com.bionic.socialNetwork.dao.impl;

import com.bionic.socialNetwork.dao.PasswordDao;
import com.bionic.socialNetwork.dao.UserDao;
import com.bionic.socialNetwork.models.Password;
import com.bionic.socialNetwork.models.User;
import com.bionic.socialNetwork.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

/**
 * @author Dmytro Troshchuk
 * @version 1.00  14.07.14.
 */
public class UserDaoImpl implements UserDao {
    @Override
    public void insert(User user, Password password) throws Exception {
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(user);
            password.setUserId(user.getId());
            session.save(password);
            session.getTransaction().commit();
        }finally {
            session.close();
        }
    }

    @Override
    public User selectById(long id) throws Exception {
        Session session= null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            User user = (User) session.get(User.class, id);
            return user;
        }finally {
            session.close();
        }

    }

    @Override
    public User selectByLogin(String login) throws Exception {
        Session session= null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery(
                    "SELECT id FROM User where login = '" + login + "'");
            List<Long> list = query.list();

                if(list.size()!=0) {
                    User user = selectById(list.get(0));
                    return user;
                }else {
                    return null;
                }
        }finally {
            session.close();
        }



    }

    @Override
    public List<User> selectNext(long beginId) throws Exception {
        int limit = 10;
        Session session= null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();

            Query query = session.createQuery(
                    "FROM User WHERE id >= " + beginId);
            query.setMaxResults(10);
            List<User> users = query.list();
            return users;
        }finally {
            session.close();
        }

    }

    @Override
    public void delete(User user) throws Exception {
        Session session= null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            PasswordDao passwordDao = new PasswordDaoImpl();
            Password password = passwordDao.selectById(user.getId());
            session.beginTransaction();
            session.delete(password);
            session.delete(user);
            session.getTransaction().commit();

        }finally {
            session.close();
        }

    }
}