CREATE TABLE IF NOT EXISTS `daily_food_log_has_food_log_entry`
(
    `daily_food_log_id` INT NOT NULL,
    `food_log_entry_id` INT NOT NULL,
    PRIMARY KEY (`daily_food_log_id`, `food_log_entry_id`),
    INDEX `fk_daily_food_log_has_food_log_entry_food_log_entry1_idx` (`food_log_entry_id` ASC) VISIBLE,
    INDEX `fk_daily_food_log_has_food_log_entry_daily_food_log1_idx` (`daily_food_log_id` ASC) VISIBLE,
    CONSTRAINT `fk_daily_food_log_has_food_log_entry_daily_food_log1`
        FOREIGN KEY (`daily_food_log_id`)
            REFERENCES `health_app`.`daily_food_log` (`id`),
    CONSTRAINT `fk_daily_food_log_has_food_log_entry_food_log_entry1`
        FOREIGN KEY (`food_log_entry_id`)
            REFERENCES `health_app`.`food_log_entry` (`id`))
    ENGINE = InnoDB;
