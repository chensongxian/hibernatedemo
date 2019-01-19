package com.csx.dao.impl;

import com.csx.dao.IEmployeeDao;
import com.csx.domain.xml.Employee;
import com.csx.utils.HibernateUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Description: TODO
 * @Author: csx
 * @Date: 2018/02/24
 */
public class EmployeeDaoImpl implements IEmployeeDao{
    @Override
    public Employee findById(Serializable id) {
        Session session=null;
        Transaction transaction=null;
        try {
            session= HibernateUtils.getSession();
            transaction=session.beginTransaction();
            return session.get(Employee.class,id);
        }catch (Exception e){
            throw new RuntimeException(e);
        }finally {
            transaction.commit();
            session.close();
        }
    }

    @Override
    public List<Employee> getAll() {
        Session session=null;
        Transaction transaction=null;
        try {
            session=HibernateUtils.getSession();
            transaction=session.beginTransaction();
            Query query = session.createQuery("FROM Employee");
            return query.list();
        }catch (Exception e){
            throw new RuntimeException(e);
        }finally {
            transaction.commit();
            session.close();
        }
    }

    @Override
    public List<Employee> getAll(String employeeName) {
        Session session=null;
        Transaction transaction=null;
        try {
            session=HibernateUtils.getSession();
            transaction=session.beginTransaction();
            Query query = session.createQuery("FROM Employee WHERE empName=?");
            query.setParameter(0,employeeName);
            return query.list();
        }catch (Exception e){
            throw new RuntimeException(e);
        }finally {
            transaction.commit();
            session.close();
        }
    }

    @Override
    public List<Employee> getAll(int index, int count) {
        Session session=null;
        Transaction transaction=null;
        try {
            session=HibernateUtils.getSession();
            transaction=session.beginTransaction();
            Query query = session.createQuery("FROM Employee");
            query.setFirstResult(index);
            query.setMaxResults(count);
            return query.list();
        }catch (Exception e){
            throw new RuntimeException(e);
        }finally {
            transaction.commit();
            session.close();
        }
    }

    @Override
    public void save(Employee emp) {
        Session session=null;
        Transaction transaction=null;
        try {
            session=HibernateUtils.getSession();
            transaction=session.beginTransaction();
            session.save(emp);
        }catch (Exception e){
            throw new RuntimeException(e);
        }finally {
            transaction.commit();
            session.close();
        }
    }

    @Override
    public void update(Employee emp) {
        Session session=null;
        Transaction transaction=null;
        try {
            session=HibernateUtils.getSession();
            transaction=session.beginTransaction();
            session.update(emp);
        }catch (Exception e){
            throw new RuntimeException(e);
        }finally {
            transaction.commit();
            session.close();
        }
    }

    @Override
    public void delete(Serializable id) {
        Session session=null;
        Transaction transaction=null;
        try {
            session=HibernateUtils.getSession();
            transaction=session.beginTransaction();
            Employee employee=session.get(Employee.class,id);
            if(employee!=null){
                session.delete(employee);
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }finally {
            transaction.commit();
            session.close();
        }
    }
}
