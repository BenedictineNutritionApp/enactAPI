package enactApp.enactAPI.data.service;

import enactApp.enactAPI.data.model.CommonPortionSizeDescription;
import enactApp.enactAPI.data.model.CommonPortionSizeUnit;
import enactApp.enactAPI.data.repository.CommonPortionSizeDescriptionRepository;
import enactApp.enactAPI.data.repository.CommonPortionSizeUnitRepository;
import enactApp.enactAPI.data.repository.FoodLogEntryRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FoodService {

    private static CommonPortionSizeDescriptionRepository commonPortionSizeDescriptionRepository;
    private static CommonPortionSizeUnitRepository commonPortionSizeUnitRepository;
    private final FoodLogEntryRepository foodLogEntryRepository;
//    private final CommonPortionSizeDescriptionRepository commonPortionSizeDescriptionRepository;
//    private final CommonPortionSizeUnitRepository commonPortionSizeUnitRepository;

    public FoodService(FoodLogEntryRepository foodLogEntryRepository, CommonPortionSizeDescriptionRepository commonPortionSizeDescriptionRepository, CommonPortionSizeUnitRepository commonPortionSizeUnitRepository) {
        this.foodLogEntryRepository = foodLogEntryRepository;
        this.commonPortionSizeDescriptionRepository = commonPortionSizeDescriptionRepository;
        this.commonPortionSizeUnitRepository = commonPortionSizeUnitRepository;
    }

    public static String getCommonPortionSizeDescription(Long commonPortionSizeDescriptionId) {
        Optional<CommonPortionSizeDescription> optionalCommonPortionSizeDescription = commonPortionSizeDescriptionRepository.findCommonPortionSizeDescriptionById(commonPortionSizeDescriptionId);
        if(optionalCommonPortionSizeDescription.isPresent()) {
            return optionalCommonPortionSizeDescription.get().getDescription();
        }
        return "";
    }

    public static String getCommonPortionSizeUnit(Long commonPortionSizeUnitId) {
        Optional<CommonPortionSizeUnit> optionalCommonPortionSizeUnit = commonPortionSizeUnitRepository.findCommonPortionSizeUnitById(commonPortionSizeUnitId);
        if(optionalCommonPortionSizeUnit.isPresent()) {
            return optionalCommonPortionSizeUnit.get().getUnit();
        }
        return "";
    }




}
