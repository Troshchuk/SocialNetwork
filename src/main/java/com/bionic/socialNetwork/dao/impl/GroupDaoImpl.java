package com.bionic.socialNetwork.dao.impl;

import com.bionic.socialNetwork.dao.GroupDao;
import com.bionic.socialNetwork.models.Group;
import com.bionic.socialNetwork.util.HibernateUtil;
import org.hibernate.Session;

/**
 * @author yoalex5
 * @version 1.0 17.07.14
 */
public class GroupDaoImpl implements GroupDao {
    @Override
    public Group selectById(long id) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Group groupE = (Group) session.get(Group.class, id);
        session.close();
        return groupE;
    }

    @Override
    public void insert(Group group) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        session.save(group);

        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(Group group) throws Exception {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();

            session.beginTransaction();

            session.update(group);

            session.getTransaction().commit();
        }
        finally {
            if (session!= null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public void delete(Group group) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        session.delete(group);

        session.getTransaction().commit();
        session.close();
    }
}
