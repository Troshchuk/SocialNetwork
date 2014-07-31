package com.bionic.socialNetwork.dao.impl;

import com.bionic.socialNetwork.dao.PostDao;
import com.bionic.socialNetwork.models.BackOfficeAdmin;
import com.bionic.socialNetwork.models.Post;
import com.bionic.socialNetwork.models.User;
import com.bionic.socialNetwork.util.HibernateUtil;
import org.hibernate.*;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * Posts Dao implementation
 *
 * @author Denis Biyovskiy, Dmytro Troshchuk
 * @version 1.10  16.07.2014.
 */
public class PostDaoImpl implements PostDao {

    @Override
    public Post selectById(long id) throws Exception {
        Session session = null;
        try {
            session  = HibernateUtil.getSessionFactory().openSession();
            Post post = (Post) session.get(Post.class, id);
            session.close();
            return post;
        } finally {
            if (session!= null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public void insert(Post post) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(post);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(Post post) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(post);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Post> selectLastWith(User user, int lot) throws Exception {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(Post.class);
            criteria.setMaxResults(10);
            criteria.add(Restrictions.eq("user.id", user.getId()));
            criteria.addOrder(Order.desc("time"));
            criteria.setFirstResult(lot * 10);
            List<Post> list = criteria.list();
            session.close();
            return list;
        } finally {
            if (session!= null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public List<Post> selectLastBeckOffWith (List<BackOfficeAdmin> backOfficeAdmins,
                                             int lot) throws Exception {
        Session session = null;
        try {
            int i = 0;
            Long [] arr = new Long[backOfficeAdmins.size()];
            for(BackOfficeAdmin backOff:backOfficeAdmins) {
                arr[i] = backOff.getUser().getId();
                i++;
            }

            session = HibernateUtil.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(Post.class);
            criteria.setMaxResults(10);
            criteria.add(Restrictions.in("user.id" , arr));
            criteria.addOrder(Order.desc("time"));
            criteria.setFirstResult(lot * 10);
            List<Post> list = criteria.list();
            session.close();
            return list;
        } finally {
            if (session!= null && session.isOpen()) {
                session.close();
            }
        }
    }
}
