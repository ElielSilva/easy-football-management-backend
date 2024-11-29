-- MySQL Script generated by MySQL Workbench
-- Thu Nov 28 22:44:24 2024
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `mydb` ;

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`users` ;

CREATE TABLE IF NOT EXISTS `mydb`.`users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `full_name` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `phone` VARCHAR(45) NULL,
  `url_image` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`teams`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`teams` ;

CREATE TABLE IF NOT EXISTS `mydb`.`teams` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` INT NOT NULL,
  `url_image` VARCHAR(45) NULL,
  `user_id` INT NOT NULL,
  `users_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE,
  UNIQUE INDEX `user_id_UNIQUE` (`user_id` ASC) VISIBLE,
  INDEX `fk_teams_users1_idx` (`users_id` ASC) VISIBLE,
  CONSTRAINT `fk_teams_users1`
    FOREIGN KEY (`users_id`)
    REFERENCES `mydb`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`players`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`players` ;

CREATE TABLE IF NOT EXISTS `mydb`.`players` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `full_name` VARCHAR(45) NOT NULL,
  `position` VARCHAR(45) NOT NULL,
  `number` INT NOT NULL,
  `teams_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_players_teams1_idx` (`teams_id` ASC) VISIBLE,
  CONSTRAINT `fk_players_teams1`
    FOREIGN KEY (`teams_id`)
    REFERENCES `mydb`.`teams` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`chanspions_ships`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`chanspions_ships` ;

CREATE TABLE IF NOT EXISTS `mydb`.`chanspions_ships` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `status` ENUM('created', 'current', 'finish') NOT NULL,
  `created_at` DATE NOT NULL,
  `type` ENUM('cup', 'league') NOT NULL,
  `quantity` INT NOT NULL,
  `award` FLOAT NOT NULL,
  `user_id` INT NOT NULL,
  `users_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE,
  INDEX `fk_chanspions_ships_users1_idx` (`users_id` ASC) VISIBLE,
  CONSTRAINT `fk_chanspions_ships_users1`
    FOREIGN KEY (`users_id`)
    REFERENCES `mydb`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`chanspions_ships_has_teams`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`chanspions_ships_has_teams` ;

CREATE TABLE IF NOT EXISTS `mydb`.`chanspions_ships_has_teams` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `chanspions_ships_id` INT NOT NULL,
  `teams_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_chanspions_ships_has_teams_teams1_idx` (`teams_id` ASC) VISIBLE,
  INDEX `fk_chanspions_ships_has_teams_chanspions_ships1_idx` (`chanspions_ships_id` ASC) VISIBLE,
  CONSTRAINT `fk_chanspions_ships_has_teams_chanspions_ships1`
    FOREIGN KEY (`chanspions_ships_id`)
    REFERENCES `mydb`.`chanspions_ships` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_chanspions_ships_has_teams_teams1`
    FOREIGN KEY (`teams_id`)
    REFERENCES `mydb`.`teams` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`results`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`results` ;

CREATE TABLE IF NOT EXISTS `mydb`.`results` (
  `id` INT NOT NULL,
  `id_home_team` INT NOT NULL,
  `home_team_goals` INT NOT NULL,
  `id_away_team` INT UNSIGNED NOT NULL,
  `away_team_goals` INT NOT NULL,
  `chanspions_ships_has_teams_id` INT NOT NULL,
  `chanspions_ships_has_teams_id1` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_home_team_UNIQUE` (`id_home_team` ASC) VISIBLE,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `id_away_team_UNIQUE` (`id_away_team` ASC) VISIBLE,
  INDEX `fk_results_chanspions_ships_has_teams1_idx` (`chanspions_ships_has_teams_id` ASC) VISIBLE,
  INDEX `fk_results_chanspions_ships_has_teams2_idx` (`chanspions_ships_has_teams_id1` ASC) VISIBLE,
  CONSTRAINT `fk_results_chanspions_ships_has_teams1`
    FOREIGN KEY (`chanspions_ships_has_teams_id`)
    REFERENCES `mydb`.`chanspions_ships_has_teams` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_results_chanspions_ships_has_teams2`
    FOREIGN KEY (`chanspions_ships_has_teams_id1`)
    REFERENCES `mydb`.`chanspions_ships_has_teams` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`statistics`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`statistics` ;

CREATE TABLE IF NOT EXISTS `mydb`.`statistics` (
  `id` INT NOT NULL,
  `gols` INT NULL,
  `yellow_cards` INT NULL,
  `red_cards` INT NULL,
  `participations` INT NULL,
  `goal_against` INT NULL,
  `matches` INT NULL,
  `players_id` INT NOT NULL,
  `chanspions_ships_has_teams_id` INT NOT NULL,
  `results_id` INT NOT NULL,
  PRIMARY KEY (`id`, `players_id`, `chanspions_ships_has_teams_id`, `results_id`),
  UNIQUE INDEX `idstatistics_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_statistics_players1_idx` (`players_id` ASC) VISIBLE,
  INDEX `fk_statistics_chanspions_ships_has_teams1_idx` (`chanspions_ships_has_teams_id` ASC) VISIBLE,
  INDEX `fk_statistics_results1_idx` (`results_id` ASC) VISIBLE,
  CONSTRAINT `fk_statistics_players1`
    FOREIGN KEY (`players_id`)
    REFERENCES `mydb`.`players` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_statistics_chanspions_ships_has_teams1`
    FOREIGN KEY (`chanspions_ships_has_teams_id`)
    REFERENCES `mydb`.`chanspions_ships_has_teams` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_statistics_results1`
    FOREIGN KEY (`results_id`)
    REFERENCES `mydb`.`results` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;