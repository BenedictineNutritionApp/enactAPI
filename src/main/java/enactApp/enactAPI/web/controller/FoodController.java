package enactApp.enactAPI.web.controller;


import enactApp.enactAPI.data.model.Food;
import enactApp.enactAPI.data.repository.FoodRepository;
import enactApp.enactAPI.data.repository.FoodRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


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

    public ArrayList<Food> getAllFood() {
        ArrayList<Food> food = new ArrayList<>();

        return food;
    }


    @GetMapping(value = "/message")
    public String getMessage() {
        return "HTTPS Working";
    }


}



