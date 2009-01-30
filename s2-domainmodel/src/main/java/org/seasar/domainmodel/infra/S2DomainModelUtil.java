package org.seasar.domainmodel.infra;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.seasar.domainmodel.domain.annotation.LifeCycleObject;
import org.seasar.domainmodel.domain.annotation.OutsideAggregate;
import org.seasar.domainmodel.domain.annotation.PerformanceSensitive;

public final class S2DomainModelUtil {

    public static boolean isInjectionTarget(Object obj) {
        return isInjectionTarget(obj.getClass());
    }

    public static boolean isInjectionTarget(Class<?> clazz) {
        return isAnnotatedWith(clazz, OutsideAggregate.class);
    }

    public static boolean isLifeCycleObject(Class<?> clazz) {
        return isAnnotatedWith(clazz, LifeCycleObject.class);
    }

    private static boolean isAnnotatedWith(
            Class<?> clazz,
            Class<? extends Annotation> annotation) {
        return clazz.getAnnotation(annotation) != null;
    }

    public static boolean isPerformanceSensitive(Method method) {
        return method.getAnnotation(PerformanceSensitive.class) != null;
    }

    //--------------------------------------------------------------------------

    private S2DomainModelUtil() {}
}
