package com.bionic.socialNetwork.dao.impl;

import com.bionic.socialNetwork.dao.PasswordDao;
import com.bionic.socialNetwork.dao.UserDao;
import com.bionic.socialNetwork.models.Group;
import com.bionic.socialNetwork.models.Interest;
import com.bionic.socialNetwork.models.Password;
import com.bionic.socialNetwork.models.User;
import com.bionic.socialNetwork.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author Dmytro Troshchuk, Denis Biyovskiy
 * @version 1.00  14.07.14.
 */
public class UserDaoImpl implements UserDao {
    @Override
    public void insert(User user, Password password) throws Exception {
        Session session;
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(user);
        password.setUserId(user.getId());
        session.save(password);
        session.getTransaction().commit();
        session.refresh(user);
        session.close();
    }

    @Override
    public User selectById(long id) throws Exception {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            User user = (User) session.get(User.class, id);
            return user;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public User selectByLogin(String login) throws Exception {

        Session session = HibernateUtil.getSessionFactory().openSession();

        Query query = session.createQuery(
                "SELECT id FROM User where login = '" + login + "'");
        List<Long> list = query.list();
        session.close();
        if (list.size() != 0) {
            User user = selectById(list.get(0));
            return user;
        } else {
            return null;
        }


    }

    @Override
    public List<User> selectByFullName(String name, String surname, int page)
    throws Exception {
        List<User> returnUser = new ArrayList<User>();
        Session session = HibernateUtil.getSessionFactory().openSession();

        Criteria criteria = session.createCriteria(User.class);
        criteria.add(Restrictions.eq("name", name))
                .add(Restrictions.eq("surname", surname))
                .setFirstResult(10 * page).setMaxResults(10);

        return returnUser;
    }

    @Override
    public List<User> selectNext(int page) throws Exception {
        int limit = 10;
        Session session = HibernateUtil.getSessionFactory().openSession();

        Criteria criteria = session.createCriteria(User.class);
        criteria.setMaxResults(10).setFirstResult(10 * page);
        List<User> users = criteria.list();
        session.close();

        return users;
    }

    @Override
    public void delete(User user) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();

        PasswordDao passwordDao = new PasswordDaoImpl();
        Password password = passwordDao.selectById(user.getId());
        session.beginTransaction();
        session.delete(password);
        session.delete(user);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(User user) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(user);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void deleteInterests(Interest interest, User user) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();

        SQLQuery query = session.createSQLQuery(
                "DELETE FROM Users_Interests WHERE user_id = " + user.getId() +
                " AND interest_id = " + interest.getInterests_id() + ";"
                                               );

        query.executeUpdate();

        session.close();
    }

    @Override
    public List<User> selectFollowingsNext(long id, int lot) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Criteria criteria = session.createCriteria(User.class);
        criteria.createAlias("myFollowings", "myFollowingsAlias");
        criteria.add(Restrictions.eq("myFollowingsAlias.id", id));
        criteria.setMaxResults(10);
        criteria.addOrder(Order.asc("id"));
        criteria.setFirstResult(lot * 10);

        List<User> list = criteria.list();
        session.close();
        return list;
    }

    @Override
    public List<User> selectFollowingsByFullName(String name, String surname,
                                                 long id, int lot)
    throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Criteria criteria = session.createCriteria(User.class);
        criteria.createAlias("myFollowings", "followingsAlias");
        criteria.setMaxResults(10);
        criteria.addOrder(Order.asc("id"));
        criteria.setFirstResult(lot * 10);

        List<User> list = criteria.list();
        List<User> resultList = new LinkedList<User>();
        session.close();
        for (User user : list) {
            if (user.getName().equals(name)
                && user.getSurname().equals(surname)
                && resultList.size() < 10) {
                resultList.add(user);
            }
        }
        return resultList;
    }

    @Override
    public void insertFollowing(User user, User hisFriend) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        SQLQuery query = session.createSQLQuery(
                "INSERT INTO Followings VALUES (" + user.getId() + ", " +
                hisFriend.getId() + ");"
                                               );

        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void deleteFollowing(User user, User hisFollowing) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        SQLQuery query = session.createSQLQuery(
                "DELETE FROM Followings WHERE follower_id = " + user.getId() +
                " AND following_id = " + hisFollowing.getId() + ";"
                                               );

        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Group> selectUserGroupsNext(long id, int lot) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Criteria criteria = session.createCriteria(Group.class);
        criteria.createAlias("followers", "followersAlias");
        criteria.add(Restrictions.eq("followersAlias.id", id));
        criteria.setMaxResults(10);
        criteria.addOrder(Order.asc("id"));
        criteria.setFirstResult(lot * 10);
        List<Group> list = criteria.list();

        session.close();
        return list;
    }

    @Override
    public List<Group> selectUserGroupsByName(long id, int lot, String name)
    throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Criteria criteria = session.createCriteria(Group.class);
        criteria.createAlias("followers", "followersAlias");
        criteria.addOrder(Order.asc("id"));
        criteria.setFirstResult(lot * 10);

        List<Group> list = criteria.list();
        List<Group> resultList = new LinkedList<Group>();
        session.close();
        for (Group group : list) {
            if (group.getName().equals(name)
                && resultList.size() < 10) {
                resultList.add(group);
            }
        }
        return resultList;
    }

    @Override
    public List<Interest> selectAllInterests(long id) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Criteria criteria = session.createCriteria(Interest.class);
        criteria.createAlias("users", "usersAlias");
        criteria.add(Restrictions.eq("usersAlias.id", id));
        List<Interest> list = criteria.list();
        session.close();
        return list;
    }

    @Override
    public boolean isFollowing(long userId1, long userId2) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();

        SQLQuery query = session.createSQLQuery(
                "SELECT * FROM Followings WHERE follower_id = " + userId1 +
                " AND following_id = " + userId2 + ";");

        boolean result = query.list().size() > 0;

        session.close();
        return result;
    }

    @Override
    public List<User> selectByInterest(long interest, int page)
    throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Criteria criteria = session.createCriteria(User.class);
        criteria.createAlias("interests", "interestsAlias");
        criteria.add(Restrictions.eq("interestsAlias.id", interest));
        criteria.setFirstResult(page * 10);
        criteria.setMaxResults(10);
        List<User> list = criteria.list();
        session.close();
        return list;
    }

    @Override
    public void insertGroup(long userId, long groupId) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();

        SQLQuery sqlQuery = session.createSQLQuery("INSERT INTO Users_Groups VALUES (:userId, :groupId);");
        sqlQuery.setParameter("userId", userId);
        sqlQuery.setParameter("groupId", groupId);
        sqlQuery.executeUpdate();

        session.getTransaction().commit();

        session.close();
    }

    @Override
    public void deleteGroup(long userId, long groupId) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();

        SQLQuery sqlQuery = session.createSQLQuery("DELETE FROM Users_Groups WHERE user_id = :userId AND group_id = :groupId ;");
        sqlQuery.setParameter("userId", userId);
        sqlQuery.setParameter("groupId", groupId);
        sqlQuery.executeUpdate();

        session.getTransaction().commit();

        session.close();
    }

    @Override
    public boolean isGroupFollowing(long userId, long groupId) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();

        SQLQuery query = session.createSQLQuery(
                "SELECT * FROM Users_Groups WHERE user_id = " + userId +
                " AND group_id = " + groupId + ";");

        boolean result = query.list().size() > 0;

        session.close();
        return result;
    }
}