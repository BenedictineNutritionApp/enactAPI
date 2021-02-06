CREATE TABLE IF NOT EXISTS `cancer_treatment_has_surgery`
(
    `cancer_treatment_id` INT NOT NULL,
    `surgery_id`          INT NOT NULL,
    PRIMARY KEY (`cancer_treatment_id`, `surgery_id`),
    INDEX `fk_cancer_treatment_has_surgery_surgery1_idx` (`surgery_id` ASC) VISIBLE,
    INDEX `fk_cancer_treatment_has_surgery_cancer_treatment1_idx` (`cancer_treatment_id` ASC) VISIBLE,
    CONSTRAINT `fk_cancer_treatment_has_surgery_cancer_treatment1`
        FOREIGN KEY (`cancer_treatment_id`)
            REFERENCES `health_app`.`cancer_treatment` (`id`),
    CONSTRAINT `fk_cancer_treatment_has_surgery_surgery1`
        FOREIGN KEY (`surgery_id`)
            REFERENCES `health_app`.`surgery` (`id`))
    ENGINE = InnoDB;
