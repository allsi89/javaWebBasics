package metube.service;

import metube.domain.entities.Tube;
import metube.domain.models.models.TubeServiceModel;
import metube.repository.TubeRepository;
import metube.util.ModelMapper;
import metube.util.ValidationUtil;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class TubeServiceImpl implements TubeService {
    private final TubeRepository tubeRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    @Inject
    public TubeServiceImpl(TubeRepository tubeRepository, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.tubeRepository = tubeRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public void saveTube(TubeServiceModel tubeServiceModel) {
        if (!this.validationUtil.isValid(tubeServiceModel)){
            throw new IllegalArgumentException();
        } else {
            this.tubeRepository.save(this.modelMapper.map(tubeServiceModel, Tube.class));
        }
    }

    @Override
    public TubeServiceModel findTubeByTitle(String title) {
        Tube tube = this.tubeRepository.findByTitle(title).orElse(null);
        return this.modelMapper.map(tube, TubeServiceModel.class);
    }

    @Override
    public List<TubeServiceModel> findAllTubes() {
        return this.tubeRepository.findAll().stream().map(t->
                this.modelMapper.map(t, TubeServiceModel.class))
                .collect(Collectors.toList());
    }
}
