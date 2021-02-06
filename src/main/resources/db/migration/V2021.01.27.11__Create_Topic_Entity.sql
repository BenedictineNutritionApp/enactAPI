CREATE TABLE IF NOT EXISTS `topic`
(
    `topic_id` INT NOT NULL AUTO_INCREMENT,
    `topic_name` VARCHAR(64) NOT NULL,
    `created_on` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_on` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`topic_id`),
    UNIQUE INDEX `idtopic_UNIQUE` (`topic_id` ASC) VISIBLE,
    UNIQUE INDEX `topic_name_UNIQUE` (`topic_name` ASC) VISIBLE)
    ENGINE = InnoDB;
