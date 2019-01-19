package com.csx.main;

import com.csx.dao.IEmployeeDao;
import com.csx.dao.impl.EmployeeDaoImpl;
import com.csx.domain.xml.Employee;

import java.util.Date;

/**
 * @author csx
 * @Package com.csx.main
 * @Description: TODO
 * @date 2019/1/19 0019
 */
public class Main {
    public static void main(String[] args) {
        IEmployeeDao employeeDao = new EmployeeDaoImpl();
        employeeDao.save(new Employee("1", new Date()));
    }
}
