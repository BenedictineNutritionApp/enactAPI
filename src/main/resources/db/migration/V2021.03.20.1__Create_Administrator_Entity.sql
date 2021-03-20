CREATE TABLE IF NOT EXISTS `administrator`
(
    `id`         INT          NOT NULL AUTO_INCREMENT,
    `email`      VARCHAR(128) NOT NULL,
    `password`   VARCHAR(32)  NOT NULL,
    `first_name` VARCHAR(32)  NOT NULL,
    `last_name`  VARCHAR(32)  NOT NULL,
    `level`      INT          NOT NULL,
    `created_on` DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_on` DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE
)
    ENGINE = InnoDB;
