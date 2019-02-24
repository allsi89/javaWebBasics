package exam.web.beans;

import exam.domain.models.binding.DocumentScheduleBindingModel;
import exam.domain.models.service.DocumentServiceModel;
import exam.service.DocumentService;
import exam.service.UserService;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

@Named
@RequestScoped
public class DocumentScheduleBean extends BaseBean {
    private DocumentScheduleBindingModel documentScheduleBindingModel;
    private DocumentService documentService;
    private ModelMapper modelMapper;

    public DocumentScheduleBean() {
    }

    @Inject
    public DocumentScheduleBean(DocumentService documentService, ModelMapper modelMapper) {
        this.documentService = documentService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void init() {
        this.documentScheduleBindingModel = new DocumentScheduleBindingModel();
    }

    public DocumentScheduleBindingModel getDocumentScheduleBindingModel() {
        return documentScheduleBindingModel;
    }

    public void setDocumentScheduleBindingModel(DocumentScheduleBindingModel documentScheduleBindingModel) {
        this.documentScheduleBindingModel = documentScheduleBindingModel;

    }

    public void schedule(){
        DocumentServiceModel documentServiceModel = this.documentService.schedule(
                this.modelMapper.map(
                        this.documentScheduleBindingModel, DocumentServiceModel.class
                ));

        if (documentServiceModel == null) {
            throw new IllegalArgumentException("Something went wrong!");
        }
        this.redirect("/details" + documentServiceModel.getId());
    }
}
