package org.seasar.domainmodel.infra;

import static org.seasar.domainmodel.infra.S2DomainModelUtil.*;

import java.lang.reflect.Method;
import java.util.Collection;

import org.aopalliance.intercept.MethodInvocation;
import org.seasar.framework.aop.interceptors.AbstractInterceptor;
import org.seasar.framework.container.S2Container;

public class S2DomainModelInterceptor extends AbstractInterceptor {
    private static final long serialVersionUID = 2815541744871166651L;

    private S2Container       fRootContainer;

    public Object invoke(MethodInvocation invocation) throws Throwable {
        Object ret = invocation.proceed();
        if (ret == null || isIgnored(invocation.getMethod())) {
            return ret;
        }
        if (ret instanceof Collection<?>) {
            injectCollection((Collection<?>) ret);
        } else if (ret.getClass().isArray()) {
            injectArray((Object[]) ret);
        } else {
            injectDomainObject(ret);
        }
        return ret;
    }

    private static boolean isIgnored(Method method) {
        return method.getReturnType().isPrimitive()
                || isPerformanceSensitive(method);
    }

    private void injectCollection(Collection<?> domainObjects) {
        if (domainObjects.isEmpty()
                || !isInjectionTarget(domainObjects.iterator().next())) {
            return;
        }
        for (Object domainObject : domainObjects) {
            doInject(domainObject);
        }
    }

    private void injectArray(Object[] domainObjects) {
        if (domainObjects.length == 0 || !isInjectionTarget(domainObjects[0])) {
            return;
        }
        for (Object domainObject : domainObjects) {
            doInject(domainObject);
        }
    }

    private void injectDomainObject(Object domainObject) {
        if (isInjectionTarget(domainObject)) {
            doInject(domainObject);
        }
    }

    private void doInject(Object domainObject) {
        fRootContainer.injectDependency(domainObject);
    }

    //--------------------------------------------------------------------------
    // Setter
    //--------------------------------------------------------------------------

    public void setContainer(S2Container container) {
        fRootContainer = container.getRoot();
    }
}
