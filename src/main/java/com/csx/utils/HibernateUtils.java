package com.csx.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created with IntelliJ IDEA.
 *
 * @Description: TODO
 * @Author: csx
 * @Date: 2018/02/24
 */
public class HibernateUtils {
    private static SessionFactory sessionFactory=null;

    private static final ThreadLocal<Session> SESSION_THREAD_LOCAL=new ThreadLocal<>();

    static {
        Configuration configuration=new Configuration().configure();
        sessionFactory=configuration.buildSessionFactory();
    }

    public static Session getSession(){
        Session session=SESSION_THREAD_LOCAL.get();
        if(session==null){
            session=sessionFactory.openSession();
            SESSION_THREAD_LOCAL.set(session);
        }
        return session;
    }

    public static void closeSession(){
        Session session=SESSION_THREAD_LOCAL.get();
        if(session!=null){
            session.close();
        }
        SESSION_THREAD_LOCAL.set(null);
    }
}
