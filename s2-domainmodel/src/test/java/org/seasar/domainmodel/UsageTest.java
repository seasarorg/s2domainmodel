package org.seasar.domainmodel;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seasar.domainmodel.domain.payroll.Employee;
import org.seasar.domainmodel.domain.payroll.EmployeeFactory;
import org.seasar.domainmodel.domain.payroll.EmployeeRepository;
import org.seasar.framework.unit.Seasar2;
import org.seasar.framework.unit.annotation.RootDicon;

@RunWith(Seasar2.class)
@RootDicon("app.dicon")
public class UsageTest {
    private EmployeeFactory    fFactory;
    private EmployeeRepository fRepository;

    @Test
    public void mainUsage() {
        Employee employee = fFactory.create();
        assertThat(employee.getTimeRecordRepository(), is(notNullValue()));

        List<Employee> list = fRepository.findAllAsList();
        for (Employee e : list) {
            assertThat(e.getTimeRecordRepository(), is(notNullValue()));
        }

        Employee[] array = fRepository.findAllAsArray();
        for (Employee e : array) {
            assertThat(e.getTimeRecordRepository(), is(notNullValue()));
        }
    }

    @Test
    public void performanceSensitive() {
        List<Employee> employees = fRepository.fastFindAll();
        for (Employee e : employees) {
            assertThat(e.getTimeRecordRepository(), is(nullValue()));
        }
    }
}
