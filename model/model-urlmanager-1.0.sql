SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

CREATE SCHEMA IF NOT EXISTS `urlmanager` DEFAULT CHARACTER SET utf8 ;
USE `urlmanager` ;

-- -----------------------------------------------------
-- Table `urlmanager`.`WEBPAGE`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `urlmanager`.`WEBPAGE` ;

CREATE  TABLE IF NOT EXISTS `urlmanager`.`WEBPAGE` (
  `Id_Webpage` INT NOT NULL AUTO_INCREMENT ,
  `URL` TEXT NOT NULL ,
  `Is_Root` BIT NOT NULL ,
  PRIMARY KEY (`Id_Webpage`) ,
  INDEX `INDEX_URL` (`URL`(256) ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `urlmanager`.`LOCALE`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `urlmanager`.`LOCALE` ;

CREATE  TABLE IF NOT EXISTS `urlmanager`.`LOCALE` (
  `Id_Locale` INT NOT NULL AUTO_INCREMENT ,
  `Language` VARCHAR(4) NOT NULL ,
  `Long_Language` VARCHAR(45) NULL ,
  `Country` VARCHAR(4) NULL ,
  `Long_Country` VARCHAR(45) NULL ,
  PRIMARY KEY (`Id_Locale`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `urlmanager`.`TAG`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `urlmanager`.`TAG` ;

CREATE  TABLE IF NOT EXISTS `urlmanager`.`TAG` (
  `Id_Tag` INT NOT NULL AUTO_INCREMENT ,
  `Label` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`Id_Tag`) ,
  INDEX `INDEX_LABEL` (`Label` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `urlmanager`.`REQUEST`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `urlmanager`.`REQUEST` ;

CREATE  TABLE IF NOT EXISTS `urlmanager`.`REQUEST` (
  `Id_Request` INT NOT NULL AUTO_INCREMENT ,
  `Label` VARCHAR(255) NOT NULL ,
  `Add_Date` DATE NULL ,
  `Modification_Date` DATE NULL ,
  PRIMARY KEY (`Id_Request`) ,
  INDEX `INDEX_LABEL` (`Label` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `urlmanager`.`WEBPAGE_TAG`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `urlmanager`.`WEBPAGE_TAG` ;

CREATE  TABLE IF NOT EXISTS `urlmanager`.`WEBPAGE_TAG` (
  `Id_Webpage` INT NOT NULL ,
  `Id_Tag` INT NOT NULL ,
  PRIMARY KEY (`Id_Webpage`, `Id_Tag`) ,
  INDEX `FK_WEBPAGE_TAG_TAG` (`Id_Tag` ASC) ,
  CONSTRAINT `FK_WEBPAGE_TAG_WEBPAGE`
    FOREIGN KEY (`Id_Webpage` )
    REFERENCES `urlmanager`.`WEBPAGE` (`Id_Webpage` )
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_WEBPAGE_TAG_TAG`
    FOREIGN KEY (`Id_Tag` )
    REFERENCES `urlmanager`.`TAG` (`Id_Tag` )
    ON DELETE RESTRICT
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `urlmanager`.`REQUEST_TAG`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `urlmanager`.`REQUEST_TAG` ;

CREATE  TABLE IF NOT EXISTS `urlmanager`.`REQUEST_TAG` (
  `Id_Request` INT NOT NULL ,
  `Id_Tag` INT NOT NULL ,
  PRIMARY KEY (`Id_Request`, `Id_Tag`) ,
  INDEX `FK_REQUEST_TAG_TAG` (`Id_Tag` ASC) ,
  CONSTRAINT `FK_REQUEST_TAG_REQUEST`
    FOREIGN KEY (`Id_Request` )
    REFERENCES `urlmanager`.`REQUEST` (`Id_Request` )
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_REQUEST_TAG_TAG`
    FOREIGN KEY (`Id_Tag` )
    REFERENCES `urlmanager`.`TAG` (`Id_Tag` )
    ON DELETE RESTRICT
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `urlmanager`.`WEBPAGE_LOCALE`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `urlmanager`.`WEBPAGE_LOCALE` ;

CREATE  TABLE IF NOT EXISTS `urlmanager`.`WEBPAGE_LOCALE` (
  `Id_Webpage` INT NOT NULL ,
  `Id_Locale` INT NOT NULL ,
  PRIMARY KEY (`Id_Webpage`, `Id_Locale`) ,
  INDEX `FK_WEBPAGE_LOCALE_LOCALE` (`Id_Locale` ASC) ,
  CONSTRAINT `FK_WEBPAGE_LOCALE_WEBPAGE`
    FOREIGN KEY (`Id_Webpage` )
    REFERENCES `urlmanager`.`WEBPAGE` (`Id_Webpage` )
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_WEBPAGE_LOCALE_LOCALE`
    FOREIGN KEY (`Id_Locale` )
    REFERENCES `urlmanager`.`LOCALE` (`Id_Locale` )
    ON DELETE RESTRICT
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `urlmanager`.`REQUEST_LOCALE`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `urlmanager`.`REQUEST_LOCALE` ;

CREATE  TABLE IF NOT EXISTS `urlmanager`.`REQUEST_LOCALE` (
  `Id_Request` INT NOT NULL ,
  `Id_Locale` INT NOT NULL ,
  PRIMARY KEY (`Id_Request`, `Id_Locale`) ,
  INDEX `FK_REQUEST_LOCALE_LOCALE` (`Id_Locale` ASC) ,
  CONSTRAINT `FK_REQUEST_LOCALE_REQUEST`
    FOREIGN KEY (`Id_Request` )
    REFERENCES `urlmanager`.`REQUEST` (`Id_Request` )
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_REQUEST_LOCALE_LOCALE`
    FOREIGN KEY (`Id_Locale` )
    REFERENCES `urlmanager`.`LOCALE` (`Id_Locale` )
    ON DELETE RESTRICT
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
