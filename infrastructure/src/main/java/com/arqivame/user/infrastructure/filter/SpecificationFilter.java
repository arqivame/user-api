package com.arqivame.user.infrastructure.filter;

import org.springframework.data.jpa.domain.Specification;

import com.arqivame.user.domain.pagination.Filter;
import com.arqivame.user.infrastructure.converter.Caster;

public abstract class SpecificationFilter {

    abstract Filter.Type filterType();

    abstract <T> Specification<T> buildSpecification(Filter filter);

    protected static <T> T cast(final Object value, final Class<T> clazz) {
        return Caster.cast(value, clazz);
    }

}
