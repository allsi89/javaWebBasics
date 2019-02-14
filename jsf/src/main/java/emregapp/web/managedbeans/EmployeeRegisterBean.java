package emregapp.web.managedbeans;

import org.modelmapper.ModelMapper;
import emregapp.domain.models.binding.EmRegBindingModel;
import emregapp.domain.models.service.EmployeeServiceModel;
import emregapp.service.EmployeeService;


import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;

@Named
@RequestScoped
public class EmployeeRegisterBean {
    private EmRegBindingModel emRegBindingModel;
    private EmployeeService employeeService;
    private ModelMapper modelMapper;

    public EmployeeRegisterBean() {
        this.emRegBindingModel = new EmRegBindingModel();
    }


    @Inject
    public EmployeeRegisterBean(EmployeeService employeeService, ModelMapper modelMapper) {
        this();
        this.employeeService = employeeService;
        this.modelMapper = modelMapper;
    }

    public EmRegBindingModel getEmRegBindingModel() {
        return emRegBindingModel;
    }

    public void setEmRegBindingModel(EmRegBindingModel emRegBindingModel) {
        this.emRegBindingModel = emRegBindingModel;
    }

    public void register () throws IOException {
        this.employeeService
                .saveEmployee(this.modelMapper
                .map(this.emRegBindingModel, EmployeeServiceModel.class));
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect("/");
    }
}

