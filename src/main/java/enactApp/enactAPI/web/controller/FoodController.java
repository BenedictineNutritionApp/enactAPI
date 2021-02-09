package enactApp.enactAPI.web.controller;


import enactApp.enactAPI.data.model.Food;
import enactApp.enactAPI.data.repository.FoodRepository;
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

    public FoodController(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }


    /**
     * @return A list of all food objects in the database
     */
    @GetMapping(value = "/api/food/all/")
    public List<Food> getAllFood() {
        return foodRepository.findAll();
    }

    /**
     * @return A list of all food objects in the database that match
     */
    @GetMapping(value = "/api/food/{description}")
    public List<Food> searchFood(@PathVariable String description) {
        return foodRepository.findFoodByDescription(description);
    }


}



