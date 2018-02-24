package com.csx.map.one2many;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.Test;

/**
 * Created with IntelliJ IDEA.
 *
 * @Description:
 * 在一对多与多对一的关联关系中，保存数据最好的通过多的一方来维护关系，
 * 这样可以减少update语句的生成，从而提高hibernate的执行效率！
 * @Author: csx
 * @Date: 2018/02/24
 */
public class One2ManyTest {
    private static SessionFactory sf;
    static {
        sf = new Configuration()
                .configure()
                .addClass(Dept.class)
                .addClass(Employee.class)   // 测试时候使用
                .buildSessionFactory();
    }

    /**
     * 保存， 部门方 【一的一方法操作】
     */
    @Test
    public void save() {

        Session session = sf.openSession();
        session.beginTransaction();

        // 部门对象
        Dept dept = new Dept();
        dept.setDeptName("应用开发部");
        // 员工对象
        Employee emp_zs = new Employee();
        emp_zs.setEmpName("张三");
        Employee emp_ls = new Employee();
        emp_ls.setEmpName("李四");
        // 关系
        dept.getEmps().add(emp_zs);
        dept.getEmps().add(emp_ls);

        // 保存
        session.save(emp_zs);
        session.save(emp_ls);
        session.save(dept); // 保存部门，部门下所有的员工

        session.getTransaction().commit();
        session.close();
		/*
		 *  结果
		 *  Hibernate: insert into t_employee (empName, salary, dept_id) values (?, ?, ?)
			Hibernate: insert into t_employee (empName, salary, dept_id) values (?, ?, ?)
			Hibernate: insert into t_dept (deptName) values (?)
			Hibernate: update t_employee set deptId=? where empId=?    维护员工引用的部门的id
			Hibernate: update t_employee set deptId=? where empId=?
		 */
    }

    /**
     * 【推荐】 保存， 部员方 【多的一方法操作】
     */
    @Test
    public void save2() {

        Session session = sf.openSession();
        session.beginTransaction();

        // 部门对象
        Dept dept = new Dept();
        dept.setDeptName("综合部");
        // 员工对象
        Employee emp_zs = new Employee();
        emp_zs.setEmpName("张三");
        Employee emp_ls = new Employee();
        emp_ls.setEmpName("李四");
        // 关系
        emp_zs.setDept(dept);
        emp_ls.setDept(dept);


        // 保存
        session.save(dept); // 先保存一的方法
        session.save(emp_zs);
        session.save(emp_ls);// 再保存多的一方，关系回自动维护(映射配置完)

        session.getTransaction().commit();
        session.close();
		/*
		 *  结果
		 *  Hibernate: insert into t_dept (deptName) values (?)
			Hibernate: insert into t_employee (empName, salary, dept_id) values (?, ?, ?)
			Hibernate: insert into t_employee (empName, salary, dept_id) values (?, ?, ?)
			少生成2条update  sql
		 */
    }

}
