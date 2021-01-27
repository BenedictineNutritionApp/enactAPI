CREATE TABLE IF NOT EXISTS `user`
(
    `user_id` INT NOT NULL AUTO_INCREMENT,
    `user_email` VARCHAR(64) NOT NULL,
    `user_password` VARCHAR(64) NOT NULL,
    `user_avatar_id` INT NOT NULL,
    `is_admin` TINYINT NOT NULL,
    `created_on` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_on` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`user_id`),
    UNIQUE INDEX `iduser_UNIQUE` (`user_id` ASC) VISIBLE,
    UNIQUE INDEX `user_email_UNIQUE` (`user_email` ASC) VISIBLE)
    ENGINE = InnoDB;
