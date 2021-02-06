CREATE TABLE IF NOT EXISTS `topic_has_article`
(
    `topic_id` INT NOT NULL,
    `article_id` INT NOT NULL,
    PRIMARY KEY (`topic_id`, `article_id`),
    INDEX `fk_topic_has_article_article1_idx` (`article_id` ASC) VISIBLE,
    INDEX `fk_topic_has_article_topic1_idx` (`topic_id` ASC) VISIBLE,
    CONSTRAINT `fk_topic_has_article_article1`
        FOREIGN KEY (`article_id`)
            REFERENCES `health_app`.`article` (`article_id`),
    CONSTRAINT `fk_topic_has_article_topic1`
        FOREIGN KEY (`topic_id`)
            REFERENCES `health_app`.`topic` (`topic_id`))
    ENGINE = InnoDB;
