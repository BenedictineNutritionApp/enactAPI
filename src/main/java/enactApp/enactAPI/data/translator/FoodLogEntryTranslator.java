package enactApp.enactAPI.data.translator;


import enactApp.enactAPI.data.model.FoodLogEntry;
import enactApp.enactAPI.data.service.FoodLogEntryService;
import enactApp.enactAPI.web.models.FoodLogEntryView;

import java.util.ArrayList;
import java.util.List;

public class FoodLogEntryTranslator {
    public static FoodLogEntryView entityToView(final FoodLogEntry foodLogEntry) {
        return FoodLogEntryView.builder()
                .id(foodLogEntry.getId())
                .date(foodLogEntry.getDate())
                .entryTime(foodLogEntry.getEntryTime())
                .portion(foodLogEntry.getPortion())
                .food(FoodLogEntryService.getFood(foodLogEntry.getFoodId()))
                .build();
    }

    public static List<FoodLogEntryView> entitiesToViews(final List<FoodLogEntry> foodLogEntryList) {
        ArrayList<FoodLogEntryView> foodLogEntryViews = new ArrayList<>();
        for (FoodLogEntry foodLogEntry : foodLogEntryList) {
            foodLogEntryViews.add(
                    FoodLogEntryView.builder()
                            .id(foodLogEntry.getId())
                            .date(foodLogEntry.getDate())
                            .entryTime(foodLogEntry.getEntryTime())
                            .portion(foodLogEntry.getPortion())
                            .food(FoodLogEntryService.getFood(foodLogEntry.getFoodId()))
                            .build()
            );
        }
        return foodLogEntryViews;
    }


}
