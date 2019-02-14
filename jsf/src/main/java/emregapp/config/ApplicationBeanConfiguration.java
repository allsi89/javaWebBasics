package emregapp.config;

import emregapp.service.EmployeeService;
import emregapp.service.EmployeeServiceImpl;
import org.modelmapper.ModelMapper;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class ApplicationBeanConfiguration {
    @Produces
    public EntityManager entityManager() {
        return Persistence
                .createEntityManagerFactory("emregPU")
                .createEntityManager();
    }

    @Produces
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }


}
