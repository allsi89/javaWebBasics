package exam.service;

import exam.domain.models.service.DocumentServiceModel;

import java.util.List;

public interface DocumentService {
    DocumentServiceModel schedule(DocumentServiceModel documentServiceModel);

    public boolean print(String id);

    DocumentServiceModel findById(String id);

    List<DocumentServiceModel> getAllDocuments();
}
