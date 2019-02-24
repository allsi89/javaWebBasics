package exam.service;

import exam.domain.entities.Document;
import exam.domain.models.service.DocumentServiceModel;
import exam.repository.DocumentRepository;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class DocumentServiceImpl implements DocumentService {
    private final DocumentRepository documentRepository;
    private final ModelMapper modelMapper;

    @Inject
    public DocumentServiceImpl(DocumentRepository documentRepository, ModelMapper modelMapper) {
        this.documentRepository = documentRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public DocumentServiceModel schedule(DocumentServiceModel documentServiceModel) {
        return this.modelMapper.map(
                this.documentRepository
                        .save(this.modelMapper.map(documentServiceModel, Document.class)), DocumentServiceModel.class);
    }

    @Override
    public boolean print(String id) {
        return this.documentRepository.delete(id);

    }

    @Override
    public DocumentServiceModel findById(String id) {
        return this.modelMapper.map(this.documentRepository.findById(id), DocumentServiceModel.class);
    }

    @Override
    public List<DocumentServiceModel> getAllDocuments() {
        return this.documentRepository.findAll()
                .stream()
                .map(d-> this.modelMapper.map(d, DocumentServiceModel.class))
                .collect(Collectors.toList());
    }
}
