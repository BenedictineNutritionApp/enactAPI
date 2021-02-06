package enactApp.enactAPI.data.model;


import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@SuperBuilder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "food")
public class Food extends AbstractEntity {

    @Column(name = "keylist")
    private String keylist;

    @Column(name = "description")
    private String description;

    @Column(name = "food_type")
    private String foodType;

    @Column(name = "ncc_food_group_category_id")
    private long nccFoodGroupCategoryId;

    @Column(name = "kcal")
    private double kcal;

    @Column(name = "protein_in_grams")
    private double proteinInGrams;

    @Column(name = "fat_in_grams")
    private double fatInGrams;

    @Column(name = "carbohydrates_in_grams")
    private double carbohydratesInGrams;

    @Column(name = "fiber_in_grams")
    private double fiberInGrams;

    @Column(name = "calcium_in_milligrams")
    private double calciumInMilligrams;

    @Column(name = "sodium_in_grams")
    private double sodiumInGrams;

    @Column(name = "saturated_fatty_acids_in_grams")
    private double saturatedFattyAcidsInGrams;

    @Column(name = "cholesterol_in_milligrams")
    private double cholesterolInMilligrams;

    @Column(name = "sugar_in_grams")
    private double sugarInGrams;

}

