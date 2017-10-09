package com.tr.wordbook.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * Baba Dao sınıfıdır.
 *
 * @author Koray PEKER
 */
@Transactional(rollbackFor = Exception.class)
public abstract class BaseDao <T extends Object> {

    private final Class<T> clazz;

    public BaseDao(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Autowired
    private SessionFactory sessionFactory;

    public void delete(T target) {
        getCurrentSession().delete(target);
    }

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public List<T> findAll() {
        return getCurrentSession().createCriteria(clazz).list();
    }

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public List<T> findAllOrdered(Order order) {
        return getCurrentSession().createCriteria(clazz).addOrder(order).list();
    }

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public T findById(Serializable id) {
        return (T) getCurrentSession().get(clazz, id);
    }

    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @SuppressWarnings("unchecked")
    public T merge(T target) {
        return (T) getCurrentSession().merge(target);
    }

    public void flush(){
        getCurrentSession().flush();
    }


}
