package com.bionic.socialNetwork.dao.impl;

import com.bionic.socialNetwork.dao.InviteDao;
import com.bionic.socialNetwork.models.Invite;
import com.bionic.socialNetwork.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

/**
 * @author Denis
 * @version 1.0 18.07.14
 */
public class InviteDaoImpl implements InviteDao {
    @Override
    public void insert(Invite invite) throws Exception {
        Session session;
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        session.save(invite);

        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(Invite invite) throws Exception {
        Session session;
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        session.delete(invite);

        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Invite selectById(long inviteId) throws Exception {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Invite invite = (Invite) session.get(Invite.class, inviteId);
            return invite;
        }
        finally {
            if (session!= null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public Invite selectByInvite(String inviteStr) throws Exception {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery(
                    "SELECT inviteId FROM Invite where invite = '" + inviteStr +
                    "'");
            List<Long> inviteId = query.list();
            Invite invite = (Invite) session.get(Invite.class, inviteId.get(0));
            return invite;
        } finally {
            if (session!= null && session.isOpen()) {
                session.close();
            }
        }
    }
}
