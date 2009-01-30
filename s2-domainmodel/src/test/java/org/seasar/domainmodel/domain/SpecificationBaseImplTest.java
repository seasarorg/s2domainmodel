package org.seasar.domainmodel.domain;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class SpecificationBaseImplTest {

    @Test
    public void andAndOr_usage() {
        Specification<String> containsASpec = new SpecificationBaseImpl<String>() {
            public boolean isSatisfiedBy(String str) {
                return str != null && str.indexOf('A') >= 0;
            }
        };
        Specification<String> containsBSpec = new SpecificationBaseImpl<String>() {
            public boolean isSatisfiedBy(String str) {
                return str != null && str.indexOf('B') >= 0;
            }
        };

        assertThat(
                containsASpec.and(containsBSpec).isSatisfiedBy("ABC"),
                is(true));
        assertThat(
                containsASpec.and(containsBSpec).isSatisfiedBy("BCD"),
                is(false));
        assertThat(
                containsASpec.or(containsBSpec).isSatisfiedBy("BCD"),
                is(true));
        assertThat(
                containsASpec.or(containsBSpec).isSatisfiedBy("CDE"),
                is(false));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void isGeneralizationOf_notImplemented() {
        new SpecificationBaseImpl<Object>() {
            public boolean isSatisfiedBy(Object obj) {
                return true;
            }
        }.isGeneralizationOf(null);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void remainderUnsatisfiedBy_notImplemented() {
        new SpecificationBaseImpl<Object>() {
            public boolean isSatisfiedBy(Object obj) {
                return true;
            }
        }.remainderUnsatisfiedBy(new Object());
    }
}
