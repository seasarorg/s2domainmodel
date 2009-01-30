package org.seasar.domainmodel.domain;

public interface Specification<T> {

    boolean isSatisfiedBy(T obj);

    boolean isGeneralizationOf(Specification<T> spec);

    Specification<T> and(Specification<T> spec);

    Specification<T> or(Specification<T> spec);

    Specification<T> remainderUnsatisfiedBy(T obj);
}
