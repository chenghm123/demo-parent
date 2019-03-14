package com.accelerator.demo.standalone.other.aggregate;

import org.jctools.maps.NonBlockingHashMap;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.util.Iterator;
import java.util.Map;

public class AggregateSlot<K, A extends AggregateSupport<A>> implements AggregateSupport<AggregateSlot<K, A>>, Serializable {

    private AggregateSupport newInstance(Class<? extends AggregateSupport> clazz) {
        try {
            Constructor<? extends AggregateSupport> ctor = clazz.getDeclaredConstructor();
            ctor.setAccessible(true);
            return ctor.newInstance();
        } catch (Exception e) {
            throw new RuntimeException("can not new " + clazz.getName()
                    + " from default constructor", e);
        }
    }

    private Map<K, A> map;

    public AggregateSlot() {
        this.map = new NonBlockingHashMap();
    }

    public AggregateSlot(int initialCapacity) {
        this.map = new NonBlockingHashMap(initialCapacity);
    }

    public A addToSlot(K key, A value) {
        A a = this.map.get(key);
        if (a == null) {
            a = (A) newInstance(value.getClass());
            this.map.put(key, a);
        }
        a.aggregateFrom(value);
        return a;
    }

    public void aggregateFrom(AggregateSlot<K, A> other) {
        if (other != null && !other.isEmpty()) {
            Iterator var2 = other.map.entrySet().iterator();
            while (var2.hasNext()) {
                Map.Entry<K, A> entry = (Map.Entry) var2.next();
                this.addToSlot(entry.getKey(), (A) entry.getValue());
            }
        }
    }

    public int size() {
        return this.map.size();
    }

    public boolean isEmpty() {
        return this.map.isEmpty();
    }

    public Map<K, A> toMap() {
        return this.map;
    }

    public String toString() {
        return this.map.toString();
    }


}
