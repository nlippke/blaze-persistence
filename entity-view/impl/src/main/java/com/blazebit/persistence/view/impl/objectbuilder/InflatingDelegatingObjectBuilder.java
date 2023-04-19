package com.blazebit.persistence.view.impl.objectbuilder;

import com.blazebit.persistence.ObjectBuilder;

public class InflatingDelegatingObjectBuilder<T> extends DelegatingObjectBuilder<T> {

    private final ViewTypeObjectBuilder<T> origin;

    public InflatingDelegatingObjectBuilder(ObjectBuilder<T> delegate, ViewTypeObjectBuilder<T> origin) {
        super(delegate);
        this.origin = origin;
    }

    @Override public T build(Object[] tuple) {
        Object[] result = origin.inflate(tuple);
        return super.build(result);
    }

}
