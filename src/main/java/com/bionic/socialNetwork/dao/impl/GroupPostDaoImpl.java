package com.bionic.socialNetwork.dao.impl;

import com.bionic.socialNetwork.dao.GroupPostDao;
import com.bionic.socialNetwork.models.Group;
import com.bionic.socialNetwork.models.GroupPost;
import com.bionic.socialNetwork.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.util.List;
/**
 * @author yoalex5, Dmytro Troshchuk
 * @version 1.1 16.07.14
 */
public class GroupPostDaoImpl implements GroupPostDao {

    @Override
    public GroupPost selectById(long id) throws Exception {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            GroupPost groupPost = (GroupPost) session.get(GroupPost.class, id);
            return groupPost;
        }
        finally {
            if (session!= null && session.isOpen()) {
                session.close();
            }
        }
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
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            session.update(groupPost);

            session.getTransaction().commit();
        }
        finally {
            if (session!= null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public void delete(GroupPost groupPost) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        session.delete(groupPost);

        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<GroupPost> selectLastWith(Group group, int lot) throws Exception {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(GroupPost.class);
            criteria.setMaxResults(10);
            criteria.add(Restrictions.eq("group.groupId", group.getGroupId()));
            criteria.addOrder(Order.desc("time"));
            criteria.setFirstResult(lot * 10);
            List list = criteria.list();
            return list;
        }
        finally {
            if (session!= null && session.isOpen()) {
                session.close();
            }
        }
    }
}
