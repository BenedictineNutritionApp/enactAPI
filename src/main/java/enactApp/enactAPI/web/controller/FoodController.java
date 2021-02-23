package enactApp.enactAPI.web.controller;


import enactApp.enactAPI.data.model.Food;
import enactApp.enactAPI.data.repository.*;
import enactApp.enactAPI.data.service.FoodService;
import enactApp.enactAPI.data.translator.FoodTranslator;
import enactApp.enactAPI.web.models.FoodView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * This is the userController class
 */
@Slf4j
@RestController
public class FoodController {

    @Autowired
    private final FoodRepository foodRepository;
    private final FoodLogEntryRepository foodLogEntryRepository;
    private final CommonPortionSizeDescriptionRepository commonPortionSizeDescriptionRepository;
    private final CommonPortionSizeUnitRepository commonPortionSizeUnitRepository;

    public FoodController(FoodRepository foodRepository, FoodLogEntryRepository foodLogEntryRepository, CommonPortionSizeDescriptionRepository commonPortionSizeDescriptionRepository, CommonPortionSizeUnitRepository commonPortionSizeUnitRepository) {
        this.foodRepository = foodRepository;
        this.foodLogEntryRepository = foodLogEntryRepository;
        this.commonPortionSizeDescriptionRepository = commonPortionSizeDescriptionRepository;
        this.commonPortionSizeUnitRepository = commonPortionSizeUnitRepository;
    }


    /**
     * @return A list of all food objects in the database
     */
    @GetMapping(value = "/api/food/all/")
    public List<FoodView> getAllFood() {
        return FoodTranslator.entitiesToViews(foodRepository.findAll());
    }

    /**
     * @return A list of all food objects in the database that match
     */
    @GetMapping(value = "/api/food/{query}")
    public List<FoodView> searchFood(@PathVariable String query) {
        return FoodTranslator.entitiesToViews(foodRepository.findFoodByDescriptionContaining(query));
    }


}



