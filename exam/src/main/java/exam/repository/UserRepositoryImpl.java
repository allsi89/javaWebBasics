package exam.repository;

import exam.domain.entities.User;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class UserRepositoryImpl extends BaseRepository implements UserRepository {

    @Inject
    public UserRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public User save(User entity) {
        return this.executeTransaction(
                entityManager -> {
                    entityManager.persist(entity);
                    return entity;
                });
    }

    @Override
    public User update(User user) {
        return this.executeTransaction(entityManager ->
                entityManager.merge(user));
    }

    @Override
    public List<User> findAll() {
        return  this.executeTransaction(
                entityManager ->
                    entityManager
                            .createQuery("SELECT u FROM users u", User.class)
                            .getResultList()
                );
    }

    @Override
    public User findById(String id) {
        return this.executeTransaction(
                entityManager ->
                        entityManager
                                .createQuery("SELECT u FROM users u WHERE u.id = :id", User.class)
                                .setParameter("id", id)
                                .getSingleResult()
        );
    }

    @Override
    public User findByUsername(String username) {
        return this.executeTransaction(
                entityManager ->
                        entityManager
                                .createQuery("SELECT u FROM users u WHERE u.username = :username", User.class)
                                .setParameter("username", username)
                                .getSingleResult()
        );
    }
}
