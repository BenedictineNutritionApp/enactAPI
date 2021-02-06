CREATE TABLE IF NOT EXISTS `user_has_daily_food_log`
(
    `user_id`           INT NOT NULL,
    `daily_food_log_id` INT NOT NULL,
    PRIMARY KEY (`user_id`, `daily_food_log_id`),
    INDEX `fk_user_has_daily_food_log_daily_food_log1_idx` (`daily_food_log_id` ASC) VISIBLE,
    INDEX `fk_user_has_daily_food_log_user1_idx` (`user_id` ASC) VISIBLE,
    CONSTRAINT `fk_user_has_daily_food_log_daily_food_log1`
        FOREIGN KEY (`daily_food_log_id`)
            REFERENCES `health_app`.`daily_food_log` (`id`),
    CONSTRAINT `fk_user_has_daily_food_log_user1`
        FOREIGN KEY (`user_id`)
            REFERENCES `health_app`.`user` (`id`))
    ENGINE = InnoDB;
