package org.geeksforgeeks.digitallibrary.commons;

public interface CommonAdapter<E, M> {

    M save(E e);

    M update(E e);
}
