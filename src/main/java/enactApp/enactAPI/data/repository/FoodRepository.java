package enactApp.enactAPI.data.repository;

import enactApp.enactAPI.data.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Repository for Food
 */
public interface FoodRepository extends JpaRepository<Food, Long> {

    Optional<Food> findFoodByKeylist(String keylist);
    List<Food> findFoodByDescription(String description);
    List<Food> findFoodByDescriptionContaining(String description);
    List<Food> findAll();



}
