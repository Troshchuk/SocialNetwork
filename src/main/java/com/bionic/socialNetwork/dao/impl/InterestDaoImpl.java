package com.bionic.socialNetwork.dao.impl;

import com.bionic.socialNetwork.dao.InterestDao;
import com.bionic.socialNetwork.models.Interest;
import com.bionic.socialNetwork.models.User;
import com.bionic.socialNetwork.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

/**
 *
 * @author Matvey
 * @version 1.00 on 17.07.2014.
 */
public class InterestDaoImpl implements InterestDao {
    @Override
    public void insert(Interest interest) throws Exception {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(interest);
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (session!= null && session.isOpen())
            session.close();
        }
    }

    @Override
    public Interest selectById(long id) throws Exception{
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Interest interest =(Interest) session.get(Interest.class, id);
            session.close();
            return interest;
        }
        finally {
            if (session!= null && session.isOpen()) {
                session.close();
            }
        }

    }

    @Override
    public Interest selectByInterest(String interest) throws Exception{
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();

            Query query = session.createQuery(
                    "FROM Interest where interest = '" + interest + "'");
            List<Interest> interests = query.list();
            session.close();
            if(interests.size() == 0){
                return null;
            }else{
                return interests.get(0);
            }
        }
        finally {
            if (session!= null && session.isOpen()) {
                session.close();
            }
        }

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
            session.close();
        }finally {
            if (session!= null && session.isOpen())
            session.close();
        }
        return list;
    }

    @Override
    public void delete (Interest interest) throws Exception{
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.delete(interest);
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (session!= null && session.isOpen())
            session.close();
        }
    }

    @Override
    public void update(Interest interest) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(interest);
        session.getTransaction().commit();
        session.close();
    }


}
