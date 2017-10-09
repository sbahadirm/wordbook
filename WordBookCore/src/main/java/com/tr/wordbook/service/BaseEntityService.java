package com.tr.wordbook.service;

import com.tr.wordbook.dao.BaseDao;
import org.hibernate.criterion.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author Koray PEKER
 * @since 0.0.1
 */
public abstract class BaseEntityService <E extends Object, D extends BaseDao<E>> {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private Class<D> daoClazz;

    @Autowired
    D dao;

    protected BaseEntityService(Class<D> daoClazz) {
        this.daoClazz = daoClazz;
    }

    public D getDao() {
        return dao;
    }

    public E findById(long id) {
        return dao.findById(id);
    }

    public List<E> findAll() {
        return dao.findAll();
    }

    public List<E> findAllOrdered(Order order) {
        return dao.findAllOrdered(order);
    }

    public E save(E e) {
        return dao.merge(e);
    }

    public void delete(E e) {
        dao.delete(e);
    }

}
