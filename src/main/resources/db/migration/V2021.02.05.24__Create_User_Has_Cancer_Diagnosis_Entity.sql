CREATE TABLE IF NOT EXISTS `user_has_cancer_diagnoses`
(
    `user_id`             INT NOT NULL,
    `cancer_diagnoses_id` INT NOT NULL,
    PRIMARY KEY (`user_id`, `cancer_diagnoses_id`),
    INDEX `fk_user_has_cancer_diagnoses_cancer_diagnoses1_idx` (`cancer_diagnoses_id` ASC) VISIBLE,
    INDEX `fk_user_has_cancer_diagnoses_user1_idx` (`user_id` ASC) VISIBLE,
    CONSTRAINT `fk_user_has_cancer_diagnoses_cancer_diagnoses1`
        FOREIGN KEY (`cancer_diagnoses_id`)
            REFERENCES `health_app`.`cancer_diagnoses` (`id`),
    CONSTRAINT `fk_user_has_cancer_diagnoses_user1`
        FOREIGN KEY (`user_id`)
            REFERENCES `health_app`.`user` (`id`))
    ENGINE = InnoDB;
