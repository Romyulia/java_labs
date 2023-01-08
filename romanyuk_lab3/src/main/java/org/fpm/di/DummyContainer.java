package org.fpm.di;

import javax.inject.Inject;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class DummyContainer implements Container {
    DummyBinder data;
    public DummyContainer(DummyBinder binder) {
        data = binder;
    }

    @Override
    public <T> T getComponent(Class<T> clazz) {
        if(data.getClass(clazz)!=null) {
            if (checkInject(clazz)!=null)
                return checkInject(clazz);
            try {
                return clazz.getConstructor().newInstance();
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                     NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }
        if(data.getImplementation(clazz)!=null)
            return (T) getComponent(data.getImplementation(clazz));
        if(data.getInstance(clazz)!=null)
            return data.getInstance(clazz);


        return null;
    }

    private<T> T checkInject(Class<T> clazz){
        for (Constructor<?> constructor: clazz.getConstructors()){
            if (constructor.isAnnotationPresent(Inject.class)){
                ArrayList<Object> constructorObjects = new ArrayList<>();
                for (Class<?> constructorClass: constructor.getParameterTypes()){
                    constructorObjects.add(getComponent(constructorClass));
                }
                try {
                    return (T) constructor.newInstance(constructorObjects.toArray());
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return null;
    }
}
