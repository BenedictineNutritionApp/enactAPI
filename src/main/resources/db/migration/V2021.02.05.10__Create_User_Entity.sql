CREATE TABLE IF NOT EXISTS `user`
(
    `id`                  INT          NOT NULL AUTO_INCREMENT,
    `email`               VARCHAR(128) NOT NULL,
    `username`            VARCHAR(64)  ,
    `password`            VARCHAR(64)  NOT NULL,
    `first_name`          VARCHAR(64)  ,
    `last_name`           VARCHAR(64)  ,
    `avatar_id`           INT          ,
    `is_admin`            TINYINT      ,
    `created_on`          DATETIME      DEFAULT CURRENT_TIMESTAMP,
    `updated_on`          DATETIME      DEFAULT CURRENT_TIMESTAMP,
    `age`                 INT          ,
    `height`              INT          ,
    `weight`              INT          ,
    `activity_level_id`   INT          ,
    `cancer_treatment_id` INT          ,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `iduser_UNIQUE` (`id` ASC) VISIBLE,
    UNIQUE INDEX `user_email_UNIQUE` (`email` ASC) VISIBLE,
    UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE,
    INDEX `fk_user_activity_level1_idx` (`activity_level_id` ASC) VISIBLE,
    INDEX `fk_user_cancer_treatment1_idx` (`cancer_treatment_id` ASC) VISIBLE,
    CONSTRAINT `fk_user_activity_level1`
        FOREIGN KEY (`activity_level_id`)
            REFERENCES `health_app`.`activity_level` (`id`),
    CONSTRAINT `fk_user_cancer_treatment1`
        FOREIGN KEY (`cancer_treatment_id`)
            REFERENCES `health_app`.`cancer_treatment` (`id`))
    ENGINE = InnoDB;
