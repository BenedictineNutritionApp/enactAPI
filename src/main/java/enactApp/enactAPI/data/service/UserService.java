package enactApp.enactAPI.data.service;

import enactApp.enactAPI.data.repository.*;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final FoodLogEntryRepository foodLogEntryRepository;

    public UserService(FoodLogEntryRepository foodLogEntryRepository) {
        this.foodLogEntryRepository = foodLogEntryRepository;
    }
//
//    public String saveFoodLogEntry(Long userId, Long foodId, int portion,Date date, String mealType, String mealTime ) {
//        DailyFoodLog dailyFoodLog = new DailyFoodLog();
//        Date newDate = new Date();
//        dailyFoodLog.setCreated(newDate);
//                dailyFoodLog.setDate(date);
//        dailyFoodLogRepository.save(dailyFoodLog);
//
//        return "success";
//    }



}
