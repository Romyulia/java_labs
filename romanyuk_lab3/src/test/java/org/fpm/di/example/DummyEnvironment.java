package org.fpm.di.example;

import org.fpm.di.Environment;
import org.fpm.di.Container;
import org.fpm.di.Configuration;

public class DummyEnvironment implements Environment {

    @Override
    public Container configure(Configuration configuration) {
        DummyBinder binder = new DummyBinder();
        configuration.configure(binder);
        return new DummyContainer(binder);
    }
}
