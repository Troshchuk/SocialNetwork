package com.bionic.socialNetwork.dao.impl;

import com.bionic.socialNetwork.dao.PotentialUserDao;
import com.bionic.socialNetwork.models.PotentialUser;
import com.bionic.socialNetwork.util.HibernateUtil;
import org.hibernate.Session;

/**
 * @author yoalex5
 * @version 1.0 18.07.14
 */
public class PotentialUserDaoImpl implements PotentialUserDao{
    @Override
    public void insert(PotentialUser potentionalUser) throws Exception {
        Session session;
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        session.save(potentionalUser);

        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(PotentialUser potentionalUser) throws Exception {
        Session session;
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        session.delete(potentionalUser);

        session.getTransaction().commit();
        session.close();
    }
}
