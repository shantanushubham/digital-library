package org.geeksforgeeks.digitallibrary.commons;

import java.util.List;

public interface CommonAdapter<E, M> {

    M save(E e);

    M getById(long id);

    List<M> getAll();

    M update(E e);

    void delete(long id);
}
