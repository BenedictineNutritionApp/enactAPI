CREATE TABLE IF NOT EXISTS `cancer_treatment`
(
    `id` INT NOT NULL,
    `radiation_therapy` TINYINT NOT NULL,
    `chemo_therapy` TINYINT NOT NULL,
    `years_since_last_treatment` INT NOT NULL,
    `created_on` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_on` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`))
    ENGINE = InnoDB;
