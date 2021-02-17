package enactApp.enactAPI.data.service;

import enactApp.enactAPI.data.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserService {

//    @Autowired
//    private UserRepository userRepository;
//    @Autowired
//    private UserHasDailyFoodLogRepository userHasDailyFoodLogRepository;
//    @Autowired
//    private DailyFoodLogRepository dailyFoodLogRepository;
//    @Autowired
//    private DailyFoodLogHasFoodLogEntryRepository dailyFoodLogHasFoodLogEntryRepository;
//    @Autowired
//    private FoodLogEntryRepository foodLogEntryRepository;
//    @Autowired
//    private FoodLogEntryHasFoodRepository foodLogEntryHasFoodRepository;
//    @Autowired
//    private FoodRepository foodRepository;
//    @Autowired
//    private NccFoodGroupCategoryRepository nccFoodGroupCategoryRepository;
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
