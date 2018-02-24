package com.csx.map.many2many;

import com.csx.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 *
 * @Description: TODO
 * @Author: csx
 * @Date: 2018/02/24
 */
public class Many2manyTest {
    @Test
    public void save(){
        Session session = HibernateUtils.getSession();
        Transaction tx = session.beginTransaction();
        Role r1 = new Role();
        r1.setName("数据录入人员");
        session.save(r1);
        Role r2 = new Role();
        r2.setName("商务主管");
        session.save(r2);
        Role r3 = new Role();
        r3.setName("大区经理");
        session.save(r3);
        User u1 = new User();
        u1.setName("10");
        Set<Role> u1Roles = new HashSet<Role>();
        u1Roles.add(r1);
        u1Roles.add(r2);
        u1.setRoles(u1Roles);
        User u2 = new User();
        u2.setName("祖儿");
        Set<Role> u2Roles = new HashSet<Role>();
        u2Roles.add(r2);
        u2Roles.add(r3);
        u2.setRoles(u2Roles);
        User u3 = new User();
        u3.setName("成龙");
        Set<Role> u3Roles = new HashSet<Role>();
        u3Roles.add(r1);
        u3Roles.add(r2);
        u3Roles.add(r3);
        u3.setRoles(u3Roles);
        session.save(u1);
        session.save(u2);
        session.save(u3);
        tx.commit();
    }

    @Test
    public void get(){
        Session session = HibernateUtils.getSession();
        Transaction tx = session.beginTransaction();
        User user = (User)session.load(User.class, 1);
        System.out.println("user.name=" + user.getName());
        for (Iterator<Role> iter = user.getRoles().iterator(); iter.hasNext();){
            Role role = (Role) iter.next();
            System.out.println(role.getName());
        }
        tx.commit();
    }
}
