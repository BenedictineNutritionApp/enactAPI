CREATE TABLE IF NOT EXISTS `article`
(
    `article_id` INT NOT NULL AUTO_INCREMENT,
    `article_content` TEXT NOT NULL,
    `created_on` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_on` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`article_id`),
    UNIQUE INDEX `article_id_UNIQUE` (`article_id` ASC) VISIBLE)
    ENGINE = InnoDB;
