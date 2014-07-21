package com.bionic.socialNetwork.dao.impl;

import com.bionic.socialNetwork.dao.PostDao;
import com.bionic.socialNetwork.models.Post;
import com.bionic.socialNetwork.util.HibernateUtil;
import org.hibernate.*;

import java.util.List;

/**
 * Posts Dao implementation
 *
 * @author Denis Biyovskiy
 * @version 1.00  16.07.2014.
 */
public class PostDaoImpl implements PostDao {

    @Override

    public Post selectById(long id) throws Exception {
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            Post post = (Post) session.get(Post.class, id);
            return post;
        }finally {
            session.close();
        }

    }

    @Override
    public List<Post> selectNext(long beginId) throws Exception {
        int limit = 10;
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery(
                    "FROM Post WHERE postId >= " + beginId + " AND postId < " +
                            (beginId + limit));
            List<Post> posts = query.list();
            return posts;
        }finally {
            session.close();
        }

    }

    @Override
    public void insert(Post post) throws Exception {
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(post);
            session.getTransaction().commit();
        }finally {
            session.close();
        }
    }

    @Override
    public void delete(Post post) throws Exception {
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(post);
            session.getTransaction().commit();
        }finally {
            session.close();
        }


    }
}
