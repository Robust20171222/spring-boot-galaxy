package com.galaxy.concurrency.collection;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangpeng
 * Date: 2018/10/24
 * Time: 21:25
 */
public class MapTest {

    public static void main(String[] args) {
        SafeKey emp = new SafeKey(2);
        emp.setName("Robin");

        // Put object in HashMap.
        Map<SafeKey, String> map = new HashMap<>();
        map.put(emp, "Showbasky");

        System.out.println(map.get(emp));

        // Change Employee name. Change in 'name' has no effect
        // on hash code.
        emp.setName("Lily");
        System.out.println(map.get(emp));
    }
}

class MutableKey {

    private int i;
    private int j;

    public MutableKey(int i, int j) {
        this.i = i;
        this.j = j;
    }

    public final int getI() {
        return i;
    }

    public final void setI(int i) {
        this.i = i;
    }

    public final int getJ() {
        return j;
    }

    public final void setJ(int j) {
        this.j = j;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + i;
        result = prime * result + j;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof MutableKey)) {
            return false;
        }
        MutableKey other = (MutableKey) obj;
        if (i != other.i) {
            return false;
        }
        if (j != other.j) {
            return false;
        }
        return true;
    }
}

class SafeKey {
    // It is specified while object creation.
    // Cannot be changed once object is created. No setter for this field.
    private int id;
    private String name;

    public SafeKey(final int id) {
        this.id = id;
    }

    public final String getName() {
        return name;
    }

    public final void setName(final String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    // Hash code depends only on 'id' which cannot be
    // changed once object is created. So hash code will not change
    // on object's state change
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        SafeKey other = (SafeKey) obj;
        if (id != other.id)
            return false;
        return true;
    }
}