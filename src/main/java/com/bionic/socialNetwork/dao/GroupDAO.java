package com.bionic.socialNetwork.dao;

/**
 * @author yoalex5
 * @version 1.0 17.07.14
 */
public interface GroupDAO<T> {
    public T selectById(long id) throws Exception;
    public void insert(T groupObj) throws Exception;
    public void update(T groupObj) throws Exception;
    public void delete(T groupObj) throws Exception;
}
