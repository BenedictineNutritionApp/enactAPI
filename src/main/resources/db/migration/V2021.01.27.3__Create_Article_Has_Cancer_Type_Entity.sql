CREATE TABLE IF NOT EXISTS `article_has_cancer_type`
(
    `article_id` INT NOT NULL,
    `cancer_type_id` INT NOT NULL,
    PRIMARY KEY (`article_id`, `cancer_type_id`),
    INDEX `fk_article_has_cancer_type_cancer_type1_idx` (`cancer_type_id` ASC) VISIBLE,
    INDEX `fk_article_has_cancer_type_article1_idx` (`article_id` ASC) VISIBLE,
    CONSTRAINT `fk_article_has_cancer_type_article1`
        FOREIGN KEY (`article_id`)
            REFERENCES `health_app`.`article` (`id`),
    CONSTRAINT `fk_article_has_cancer_type_cancer_type1`
        FOREIGN KEY (`cancer_type_id`)
            REFERENCES `health_app`.`cancer_type` (`id`))
    ENGINE = InnoDB;
