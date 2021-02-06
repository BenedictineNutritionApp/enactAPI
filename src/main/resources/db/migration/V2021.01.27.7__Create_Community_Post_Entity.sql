CREATE TABLE IF NOT EXISTS `community_post`
(
    `community_post_id` INT NOT NULL AUTO_INCREMENT,
    `community_post_content` VARCHAR(512) NOT NULL,
    `community_post_submission_date` DATETIME NOT NULL,
    `community_post_edit_date` DATETIME NOT NULL,
    `user_id` INT NOT NULL,
    `created_on` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_on` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`community_post_id`),
    UNIQUE INDEX `post_id_UNIQUE` (`community_post_id` ASC) VISIBLE,
    INDEX `fk_post_user1_idx` (`user_id` ASC) VISIBLE,
    CONSTRAINT `fk_post_user1`
        FOREIGN KEY (`user_id`)
            REFERENCES `health_app`.`user` (`user_id`))
    ENGINE = InnoDB;
