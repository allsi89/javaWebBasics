package exam.repository;

import exam.domain.entities.Document;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class DocumentRepositoryImpl extends BaseRepository implements DocumentRepository {

    @Inject
    protected DocumentRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Document save(Document entity) {
        return this.executeTransaction(
                entityManager -> {
                    entityManager.persist(entity);
                    return entity;
                });
    }

    @Override
    public Document update(Document entity) {
        return null;
    }

    @Override
    public List<Document> findAll() {
        return  this.executeTransaction(
                entityManager ->
                        entityManager
                                .createQuery("SELECT d FROM documents d", Document.class)
                                .getResultList()
        );
    }

    @Override
    public Document findById(String id) {
        return this.executeTransaction(
                entityManager ->
                        entityManager
                                .createQuery("SELECT d FROM documents d WHERE d.id = :id", Document.class)
                                .setParameter("id", id)
                                .getSingleResult()
        );
    }

    @Override
    public boolean delete(String id ) {
        return this.executeTransaction(
                entityManager -> {
                    entityManager
                            .createQuery("DELETE FROM documents d WHERE d.id = :id")
                            .setParameter("id", id)
                            .executeUpdate();
                    return true;
                });
    }
}
