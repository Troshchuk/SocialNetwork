package com.bionic.socialNetwork.dao.impl;

import com.bionic.socialNetwork.dao.PostDao;
import com.bionic.socialNetwork.models.Post;
import com.bionic.socialNetwork.util.HibernateUtil;
import org.hibernate.Session;

/**
 * Posts Dao implementation
 *
 * @author Denis Biyovskiy
 * @version 1.00  16.07.2014.
 */
public class PostDaoImpl implements PostDao {

    @Override
    public Post getPost(long userId) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Post post = (Post) session.get(Post.class, userId);
        session.close();
        return post;
    }

    @Override
    public void setPost(Post post) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(post);
        session.getTransaction().commit();
        session.close();
    }
}
