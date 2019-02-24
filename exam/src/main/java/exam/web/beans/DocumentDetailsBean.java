package exam.web.beans;

import exam.domain.models.service.DocumentServiceModel;
import exam.service.DocumentService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;


@Named
@RequestScoped
public class DocumentDetailsBean{
    private DocumentService documentService;

    public DocumentDetailsBean() {
    }

    @Inject
    public DocumentDetailsBean(DocumentService documentService) {
        this.documentService = documentService;
    }

    public DocumentServiceModel getDocumentServiceModel(String id) {
        return this.documentService.findById(id);
    }
}
