package com.bionic.socialNetwork.dao.impl;

import com.bionic.socialNetwork.dao.PrivateMessageDao;
import com.bionic.socialNetwork.models.PrivateMessage;
import com.bionic.socialNetwork.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

/**
 * Private messages Dao implementation
 *
 * @author Denis Biyovskiy
 * @version 1.00  16.07.2014.
 */
public class PrivateMessageDaoImpl implements PrivateMessageDao {


    @Override
    public PrivateMessage selectBySentId(long id) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        PrivateMessage privateMessage = (PrivateMessage) session.get(PrivateMessage.class, id);
        session.close();
        return privateMessage;
    }

    @Override
    public PrivateMessage selectByReceiverId(long id) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        PrivateMessage privateMessage = (PrivateMessage) session.get(PrivateMessage.class, id);
        session.close();
        return privateMessage;
    }

    @Override
    public List<PrivateMessage> selectNextSentId(long beginId) throws Exception {
        int limit = 10;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery(
                "FROM PrivateMessage WHERE sentUser >= " + beginId + " AND sentUser < " +
                        (beginId + limit));
        List<PrivateMessage> privateMessages = query.list();
        session.close();
        return privateMessages;
    }

    @Override
    public List<PrivateMessage> selectNextReceiverId(long beginId) throws Exception {
        int limit = 10;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery(
                "FROM PrivateMessage WHERE receiverUser >= " + beginId + " AND receiverUser < " +
                        (beginId + limit));
        List<PrivateMessage> privateMessages = query.list();
        session.close();
        return privateMessages;
    }

    @Override
    public void insert(PrivateMessage privateMessage) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(privateMessage);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(PrivateMessage privateMessage) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(privateMessage);
        session.getTransaction().commit();
        session.close();
    }
}
