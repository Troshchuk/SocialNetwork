package com.bionic.socialNetwork.dao.impl;

import com.bionic.socialNetwork.dao.PrivateMessageDao;
import com.bionic.socialNetwork.models.PrivateMessage;
import com.bionic.socialNetwork.util.HibernateUtil;
import org.hibernate.Session;

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
    public void insert(PrivateMessage privateMessage) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(privateMessage);
        session.getTransaction().commit();
        session.close();
    }
}
