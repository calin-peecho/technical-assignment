package com.example.demo.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * This class represent composite primary key for Bier.class
 */
public class BierId implements Serializable {
    private long id;
    private long user;

    public BierId() {
    }

    public BierId(long id, long user) {
        this.id = id;
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BierId bierId = (BierId) o;
        return id == bierId.id &&
                user == bierId.user;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user);
    }
}
