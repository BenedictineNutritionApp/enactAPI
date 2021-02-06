package enactApp.enactAPI.data.repository;

import enactApp.enactAPI.data.model.NccFoodGroupCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository for NCC Food Group Categories
 */
public interface NccFoodGroupCategoryRepository extends JpaRepository<NccFoodGroupCategory, Long> {

    Optional<NccFoodGroupCategory> findNccFoodGroupCategoryByCategory(String category);



}
