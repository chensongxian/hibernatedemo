package com.csx.dao;

import com.csx.domain.Employee;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Description: TODO
 * @Author: csx
 * @Date: 2018/02/24
 */
public interface IEmployeeDao {
    public Employee findById(Serializable id);

    public List<Employee> getAll();

    public List<Employee> getAll(String employeeName);

    public List<Employee> getAll(int index, int count);

    public void save(Employee emp);

    public void update(Employee emp);

    public void delete(Serializable id);
}
