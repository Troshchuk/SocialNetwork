package com.bionic.socialNetwork.dao.impl;

import com.bionic.socialNetwork.dao.AddUserDao;
import com.bionic.socialNetwork.models.AddUser;
import com.bionic.socialNetwork.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

/**
 * @author Denis
 * @version 1.00  18.07.2014.
 */
public class AddUserDaoImpl implements AddUserDao {

    @Override
    public AddUser selectByInvite(String invite) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery(
                "SELECT addUserId FROM AddUser where invite = '" + invite + "'");
        List<Long> addUserId = query.list();
        AddUser addUser = (AddUser) session.get(AddUser.class, addUserId.get(0));
        session.close();
        return addUser;
    }

    @Override
    public AddUser selectById(long addUserId) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        AddUser addUser = (AddUser) session.get(AddUser.class, addUserId);
        session.close();
        return addUser;
    }

    @Override
    public void delete(AddUser addUser) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(addUser);
        session.getTransaction().commit();
        session.close();
    }
}
