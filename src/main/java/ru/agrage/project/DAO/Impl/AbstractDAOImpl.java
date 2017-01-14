package ru.agrage.project.DAO.Impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ru.agrage.project.DAO.AbstractDAO;
import ru.agrage.project.Models.UserModel;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by dmitry on 1/4/17.
 */

public abstract class AbstractDAOImpl<PK extends Serializable, T> implements AbstractDAO<PK, T>
{
    protected Class<? extends T> mainClass;

    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(final SessionFactory sessionFactory)
    {
        this.sessionFactory = sessionFactory;
    }

    public Session getSession()
    {
        return sessionFactory.getCurrentSession();
    }

    public AbstractDAOImpl()
    {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        mainClass = (Class) pt.getActualTypeArguments()[0];
    }

    @Override
    @Transactional
    public void create(final T object)
    {
        getSession().save(object);
    }

    @Override
    @Transactional
    public void delete(final T object)
    {
        getSession().delete(object);
    }

}
