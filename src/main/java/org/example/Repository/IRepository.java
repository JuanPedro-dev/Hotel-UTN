package org.example.Repository;

import java.util.List;

public interface IRepository<T> {
    void read();
    void save();
    List<T> list();
    void add(T... objeto);
    void delete(int id);
    void update(T objetoNuevo);
}
