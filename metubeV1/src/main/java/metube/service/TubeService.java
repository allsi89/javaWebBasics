package metube.service;

import metube.domain.models.models.TubeServiceModel;

import java.util.List;

public interface TubeService {
    void saveTube(TubeServiceModel tubeServiceModel);

    TubeServiceModel findTubeByTitle(String title);

    List<TubeServiceModel> findAllTubes();

}
