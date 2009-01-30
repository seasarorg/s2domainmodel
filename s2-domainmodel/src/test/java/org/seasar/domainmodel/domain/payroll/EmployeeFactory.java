package org.seasar.domainmodel.domain.payroll;

import org.seasar.domainmodel.domain.annotation.LifeCycleObject;

@LifeCycleObject
public class EmployeeFactory {

    public Employee create() {
        return new Employee();
    }
}
