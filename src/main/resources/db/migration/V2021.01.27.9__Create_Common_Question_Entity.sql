CREATE TABLE IF NOT EXISTS `common_question`
(
    `common_question_id` INT NOT NULL AUTO_INCREMENT,
    `common_question_question` VARCHAR(256) NOT NULL,
    `common_question_answer` VARCHAR(512) NOT NULL,
    `created_on` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_on` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`common_question_id`),
    UNIQUE INDEX `common_question_id_UNIQUE` (`common_question_id` ASC) VISIBLE)
    ENGINE = InnoDB;
