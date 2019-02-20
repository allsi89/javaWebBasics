package fdmc.web.managedbeans;

import fdmc.domain.models.binding.CatCreateBindingModel;
import fdmc.domain.models.service.CatServiceModel;
import fdmc.service.CatService;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.Validator;
import java.io.IOException;

@Named
@RequestScoped
public class CatCreateBean {
    private CatCreateBindingModel catCreateBindingModel;
    private CatService catService;
    private ModelMapper modelMapper;
    private Validator validator;

    public CatCreateBean() {
        this.catCreateBindingModel = new CatCreateBindingModel();
    }

    @Inject
    public CatCreateBean(CatService catService, ModelMapper modelMapper, Validator validator) {
        this();
        this.catService = catService;
        this.modelMapper = modelMapper;
        this.validator = validator;
    }

    public void create() throws IOException {
        if (this.validator.validate(this.catCreateBindingModel).size() == 0){
            this.catService
                    .saveCat(this.modelMapper
                            .map(this.catCreateBindingModel, CatServiceModel.class));
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            context.redirect("/view/all-cats.xhtml");
        } else {
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            context.redirect("/");
        }
    }

    public CatCreateBindingModel getCatCreateBindingModel() {
        return catCreateBindingModel;
    }

    public void setCatCreateBindingModel(CatCreateBindingModel catCreateBindingModel) {
        this.catCreateBindingModel = catCreateBindingModel;
    }
}
