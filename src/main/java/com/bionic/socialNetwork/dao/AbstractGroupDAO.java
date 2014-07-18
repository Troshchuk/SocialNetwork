package com.bionic.socialNetwork.dao;

import com.bionic.socialNetwork.util.HibernateUtil;
import org.hibernate.Session;

/**
 * @author yoalex5
 * @version 1.0 17.07.14
 */
public class AbstractGroupDAO<T> implements GroupDAO<T> {
    private final Class<T> genericType;

    public AbstractGroupDAO(Class<T> type) {
        genericType = type;
    }

    public Class<T> getMyType() {
        return genericType;
    }

    @Override
    public T selectById(long id) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        T groupE = (T)session.get(genericType, id);
        session.close();
        return groupE;
    }

    @Override
    public void insert(T groupObj) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        session.save(groupObj);

        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(T groupObj) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        session.update(groupObj);

        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(T groupObj) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        session.delete(groupObj);

        session.getTransaction().commit();
        session.close();
    }
}
