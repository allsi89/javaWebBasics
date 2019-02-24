package exam.repository;

import java.util.List;

public interface GenericRepository <E, K> {
    E save(E entity);

    E update(E entity);

    List<E> findAll();

    E findById(K id);
}
