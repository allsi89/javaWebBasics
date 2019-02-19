package emregapp.web.managedbeans;

import org.modelmapper.ModelMapper;
import emregapp.domain.models.view.EmployeeViewModel;
import emregapp.service.EmployeeService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class EmployeeViewBean {
    private List<EmployeeViewModel> viewModels;
    private EmployeeService employeeService;
    private ModelMapper modelMapper;

    public EmployeeViewBean() {
    }

    @Inject
    public EmployeeViewBean(EmployeeService employeeService, ModelMapper modelMapper) {
        this.employeeService = employeeService;
        this.modelMapper = modelMapper;
        this.viewModels = this.employeeService
                .getAllEmployees()
                .stream()
                .map(employeeServiceModel ->
                        this.modelMapper.map(employeeServiceModel, EmployeeViewModel.class))
                .collect(Collectors.toList());
    }

    public List<EmployeeViewModel> getViewModels() {
        return viewModels;
    }

    public void setViewModels(List<EmployeeViewModel> viewModels) {
        this.viewModels = viewModels;
    }


    public BigDecimal getTotalMoneyNeeded() {
        BigDecimal sum = BigDecimal.ZERO;
        for (EmployeeViewModel viewModel : viewModels) {
            sum = sum.add(viewModel.getSalary());
        }
        return sum;
    }


    public BigDecimal getAverageMoneyNeeded() {
        if(this.getTotalMoneyNeeded().equals(BigDecimal.ZERO)){
            return BigDecimal.ZERO;
        } else {
            return this.getTotalMoneyNeeded()
                    .divide(BigDecimal.valueOf(this.viewModels.size()), RoundingMode.valueOf(2));
        }
    }
}
