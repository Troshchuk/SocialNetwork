package com.bionic.socialNetwork.dao.impl;

import com.bionic.socialNetwork.dao.GroupPostDao;
import com.bionic.socialNetwork.models.GroupPost;
import com.bionic.socialNetwork.util.HibernateUtil;
import org.hibernate.Session;

/**
 * @author yoalex5
 * @version 1.0 16.07.14
 */
public class GroupPostDaoImpl implements GroupPostDao {

    @Override
    public GroupPost selectById(long id) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        GroupPost groupPost = (GroupPost) session.get(GroupPost.class, id);
        session.close();
        return groupPost;
    }

    @Override
    public void insert(GroupPost groupPost) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        session.save(groupPost);

        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(GroupPost groupPost) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        session.update(groupPost);

        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(GroupPost groupPost) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        session.delete(groupPost);

        session.getTransaction().commit();
        session.close();
    }
}
