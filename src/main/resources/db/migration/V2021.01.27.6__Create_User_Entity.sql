CREATE TABLE IF NOT EXISTS `user`
(
    `id` INT NOT NULL AUTO_INCREMENT,
    `user_email` VARCHAR(128) NOT NULL,
    `user_password` VARCHAR(64) NOT NULL,
    `user_first_name` VARCHAR(64) NOT NULL,
    `user_last_name` VARCHAR(64) NOT NULL,
    `user_avatar_id` INT NOT NULL,
    `is_admin` TINYINT NOT NULL,
    `created_on` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_on` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `iduser_UNIQUE` (`id` ASC) VISIBLE,
    UNIQUE INDEX `user_email_UNIQUE` (`user_email` ASC) VISIBLE)
    ENGINE = InnoDB;
