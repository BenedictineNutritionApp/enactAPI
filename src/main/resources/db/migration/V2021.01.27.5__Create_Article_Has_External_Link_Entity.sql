CREATE TABLE IF NOT EXISTS `article_has_external_link`
(
    `article_id` INT NOT NULL,
    `external_link_id` INT NOT NULL,
    PRIMARY KEY (`article_id`, `external_link_id`),
    INDEX `fk_article_has_external_link_external_link1_idx` (`external_link_id` ASC) VISIBLE,
    INDEX `fk_article_has_external_link_article1_idx` (`article_id` ASC) VISIBLE,
    CONSTRAINT `fk_article_has_external_link_article1`
        FOREIGN KEY (`article_id`)
            REFERENCES `health_app`.`article` (`article_id`),
    CONSTRAINT `fk_article_has_external_link_external_link1`
        FOREIGN KEY (`external_link_id`)
            REFERENCES `health_app`.`external_link` (`external_link_id`))
    ENGINE = InnoDB;
