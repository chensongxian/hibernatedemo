package com.csx.map.collection;

import com.csx.map.collection.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 *
 * @Description: TODO
 * @Author: csx
 * @Date: 2018/02/24
 */
public class CollectionTest {
    private static SessionFactory sf;
    static  {
        // 创建sf对象
        sf = new Configuration()
                .configure()
                .addClass(com.csx.domain.User.class)  //（测试） 会自动加载映射文件：Employee.hbm.xml
                .buildSessionFactory();
    }

    @Test
    public void testSaveSet() throws Exception {
        Session session = sf.openSession();
        session.beginTransaction();

        //-- 保存
        Set<String> addressSet = new HashSet<String>();
        addressSet.add("广州");
        addressSet.add("深圳");
        // 用户对象
        User user = new User();
        user.setUserName("Jack");
        user.setAddress(addressSet);

        // 保存
        session.save(user);

        session.getTransaction().commit();
        session.close();
    }

    // 保存list/map
    @Test
    public void testSaveList() throws Exception {
        Session session = sf.openSession();
        session.beginTransaction();
        User user = new User();
        user.setUserName("Tom");
//		// 用户对象  --  list
//		user.getAddressList().add("广州");
//		user.getAddressList().add("深圳");
//		// 保存
//		session.save(user);

        // 用户对象  --  Map
        user.getAddressMap().put("A0001", "广州");
        user.getAddressMap().put("A0002", "深圳");

        // 保存
        session.save(user);

        session.getTransaction().commit();
        session.close();
    }

}
