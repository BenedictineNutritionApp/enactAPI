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

    @Column(name = "base_id")
    private int baseId;

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

    @Column(name = "soluble_fiber_in_grams")
    private double solubleFiberInGrams;

    @Column(name = "insoluble_fiber_in_grams")
    private double insolubleFiberInGrams;

    @Column(name = "calcium_in_milligrams")
    private double calciumInMilligrams;

    @Column(name = "sodium_in_milligrams")
    private double sodiumInMilligrams;

    @Column(name = "saturated_fatty_acids_in_grams")
    private double saturatedFattyAcidsInGrams;

    @Column(name = "polyunsaturated_fatty_acids_in_grams")
    private double polyunsaturatedFattyAcidsInGrams;

    @Column(name = "monounsaturated_fatty_acids_in_grams")
    private double monounsaturatedFattyAcidsInGrams;

    @Column(name = "cholesterol_in_milligrams")
    private double cholesterolInMilligrams;

    @Column(name = "sugar_in_grams")
    private double sugarInGrams;

    @Column(name = "added_sugars_in_grams")
    private double addedSugarsInGrams;

    @Column(name = "alcohol_in_grams")
    private double alcoholInGrams;

    @Column(name = "caffeine_in_milligrams")
    private double caffeineInMilligrams;

    @Column(name = "iron_in_milligrams")
    private double ironInMilligrams;

    @Column(name = "potassium_in_milligrams")
    private double potassiumInMilligrams;

    @Column(name = "phosphorus_in_milligrams")
    private double phosphorusInMilligrams;

    @Column(name = "thiamin_in_milligrams")
    private double thiaminInMilligrams;

    @Column(name = "riboflavin_in_milligrams")
    private double riboflavinInMilligrams;

    @Column(name = "niacin_in_milligrams")
    private double niacinInMilligrams;

    @Column(name = "pantothenic_acid_in_milligrams")
    private double pantothenicAcidInMilligrams;

    @Column(name = "vitamin_b6_in_milligrams")
    private double vitaminB6InMilligrams;

    @Column(name = "vitamin_b12_in_micrograms")
    private double vitaminB12InMicrograms;

    @Column(name = "vitamin_c_in_milligrams")
    private double vitaminCInMilligrams;

    @Column(name = "folate_in_micrograms")
    private double folateInMicrograms;

    @Column(name = "vitamin_a_in_international_units")
    private double vitaminAInInternationalUnits;

    @Column(name = "beta_carotene_in_micrograms")
    private double betaCaroteneInMicrograms;

    @Column(name = "lycopene_in_micrograms")
    private double lycopeneInMicrograms;

    @Column(name = "vitamin_d_in_micrograms")
    private double vitaminDInMicrograms;

    @Column(name = "vitamin_e_in_international_units")
    private double vitaminEInInternationalUnits;

}

