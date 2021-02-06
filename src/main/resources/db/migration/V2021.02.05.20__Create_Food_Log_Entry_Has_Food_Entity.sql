CREATE TABLE IF NOT EXISTS `food_log_entry_has_food`
(
    `food_log_entry_id` INT NOT NULL,
    `food_id`           INT NOT NULL,
    `portion`           INT NOT NULL,
    PRIMARY KEY (`food_log_entry_id`, `food_id`),
    INDEX `fk_food_log_entry_has_food_food1_idx` (`food_id` ASC) VISIBLE,
    INDEX `fk_food_log_entry_has_food_food_log_entry1_idx` (`food_log_entry_id` ASC) VISIBLE,
    CONSTRAINT `fk_food_log_entry_has_food_food1`
        FOREIGN KEY (`food_id`)
            REFERENCES `health_app`.`food` (`id`),
    CONSTRAINT `fk_food_log_entry_has_food_food_log_entry1`
        FOREIGN KEY (`food_log_entry_id`)
            REFERENCES `health_app`.`food_log_entry` (`id`))
    ENGINE = InnoDB;
