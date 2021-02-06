CREATE TABLE IF NOT EXISTS `cancer_diagnoses`
(
    `id` INT NOT NULL AUTO_INCREMENT,
    `cancer_location` VARCHAR(32) NOT NULL,
    `stage` INT NOT NULL,
    `years_since_last_diagnosis` INT NOT NULL,
    `created_on` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_on` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)
    ENGINE = InnoDB;
