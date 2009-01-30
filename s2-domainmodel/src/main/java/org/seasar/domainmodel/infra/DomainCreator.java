package org.seasar.domainmodel.infra;

import static org.seasar.domainmodel.infra.S2DomainModelUtil.*;

import org.seasar.framework.container.ComponentCreator;
import org.seasar.framework.container.ComponentCustomizer;
import org.seasar.framework.container.ComponentDef;
import org.seasar.framework.container.InstanceDef;
import org.seasar.framework.container.assembler.AutoBindingDefFactory;
import org.seasar.framework.container.deployer.InstanceDefFactory;
import org.seasar.framework.container.factory.AnnotationHandler;
import org.seasar.framework.container.factory.AnnotationHandlerFactory;
import org.seasar.framework.convention.NamingConvention;

public class DomainCreator implements ComponentCreator {
    private static final String DOMAIN_PACKAGE = "domain";

    private NamingConvention    fNamingConvention;
    private ComponentCustomizer fDomainCustomizer;

    public DomainCreator(NamingConvention convention) {
        fNamingConvention = convention;
    }

    public ComponentDef createComponentDef(
            @SuppressWarnings("unchecked") Class componentClass) {
        if (!isDomainClass(componentClass)) {
            return null;
        }
        if (isInjectionTarget(componentClass)) {
            return componentDefOfDomainObject(componentClass);
        } else if (isLifeCycleObject(componentClass)) {
            return componentDefOfLifeCycleObject(componentClass);
        }
        return null;
    }

    private boolean isDomainClass(Class<?> componentClass) {
        String className = componentClass.getCanonicalName();
        String[] roots = fNamingConvention.getRootPackageNames();
        for (String root : roots) {
            if (className.startsWith(root + "." + DOMAIN_PACKAGE + ".")) {
                return true;
            }
        }
        return false;
    }

    private ComponentDef componentDefOfDomainObject(Class<?> componentClass) {
        return componentDef(componentClass, InstanceDefFactory.OUTER, false);
    }

    private ComponentDef componentDefOfLifeCycleObject(Class<?> componentClass) {
        return componentDef(componentClass, InstanceDefFactory.SINGLETON, true);
    }

    private ComponentDef componentDef(
            Class<?> componentClass,
            InstanceDef instanceDef,
            boolean customize) {
        AnnotationHandler handler = AnnotationHandlerFactory.getAnnotationHandler();
        ComponentDef def = handler.createComponentDef(
                componentClass,
                instanceDef,
                AutoBindingDefFactory.AUTO,
                false);
        if (def.getComponentName() == null) {
            def.setComponentName(fNamingConvention.fromClassNameToComponentName(componentClass.getName()));
        }
        handler.appendDI(def);
        if (customize) {
            customize(def);
        }
        handler.appendInitMethod(def);
        handler.appendDestroyMethod(def);
        handler.appendAspect(def);
        handler.appendInterType(def);
        return def;
    }

    private void customize(ComponentDef def) {
        if (fDomainCustomizer != null) {
            fDomainCustomizer.customize(def);
        }
    }

    public ComponentDef createComponentDef(String componentName) {
        Class<?> componentClass = fNamingConvention.fromComponentNameToClass(componentName);
        if (componentClass == null) {
            return null;
        }
        return createComponentDef(componentClass);
    }

    //--------------------------------------------------------------------------
    // Setter
    //--------------------------------------------------------------------------

    public void setDomainCustomizer(ComponentCustomizer customizer) {
        fDomainCustomizer = customizer;
    }
}
