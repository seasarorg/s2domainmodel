package org.seasar.domainmodel;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.seasar.domainmodel.domain.payroll.Employee;
import org.seasar.domainmodel.domain.payroll.EmployeeFactory;
import org.seasar.domainmodel.infra.DomainCreator;
import org.seasar.framework.container.ComponentCreator;
import org.seasar.framework.container.ComponentDef;
import org.seasar.framework.container.deployer.InstanceDefFactory;
import org.seasar.framework.convention.NamingConvention;
import org.seasar.framework.convention.impl.NamingConventionImpl;

public class DomainCreatorTest {
    private ComponentCreator fCreator;

    @Before
    public void setUp() {
        fCreator = new DomainCreator(namingConvention());
    }

    @Test
    public void createComponentDefClass_domainObject() {
        ComponentDef employeeDef = fCreator.createComponentDef(Employee.class);
        assertThat(employeeDef, is(notNullValue()));
        assertThat(employeeDef.getInstanceDef(), is(InstanceDefFactory.OUTER));
    }

    @Test
    public void createComponentDefClass_lifecycleObject() {
        ComponentDef factoryDef = fCreator.createComponentDef(EmployeeFactory.class);
        assertThat(factoryDef, is(notNullValue()));
        assertThat(
                factoryDef.getInstanceDef(),
                is(InstanceDefFactory.SINGLETON));
    }

    //--------------------------------------------------------------------------

    private static NamingConvention namingConvention() {
        NamingConventionImpl convention = new NamingConventionImpl();
        convention.addRootPackageName(DomainCreatorTest.class.getPackage().getName());
        return convention;
    }
}
