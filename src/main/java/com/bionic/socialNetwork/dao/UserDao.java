package com.bionic.socialNetwork.dao;

import com.bionic.socialNetwork.models.Interest;
import com.bionic.socialNetwork.models.User;

import java.util.List;

/**
 * DAO for User's entity
 *
 * @author Dmytro Troshchuk
 * @version 1.00  14.07.14.
 */
public interface UserDao {
    /**
     * Insert user entity to database
     *
     * @param user User's entity
     */
    public void insert(User user) throws Exception;

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
     * Select next 10 (or less) users begin with id
     *
     * @param beginId id at which to begin select
     * @return List 10 (or less) of users
     */
    public List<User> selectNext(long beginId) throws Exception;

    /**
     * Delete user from database
     *
     * @param user user which delete from database
     */
    public void delete(User user) throws Exception;
}
