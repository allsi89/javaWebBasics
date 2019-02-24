package exam.web.beans;

import exam.domain.models.service.DocumentServiceModel;
import exam.service.DocumentService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class DocumentPrintBean extends BaseBean {
    private DocumentService documentService;

    public DocumentPrintBean() {
    }

    @Inject
    public DocumentPrintBean(DocumentService documentService) {
        this.documentService = documentService;
    }

    public DocumentServiceModel getDocumentServiceModel(String id) {
        return this.documentService.findById(id);
    }

    public void print(String id) {
        if (!this.documentService.print(id)) {
            return;
        }
        this.redirect("/home");
    }
}
