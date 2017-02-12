package cat.dme.smart.marcopolo.dao;

import java.util.List;

/**
 * Created by dmolina on 12/02/17.
 */

public interface BaseDao<T> {
    List<T> getAll();
    T get(Long id);
    Long save(T trip);
    void update(T trip);
    void delete(Long id);
}
