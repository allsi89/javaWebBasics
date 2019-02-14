package emregapp.service;

import emregapp.domain.entities.Employee;
import emregapp.domain.models.service.EmployeeServiceModel;
import emregapp.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeServiceImpl implements EmployeeService{
    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    @Inject
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean saveEmployee(EmployeeServiceModel employeeServiceModel) {
        try{
            this.employeeRepository.save(this.modelMapper.map(employeeServiceModel, Employee.class));
        } catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public List<EmployeeServiceModel> getAllEmployees() {
        return this.employeeRepository
                .findAll().stream()
                .map(employee -> this.modelMapper.map(employee, EmployeeServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void removeEmployee(String id) {
        this.employeeRepository.removeById(id);
    }
}
