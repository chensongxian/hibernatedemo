package com.csx.domain;

import com.csx.domain.xml.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyComponentPathImpl;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @Description: TODO
 * @Author: csx
 * @Date: 2018/02/24
 */
public class EmployeeTest {

    private static Session session=null;

    private static SessionFactory sessionFactory=null;

    private static Transaction transaction=null;

    @BeforeAll
    public static void before(){
        Configuration configuration=new Configuration().configure();
        configuration.setImplicitNamingStrategy(new ImplicitNamingStrategyComponentPathImpl());
        sessionFactory=configuration.buildSessionFactory();

        session=sessionFactory.openSession();

        transaction=session.beginTransaction();
    }

    @AfterAll
    public static void after(){
        transaction.commit();
        session.close();
        sessionFactory.close();
    }

    /**
     * 保存一个对象
     */
    @Test
    public void saveEmployee(){
        Employee employee=new Employee();
        employee.setEmpName("csx");
        employee.setWorkDate(new Date());

        session.save(employee);

    }

    /**
     * 更新一个对象
     */
    @Test
    public void updateEmployee(){
        Employee employee=new Employee();
        employee.setEmpId(1);
        employee.setEmpName("csx1");
        employee.setWorkDate(new Date());

        session.update(employee);
    }

    /**
     * 保存或更新
     * 有id则更新，没id则保存
     */
    @Test
    public void saveOrUpdateEmployee(){
        Employee employee=new Employee();
//        employee.setEmpId(1);
        employee.setEmpName("csx");
        employee.setWorkDate(new Date());

        session.saveOrUpdate(employee);
    }


    /**
     * 主键查询,支持懒加载
     * 要用到数据时才会向数据库发送查询语句
     *
     */
    @Test
    public void loadEmployee(){
        Employee employee=session.load(Employee.class,1);
        Assertions.assertNotNull(employee);

    }

    /**
     * 主键查询不支持懒加载
     * 及时加载
     */
    @Test
    public void getEmployee(){
        Employee employee=session.get(Employee.class,1);
//        Assertions.assertNotNull(employee);
    }

}
