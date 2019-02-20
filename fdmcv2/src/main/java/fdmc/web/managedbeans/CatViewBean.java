package fdmc.web.managedbeans;

import fdmc.domain.models.view.CatViewModel;
import fdmc.service.CatService;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class CatViewBean {
    private List<CatViewModel> viewModels;
    private CatService catService;
    private ModelMapper modelMapper;

    public CatViewBean() {
    }

    @Inject
    public CatViewBean(CatService catService, ModelMapper modelMapper) {
        this.catService = catService;
        this.modelMapper = modelMapper;
        this.viewModels = this.catService
                .getAllCats()
                .stream()
                .map(catServiceModel ->
                        this.modelMapper.map(catServiceModel, CatViewModel.class))
                .collect(Collectors.toList());
    }

    public List<CatViewModel> getViewModels() {
        return viewModels;
    }

    public void setViewModels(List<CatViewModel> viewModels) {
        this.viewModels = viewModels;
    }
}
