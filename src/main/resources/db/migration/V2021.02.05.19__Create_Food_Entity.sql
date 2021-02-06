CREATE TABLE IF NOT EXISTS `food`
(
    `id` INT NOT NULL AUTO_INCREMENT,
    `keylist` VARCHAR(64) NOT NULL,
    `description` VARCHAR(256) NOT NULL,
    `food_type` VARCHAR(2) NOT NULL,
    `ncc_food_group_category_id` INT NOT NULL,
    `kcal` DECIMAL(10,4) NOT NULL,
    `protein_in_grams` DECIMAL(10,4) NOT NULL,
    `fat_in_grams` DECIMAL(10,4) NOT NULL,
    `carbohydrates_in_grams` DECIMAL(10,4) NOT NULL,
    `fiber_in_grams` DECIMAL(10,4) NOT NULL,
    `calcium_in_milligrams` DECIMAL(10,4) NOT NULL,
    `sodium_in_grams` DECIMAL(10,4) NOT NULL,
    `saturated_fatty_acids_in_grams` DECIMAL(10,4) NOT NULL,
    `cholesterol_in_milligrams` DECIMAL(10,4) NOT NULL,
    `sugar_in_grams` DECIMAL(10,4) NOT NULL,
    `created_on` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_on` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
    INDEX `fk_food_ncc_food_group_category1_idx` (`ncc_food_group_category_id` ASC) VISIBLE,
    CONSTRAINT `fk_food_ncc_food_group_category1`
        FOREIGN KEY (`ncc_food_group_category_id`)
            REFERENCES `health_app`.`ncc_food_group_category` (`id`))
    ENGINE = InnoDB;
