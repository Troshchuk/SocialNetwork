package com.bionic.socialNetwork.dao;

import com.bionic.socialNetwork.models.Group;
import com.bionic.socialNetwork.models.Interest;
import com.bionic.socialNetwork.models.Password;
import com.bionic.socialNetwork.models.User;

import java.util.List;

/**
 * DAO for User's entity
 *
 * @author Dmytro Troshchuk, Denis Biyovskiy
 * @version 1.00  14.07.14.
 */
public interface UserDao {
    /**
     * Insert user entity to database with password
     *
     * @param user User's entity
     */
    public void insert(User user, Password password) throws Exception;

    /**
     * Select user from database by id
     *
     * @param id identical key
     * @return User entity with this id
     */
    public User selectById(long id) throws Exception;

    /**
     * Select user from database by login
     *
     * @param login login
     * @return User entity with this login
     */
    public User selectByLogin(String login) throws Exception;

    /**
     * Select users searching by name
     *
     * @param name
     * @return List<User>
     * @throws Exception
     */
    public List<User> selectByFullName(String name, String surname, int page)
            throws Exception;

    /**
     * Select next 10 (or less) users begin with id
     *
     * @param page id at which to begin select
     * @return List 10 (or less) of users
     */
    public List<User> selectNext(int page) throws Exception;

    /**
     * Delete user from database
     *
     * @param user user which delete from database
     */
    public void delete(User user) throws Exception;

    public void update(User user) throws Exception;

    public void deleteInterests(Interest interest, User user) throws Exception;

    public List<User> selectFollowingsNext(long id, int lot) throws Exception;

    public List<User> selectFollowingsByFullName(String name, String surname,
                                                 long id, int lot)
    throws Exception;

    public void insertFollowing(User user, User hisFollowing) throws Exception;

    public void deleteFollowing(User user, User hisFollowing) throws Exception;

    public List<Group> selectUserGroupsNext(long id, int lot) throws Exception;

    public List<Group> selectUserGroupsByName(long id, int lot, String name)
    throws Exception;

    public List<Interest> selectAllInterests(long id) throws Exception;

    public boolean isFollowing(long userId1, long userId2) throws Exception;

    public List<User> selectByInterest(long interest, int page)
    throws Exception;

    public void insertGroup(long userId, long groupId) throws Exception;

    public void deleteGroup(long userId, long groupId) throws Exception;

    public boolean isGroupFollowing(long userId, long groupId) throws Exception;
}
