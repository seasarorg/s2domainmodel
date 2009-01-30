package org.seasar.domainmodel.domain.payroll;

import java.util.ArrayList;
import java.util.List;

import org.seasar.domainmodel.domain.annotation.LifeCycleObject;
import org.seasar.domainmodel.domain.annotation.PerformanceSensitive;

@LifeCycleObject
public class EmployeeRepository {

    public List<Employee> findAllAsList() {
        List<Employee> employees = new ArrayList<Employee>();
        for (int i = 0; i < 10; i++) {
            employees.add(new Employee());
        }
        return employees;
    }

    public Employee[] findAllAsArray() {
        Employee[] employees = new Employee[10];
        for (int i = 0; i < 10; i++) {
            employees[i] = new Employee();
        }
        return employees;
    }

    @PerformanceSensitive
    public List<Employee> fastFindAll() {
        List<Employee> employees = new ArrayList<Employee>();
        for (int i = 0; i < 10; i++) {
            employees.add(new Employee());
        }
        return employees;
    }
}
