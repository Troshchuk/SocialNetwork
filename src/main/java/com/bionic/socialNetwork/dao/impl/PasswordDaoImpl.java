package com.bionic.socialNetwork.dao.impl;

import com.bionic.socialNetwork.dao.PasswordDao;
import com.bionic.socialNetwork.models.Password;
import com.bionic.socialNetwork.util.HibernateUtil;
import org.hibernate.Session;

/**
 * @author Dmytro Troshchuk
 * @version 1.00  15.07.14.
 */
public class PasswordDaoImpl implements PasswordDao {

    @Override
    public Password selectById(long id) throws Exception {
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            Password password = (Password) session.get(Password.class, id);
            return password;
        }finally {
            session.close();
        }

    }

    @Override
    public void update(Password password) throws Exception {
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(password);
            session.getTransaction().commit();
        }finally {
            session.close();
        }


    }

}
