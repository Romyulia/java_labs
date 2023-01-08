package org.fpm.di;

import org.fpm.di.Binder;

import javax.inject.Singleton;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class DummyBinder implements Binder {
    private ArrayList<Class<?>> clazzList = new ArrayList<>();
    private HashMap<Class<?>, Class<?>> implementationMap = new HashMap<>();
    private HashMap<Class<?>, Object> instanceMap = new HashMap<>();
    @Override
    public <T> void bind(Class<T> clazz) {
       if(!clazz.isAnnotationPresent(Singleton.class)) {
           clazzList.add(clazz);
           return;
       }
       try {
            bind(clazz, clazz.getConstructor().newInstance());
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> void bind(Class<T> clazz, Class<? extends T> implementation) {
        implementationMap.put(clazz,implementation);
    }

    @Override
    public <T> void bind(Class<T> clazz, T instance) {
        instanceMap.put(clazz, instance);
    }

    public <T> Class<?> getClass(Class<T> clazz){
        if(clazzList.contains(clazz)) return clazz;
        return null;
    }

    public <T> Class<?> getImplementation(Class<T> clazz){
        if(implementationMap.containsKey(clazz)) return implementationMap.get(clazz);
        return null;
    }

    public <T> T getInstance(Class<T> clazz) {
        if (instanceMap.containsKey(clazz)) return (T) instanceMap.get(clazz);
        return null;
    }
}
