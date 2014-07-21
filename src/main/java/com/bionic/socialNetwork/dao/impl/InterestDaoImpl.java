package com.bionic.socialNetwork.dao.impl;

import com.bionic.socialNetwork.dao.InterestDao;
import com.bionic.socialNetwork.models.Administrator;
import com.bionic.socialNetwork.models.Interest;
import com.bionic.socialNetwork.models.User;
import com.bionic.socialNetwork.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by Matvey on 17.07.2014.
 */
public class InterestDaoImpl implements InterestDao {
    @Override
    public void insert(Interest interest) throws Exception {
        Session session;
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(interest);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Interest selectById(long id) throws Exception{
        Session session;
        session = HibernateUtil.getSessionFactory().openSession();
        Interest interest = (Interest) session.get(Interest.class, id);
        session.close();
        return interest;

    }

    @Override
    public List<User> selectByInterest(Interest interest) throws Exception{
        Session session = HibernateUtil.getSessionFactory().openSession();

        Query query = session.createQuery(
                "SELECT userSet FROM Interest where interest = '" + interest + "'");
        List<User> user = query.list();
        session.close();
        return user;
    }

    @Override
    public void delete (Interest interest) throws Exception{
        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();
        session.delete(interest);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Interest> selectByUser(User user) throws Exception {
        Session session = null;
        List<Interest> list;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("FROM Interest");
            list = query.list();
        }finally {

        }session.close();

        return list;
    }

}
