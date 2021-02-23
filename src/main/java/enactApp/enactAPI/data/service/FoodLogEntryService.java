package enactApp.enactAPI.data.service;

import enactApp.enactAPI.data.model.Food;
import enactApp.enactAPI.data.repository.FoodLogEntryRepository;
import enactApp.enactAPI.data.repository.FoodRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FoodLogEntryService {


    private static FoodRepository foodRepository;
    private final FoodLogEntryRepository foodLogEntryRepository;

    public FoodLogEntryService(FoodLogEntryRepository foodLogEntryRepository, FoodRepository foodRepository) {
        this.foodLogEntryRepository = foodLogEntryRepository;
        this.foodRepository = foodRepository;
    }


    public static Food getFood(Long id) {
        Optional<Food> optionalFood = foodRepository.findFoodById(id);
        if (optionalFood.isPresent()) {
            return optionalFood.get();
        }
        return null;
    }

}
