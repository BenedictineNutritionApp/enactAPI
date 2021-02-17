ALTER TABLE `user`
    DROP COLUMN `age`,
    ADD `date_of_birth` DATE,
    ADD `race`          VARCHAR(64),
    ADD `ethnicity`     VARCHAR(64),
    ADD `gender`        VARCHAR(32);
