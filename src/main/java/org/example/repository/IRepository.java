package org.example.repository;

import java.util.List;

/**
 *
 * @param <T> Type of Object
 * @param <U> Type of ID from that Object
 */
public interface IRepository<T, U> {
    /**
     * lee del archivo y carga en memoria
     */
    void readFromFile();
    /**
     * Lee en memoria y carga en archivo (Persistir los cambios en el archivo)
     */
    void saveToFile();

    void add(T obj);

    List<T> list();
    T getById(U id);
    void update(T obj);

    void delete(U id);

}
