package sboj.repository;

import javax.persistence.EntityManager;
import java.util.function.Function;

public abstract class BaseRepository {
    private final EntityManager entityManager;

    protected BaseRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    protected <T> T executeTransaction(Function<EntityManager, T> function){
        T result = null;
        try {
            this.entityManager.getTransaction().begin();
            result = function.apply(this.entityManager);
            this.entityManager.getTransaction().commit();
        } catch (Exception e) {
            this.entityManager.getTransaction().rollback();
            e.printStackTrace();
        }
        return result;
    }
}

