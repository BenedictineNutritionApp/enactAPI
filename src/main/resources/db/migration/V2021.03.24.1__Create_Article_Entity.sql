CREATE TABLE IF NOT EXISTS `article`
(
    `id` INT NOT NULL AUTO_INCREMENT,
    `article_name` VARCHAR(64),
    `data` BLOB,
    `created_on` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_on` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)
    ENGINE = InnoDB;
