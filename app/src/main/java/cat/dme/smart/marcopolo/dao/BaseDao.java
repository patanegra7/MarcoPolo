package cat.dme.smart.marcopolo.dao;

import java.util.List;

/**
 * Generic DAO interface for CRUD support.
 *
 * Created by VIddA Software - DME Creaciones.
 */
public interface BaseDao<T> {
    List<T> getAll();
    T get(Long id);
    Long save(T entity);
    void update(T entity);
    void delete(Long id);
}
