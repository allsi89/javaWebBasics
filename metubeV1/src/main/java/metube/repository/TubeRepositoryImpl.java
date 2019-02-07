package metube.repository;

import metube.domain.entities.Tube;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Optional;

public class TubeRepositoryImpl implements TubeRepository {
    private EntityManager entityManager;

    public TubeRepositoryImpl() {
        this.entityManager = Persistence
                .createEntityManagerFactory("metube")
                .createEntityManager();
    }

    @Override
    public Optional<Tube> findByTitle(String title) {
        try{

            return Optional.of(this.entityManager
                    .createQuery("SELECT t FROM tubes t WHERE t.title = :title", Tube.class)
                    .setParameter("title", title)
                    .getSingleResult());
        } catch (NoResultException e){
            return Optional.empty();
        }

    }

    @Override
    public Tube save(Tube tube) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(tube);
        this.entityManager.getTransaction().commit();
        return tube;
    }

    @Override
    public List<Tube> findAll() {
        return this.entityManager
                .createQuery("SELECT t FROM tubes t", Tube.class)
                .getResultList();
    }

    @Override
    public Optional<Tube> findById(String id) {
        try{
            return Optional.of(this.entityManager
                    .createQuery("SELECT t FROM tubes t WHERE t.id = :id", Tube.class)
                    .setParameter("id", id)
                    .getSingleResult());
        } catch (NoResultException e){
            return Optional.empty();
        }

    }
}
