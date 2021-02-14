package enactApp.enactAPI;

import enactApp.enactAPI.data.model.Food;
import enactApp.enactAPI.data.model.NccFoodGroupCategory;
import enactApp.enactAPI.data.repository.FoodRepository;
import enactApp.enactAPI.data.repository.NccFoodGroupCategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Optional;
import java.util.Scanner;

@SpringBootApplication
public class EnactApiApplication {

    final private FoodRepository foodRepository;
    final private NccFoodGroupCategoryRepository nccFoodGroupCategoryRepository;

    public EnactApiApplication(FoodRepository foodRepository, NccFoodGroupCategoryRepository nccFoodGroupCategoryRepository) {
        this.foodRepository = foodRepository;
        this.nccFoodGroupCategoryRepository = nccFoodGroupCategoryRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(EnactApiApplication.class, args);
    }

    /**
     * @return the method to be executed
     */
    @Bean
    public CommandLineRunner demo() {
        return (args) -> {
            Scanner input = new Scanner(System.in);
            System.out.print("Would you like to import food nutrition data? (Y/N): ");
            String answer = input.next();
            if (answer.equalsIgnoreCase("Y")) {
                System.out.println("INSERTING DATA...");
                insertData();
            } else {
                System.out.println("SKIPPING DATA INSERTION...");
            }
            System.out.println("APP RUNNING");
            input.close();
        };
    }

    /**
     * This method parses a csv file and saves food information to the database.
     */
    public void insertData() {
        Scanner fileReader = null;
        try {
            fileReader = new Scanner(new File("src/main/resources/db.data_files/NDSR 2017 Nutrients Per 100 Gram.csv"));
        } catch (FileNotFoundException e) {
            System.out.println("FILE NOT FOUND. CHECK PATH/NAME");
            e.printStackTrace();
        }
        // Skip the header information
        fileReader.nextLine();
        // Parse each line of the csv
        while (fileReader.hasNextLine()) {
            String line = fileReader.nextLine();
            String[] foodInfo = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");

            String keylist = foodInfo[1];
            String description = foodInfo[3];
            String foodType = foodInfo[4];
            String nccFoodGroupCategory = foodInfo[6];
            double kcal;
            double proteinInGrams;
            double fatInGrams;
            double carbohydratesInGrams;
            double fiberInGrams;
            double calciumInMilligrams;
            double sodiumInMilligrams;
            double saturatedFattyAcidsInGrams;
            double cholesterolInMilligrams;

            // Check if any of the columns are empty, replace missing with zeros.
            if (foodInfo[7].isEmpty()) {
                kcal = 0;
            } else {
                kcal = Double.parseDouble(foodInfo[7]);
            }

            if (foodInfo[8].isEmpty()) {
                proteinInGrams = 0;
            } else {
                proteinInGrams = Double.parseDouble(foodInfo[8]);
            }

            if (foodInfo[9].isEmpty()) {
                fatInGrams = 0;
            } else {
                fatInGrams = Double.parseDouble(foodInfo[9]);
            }

            if (foodInfo[10].isEmpty()) {
                carbohydratesInGrams = 0;
            } else {
                carbohydratesInGrams = Double.parseDouble(foodInfo[10]);
            }

            if (foodInfo[11].isEmpty()) {
                fiberInGrams = 0;
            } else {
                fiberInGrams = Double.parseDouble(foodInfo[11]);
            }

            if (foodInfo[12].isEmpty()) {
                calciumInMilligrams = 0;
            } else {
                calciumInMilligrams = Double.parseDouble(foodInfo[12]);
            }

            if (foodInfo[13].isEmpty()) {
                sodiumInMilligrams = 0;
            } else {
                sodiumInMilligrams = Double.parseDouble(foodInfo[13]);
            }

            if (foodInfo[14].isEmpty()) {
                saturatedFattyAcidsInGrams = 0;
            } else {
                saturatedFattyAcidsInGrams = Double.parseDouble(foodInfo[14]);
            }

            if (foodInfo[15].isEmpty()) {
                cholesterolInMilligrams = 0;
            } else {
                cholesterolInMilligrams = Double.parseDouble(foodInfo[15]);
            }

            double sugarInGrams = 0;
            long nccFoodGroupCategoryId;
            if (foodInfo.length == 17) {
                sugarInGrams = Double.parseDouble(foodInfo[16]);
            }

            // Check if the category is already in the database
            // If it isn't, it is added to the database
            Optional<NccFoodGroupCategory> optionalNccFoodGroupCategory = nccFoodGroupCategoryRepository.findNccFoodGroupCategoryByCategory(nccFoodGroupCategory);
            if (optionalNccFoodGroupCategory.isEmpty()) {
                NccFoodGroupCategory newNccFoodGroupCategory = NccFoodGroupCategory.builder()
                        .category(nccFoodGroupCategory)
                        .created(new Date())
                        .updated(new Date())
                        .build();
                nccFoodGroupCategoryRepository.save(newNccFoodGroupCategory);
            }
            // Get the id for a given category
            optionalNccFoodGroupCategory = nccFoodGroupCategoryRepository.findNccFoodGroupCategoryByCategory(nccFoodGroupCategory);
            NccFoodGroupCategory storedNccFoodGroupCategory = optionalNccFoodGroupCategory.get();
            nccFoodGroupCategoryId = storedNccFoodGroupCategory.getId();

            // Save the new food item to the database with the appropriate category id
            Food newFood = Food.builder()
                    .keylist(keylist)
                    .description(description)
                    .foodType(foodType)
                    .nccFoodGroupCategoryId(nccFoodGroupCategoryId)
                    .kcal(kcal)
                    .proteinInGrams(proteinInGrams)
                    .fatInGrams(fatInGrams)
                    .carbohydratesInGrams(carbohydratesInGrams)
                    .fiberInGrams(fiberInGrams)
                    .calciumInMilligrams(calciumInMilligrams)
                    .sodiumInMilligrams(sodiumInMilligrams)
                    .saturatedFattyAcidsInGrams(saturatedFattyAcidsInGrams)
                    .cholesterolInMilligrams(cholesterolInMilligrams)
                    .sugarInGrams(sugarInGrams)
                    .created(new Date())
                    .updated(new Date())
                    .build();
            foodRepository.save(newFood);
        }
        System.out.println("DATA INSERTION COMPLETE");
    }

}
