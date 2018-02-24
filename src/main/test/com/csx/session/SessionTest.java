package com.csx.session;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Description: TODO
 * @Author: csx
 * @Date: 2018/02/24
 */
public class SessionTest {
    private static SessionFactory sf;
    static  {
        // 创建sf对象
        sf = new Configuration()
                .configure()
                .buildSessionFactory();
    }

    /**
     * 不同的session是否会共享缓存数据?
     * 答：不会，通过下面代码会发现进行了两次update语句，说明两个session不会共享缓存
     */
    @Test
    public void twoSessionCache(){
        Session session1=sf.openSession();
        Transaction transaction1=session1.beginTransaction();

        Session session2=sf.openSession();
        Transaction transaction2=session2.beginTransaction();

        //user放入session1缓冲区
        User user=session1.get(User.class,1);
        //user放入session2缓冲区
        session2.update(user);

        //修改对象
        user.setUserName("xxx");

        transaction1.commit();
        session1.close();
        transaction2.commit();
        session2.close();

    }

    /**
     * iterate查询
     *
     *  N+1查询； N表示所有的记录总数
     * 即会先发送一条语句查询所有记录的主键（1），
     * 再根据每一个主键再去数据库查询（N）！
     *
     *
     * 通过测试发现，第二次查询时只是再次查询了所有记录的主键，并未再次根据主键去数据库查询
     * 由此可见是从缓存中取得数据
     *
     * iterate查询会放入缓存，也会从缓存中取数据！
     */
    @Test
    public void testIterator(){
        Session session=sf.openSession();
        Transaction transaction=session.beginTransaction();

        //HQL查询
        Query query=session.createQuery("from User");
        Iterator iterate = query.iterate();
        while (iterate.hasNext()){
            User user= (User) iterate.next();
            System.out.println(user);
        }
        System.out.println("-------------------");
        /*
         * 测试缓存
         *
         * 再次取数据看是从缓存中取还是数据库
         */
        iterate = query.iterate();
        while (iterate.hasNext()){
            User user= (User) iterate.next();
            System.out.println(user);
        }
        transaction.commit();
        session.close();
    }

    /**
     * list查询
     *
     * 一次性把数据查询处理
     *
     * 第二次查询时并未从缓存中取，而是再次从数据库里面查询
     */
    @Test
    public void testList(){
        Session session=sf.openSession();
        Transaction transaction=session.beginTransaction();

        //HQL查询
        Query query=session.createQuery("from User");
        List<User> users1 = query.list();
        for(User user:users1){
            System.out.println(user);
        }
        System.out.println("-------------------");
        /*
         * 测试缓存
         *
         * 再次取数据看是从缓存中取还是数据库
         */
        users1 = query.list();
        for(User user:users1){
            System.out.println(user);
        }
        transaction.commit();
        session.close();
    }

    /**
     * 验证list查询是否把数据放入缓存
     *
     * 第一次用list查询，第二次用iterate查询
     * 发现第二次查询时只是查询了所有记录主键，并未再次查询所有记录
     * 说明list查询已经把记录放入缓存
     */
    @Test
    public void list_iterator(){
        Session session=sf.openSession();
        Transaction transaction=session.beginTransaction();

        //HQL查询
        Query query=session.createQuery("from User");
        List<User> users1 = query.list();
        for(User user:users1){
            System.out.println(user);
        }
        System.out.println("-------------------");
        /*
         * 测试缓存
         *
         * 再次取数据看是从缓存中取还是数据库
         */
        Iterator iterate = query.iterate();
        while (iterate.hasNext()){
            User user= (User) iterate.next();
            System.out.println(user);
        }
        transaction.commit();
        session.close();
    }

    /**
     * 因为load启用了懒加载
     * 如果session关闭后，使用懒加载数据报错：
     * org.hibernate.LazyInitializationException: could not initialize proxy - no Session
     *
     * 解决session关闭后不能使用懒加载数据的问题
     *
     *  方式1： 先使用一下数据
     * dept.getDeptName();
     *  方式2：强迫代理对象初始化
     * Hibernate.initialize(dept);
     * 方式3：关闭懒加载
     * 设置lazy=false;
     * 方式4： 在使用数据之后，再关闭session！
     */
    @Test
    public void testLazy(){
        Session session=sf.openSession();
//        Transaction transaction=session.beginTransaction();

        User user=session.load(User.class,1);
        /*
         * 解决session关闭后不能使用懒加载数据的问题
         *
         */
        //方式1.先使用一下数据
//        System.out.println(user.getUserName());
        //方式2：强迫代理对象初始化
        Hibernate.initialize(user);
        //方式3:关闭懒加载
//        transaction.commit();
        session.close();
        System.out.println(user);

    }

}
