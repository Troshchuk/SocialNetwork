package com.bionic.socialNetwork.dao;

import com.bionic.socialNetwork.models.Interest;
import com.bionic.socialNetwork.models.User;

import java.util.List;

/**
 * Dao for Interest Entity
 *
 * @author Matvii Mitnitskyi
 * @version 1.00 17.07.14
 */
public interface InterestDao {
    /**
     * Insert Interest Entity to database;
     *
     * @param interest Interest`s Entity
     */
    public void insert(Interest interest) throws Exception;

    /**
     * Select Interest Entity from database by id
     *
     * @return Interest
     * @param id
     */
    public Interest selectById(long id) throws Exception;

    /**
     * Take interest entity from database.
     *
     * @return interest entity
     * @param interest string interest
     */
    public Interest selectByInterest(String interest) throws Exception;

    /**
     * Deletes pointed interest from database;
     *
     * @throws Exception
     * @param interest Interest`s Entity
     */
    public void delete (Interest interest) throws Exception;

    public void update (Interest interest) throws Exception;

    public List<Interest> selectByUser(User user) throws Exception;
}
