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
    public String getPasswordById(long id) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Password password = (Password) session.get(Password.class, id);
        session.close();
        return password.getPassword();
    }

    @Override
    public void setPasswordById(long id, String pass) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Password password = new Password(id, pass);
        session.save(password);
        session.getTransaction().commit();
        session.close();
    }
}
