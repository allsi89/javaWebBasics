package exam.web.beans;

import exam.domain.models.view.DocumentViewModel;
import exam.service.DocumentService;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class HomeBean extends BaseBean {
    private List<DocumentViewModel> documentViewModels;
    private DocumentService documentService;
    private ModelMapper modelMapper;

    public HomeBean() {
    }

    @Inject
    public HomeBean(DocumentService documentService, ModelMapper modelMapper) {
        this.documentService = documentService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void init() {
        this.setDocumentViewModels(this.documentService.getAllDocuments()
                .stream()
                .map(d->this.modelMapper.map(d, DocumentViewModel.class))
                .collect(Collectors.toList()));
    }

    public List<DocumentViewModel> getDocumentViewModels() {
        return documentViewModels;
    }

    public void setDocumentViewModels(List<DocumentViewModel> documentViewModels) {
        this.documentViewModels = documentViewModels;
    }
}
