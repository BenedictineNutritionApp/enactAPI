package enactApp.enactAPI.web.controller;

import enactApp.enactAPI.data.model.*;
import enactApp.enactAPI.data.repository.*;
import enactApp.enactAPI.data.translator.FoodTranslator;
import enactApp.enactAPI.web.models.FoodView;
import org.springframework.beans.factory.annotation.Autowired;
import enactApp.enactAPI.data.model.ActivityOption;
import enactApp.enactAPI.data.repository.ActivityOptionRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/food")
public class FoodController {


    @Autowired
    private FoodRepository foodRepository;


    /**
     * @return A list of all food objects in the database
     */
    @GetMapping(value = "/all/")
    public List<FoodView> getAllFood() {
        return FoodTranslator.entitiesToViews(foodRepository.findAll());
    }

    /**
     * @return A list of all food objects in the database that match
     */
    @GetMapping(value = "/{query}")
    public List<FoodView> searchFood(@PathVariable String query) {
        return FoodTranslator.entitiesToViews(foodRepository.findFoodByDescriptionContaining(query));
    }


}
