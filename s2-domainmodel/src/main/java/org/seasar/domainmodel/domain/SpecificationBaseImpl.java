package org.seasar.domainmodel.domain;

public abstract class SpecificationBaseImpl<T> implements Specification<T> {

    public boolean isGeneralizationOf(Specification<T> spec) {
        throw new UnsupportedOperationException("not implemented.");
    }

    public final Specification<T> and(Specification<T> spec) {
        return new AndSpecification<T>(this, spec);
    }

    public final Specification<T> or(Specification<T> spec) {
        return new OrSpecification<T>(this, spec);
    }

    public Specification<T> remainderUnsatisfiedBy(T obj) {
        throw new UnsupportedOperationException("not implemented.");
    }

    private static class AndSpecification<U> extends SpecificationBaseImpl<U> {
        private Specification<U> fSpecification1;
        private Specification<U> fSpecification2;

        public AndSpecification(Specification<U> spec1, Specification<U> spec2) {
            fSpecification1 = spec1;
            fSpecification2 = spec2;
        }

        public boolean isSatisfiedBy(U obj) {
            return fSpecification1.isSatisfiedBy(obj)
                    && fSpecification2.isSatisfiedBy(obj);
        }
    }

    private static class OrSpecification<U> extends SpecificationBaseImpl<U> {
        private Specification<U> fSpecification1;
        private Specification<U> fSpecification2;

        public OrSpecification(Specification<U> spec1, Specification<U> spec2) {
            fSpecification1 = spec1;
            fSpecification2 = spec2;
        }

        public boolean isSatisfiedBy(U obj) {
            return fSpecification1.isSatisfiedBy(obj)
                    || fSpecification2.isSatisfiedBy(obj);
        }
    }
}
