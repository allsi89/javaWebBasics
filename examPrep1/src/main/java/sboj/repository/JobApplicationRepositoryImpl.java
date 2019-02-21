package sboj.repository;

import sboj.domain.entities.JobApplication;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class JobApplicationRepositoryImpl extends BaseRepository implements JobApplicationRepository {

    @Inject
    protected JobApplicationRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public void delete(String id) {

        //Update or Delete queries cannot be typed, aka do not include JobApplication.class
        /*
          entityManager.createQuery("UPDATE job_applications j SET j.deleted = true WHERE j.id = :id", JobApplication.class - wrong )
                    .setParameter("id", id)
                    .executeUpdate();

                    Manager.createQuery("DELETE FROM job_applications j WHERE j.id = :id")
                    .setParameter("id", id)
                    .executeUpdate();
                    "DELETE FROM job_applications j WHERE j.id = :id" for actual delete from the database

         */
        this.executeTransaction((entityManager) -> {
            entityManager.createQuery("UPDATE job_applications j SET j.deleted = true WHERE j.id = :id")
                    .setParameter("id", id)
                    .executeUpdate();
            return null;
        });
    }

    @Override
    public JobApplication save(JobApplication jobApplication) {
        return this.executeTransaction((entityManager) -> {
            entityManager.persist(jobApplication);
            return jobApplication;
        });
    }

    @Override
    public List<JobApplication> findAll() {
        return this.executeTransaction((entityManager) -> entityManager
                .createQuery("SELECT j FROM job_applications j WHERE j.deleted = false ", JobApplication.class)
                .getResultList());
    }

    @Override
    public JobApplication findById(String id) {
        return this.executeTransaction((entityManager) -> entityManager
                .createQuery("SELECT j FROM job_applications j WHERE j.id = :id", JobApplication.class)
                .setParameter("id", id)
                .getSingleResult());
    }
}
