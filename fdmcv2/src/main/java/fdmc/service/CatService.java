package fdmc.service;

import fdmc.domain.models.service.CatServiceModel;

import java.util.List;

public interface CatService {

    boolean saveCat(CatServiceModel catServiceModel);

    List<CatServiceModel> getAllCats();

    void removeById(String id);


}
