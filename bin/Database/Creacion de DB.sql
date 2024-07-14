-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema Paw_Connect
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema Paw_Connect
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `Paw_Connect` DEFAULT CHARACTER SET utf8 ;
USE `Paw_Connect` ;

-- -----------------------------------------------------
-- Table `Paw_Connect`.`direccion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Paw_Connect`.`direccion` (
  `direccion_id` INT NOT NULL,
  `direccion_calle` VARCHAR(30) NULL,
  `direccion_altura` VARCHAR(30) NULL,
  `direccion_departamento` VARCHAR(15) NULL,
  PRIMARY KEY (`direccion_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Paw_Connect`.`administrador`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Paw_Connect`.`administrador` (
  `administrador_id` INT NOT NULL AUTO_INCREMENT,
  `administrador_contraseña` VARCHAR(45) NOT NULL,
  `administrador_nombre` VARCHAR(25) NOT NULL,
  `administrador_email` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`administrador_id`),
  UNIQUE INDEX `usuario_id_UNIQUE` (`administrador_id` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Paw_Connect`.`refugio`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Paw_Connect`.`refugio` (
  `refugio_id` INT NOT NULL,
  `refugio_direccion_id` INT NOT NULL,
  `refugio_nombre` VARCHAR(45) NOT NULL,
  `administrador_id` INT NOT NULL,
  PRIMARY KEY (`refugio_id`, `refugio_direccion_id`, `administrador_id`),
  INDEX `fk_refugio_direccion1_idx` (`refugio_direccion_id` ASC) VISIBLE,
  INDEX `fk_refugio_administrador1_idx` (`administrador_id` ASC) VISIBLE,
  CONSTRAINT `fk_refugio_direccion1`
    FOREIGN KEY (`refugio_direccion_id`)
    REFERENCES `Paw_Connect`.`direccion` (`direccion_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_refugio_administrador1`
    FOREIGN KEY (`administrador_id`)
    REFERENCES `Paw_Connect`.`administrador` (`administrador_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Paw_Connect`.`salud`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Paw_Connect`.`salud` (
  `salud_id` INT NOT NULL AUTO_INCREMENT,
  `salud_vacunado` TINYINT(1) NOT NULL,
  `salud_desparacitado` TINYINT(1) NOT NULL,
  PRIMARY KEY (`salud_id`),
  UNIQUE INDEX `salud_id_UNIQUE` (`salud_id` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Paw_Connect`.`animal`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Paw_Connect`.`animal` (
  `animal_id` INT NOT NULL AUTO_INCREMENT,
  `animal_nombre` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`animal_id`),
  UNIQUE INDEX `animal_id_UNIQUE` (`animal_id` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Paw_Connect`.`color`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Paw_Connect`.`color` (
  `color_id` INT NOT NULL AUTO_INCREMENT,
  `color_nombre` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`color_id`),
  UNIQUE INDEX `color_id_UNIQUE` (`color_id` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Paw_Connect`.`habitacion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Paw_Connect`.`habitacion` (
  `habitacion_id` INT NOT NULL AUTO_INCREMENT,
  `habitacion_otras_mascotas` TINYINT NOT NULL,
  `habitacion_niños` TINYINT NOT NULL,
  PRIMARY KEY (`habitacion_id`),
  UNIQUE INDEX `opciones_id_UNIQUE` (`habitacion_id` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Paw_Connect`.`mascota`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Paw_Connect`.`mascota` (
  `mascota_id` INT NOT NULL AUTO_INCREMENT,
  `mascota_nombre` VARCHAR(30) NOT NULL,
  `mascota_edad` SMALLINT(2) NOT NULL,
  `mascota_descripcion` MEDIUMTEXT NULL,
  `mascota_recomendacion` MEDIUMTEXT NULL,
  `mascota_id_refugio` INT NOT NULL,
  `mascota_salud_id` INT NOT NULL,
  `mascota_animal_id` INT NOT NULL,
  `mascota_color_id` INT NOT NULL,
  `habitacion_id` INT NOT NULL,
  PRIMARY KEY (`mascota_id`, `mascota_id_refugio`, `mascota_salud_id`, `mascota_animal_id`, `mascota_color_id`, `habitacion_id`),
  UNIQUE INDEX `gato_id_UNIQUE` (`mascota_id` ASC) VISIBLE,
  INDEX `fk_gato_refugio1_idx` (`mascota_id_refugio` ASC) VISIBLE,
  INDEX `fk_gato_salud1_idx` (`mascota_salud_id` ASC) VISIBLE,
  INDEX `fk_mascota_animal1_idx` (`mascota_animal_id` ASC) VISIBLE,
  INDEX `fk_mascota_color1_idx` (`mascota_color_id` ASC) VISIBLE,
  INDEX `fk_mascota_habitacion1_idx` (`habitacion_id` ASC) VISIBLE,
  CONSTRAINT `fk_gato_refugio1`
    FOREIGN KEY (`mascota_id_refugio`)
    REFERENCES `Paw_Connect`.`refugio` (`refugio_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_gato_salud1`
    FOREIGN KEY (`mascota_salud_id`)
    REFERENCES `Paw_Connect`.`salud` (`salud_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_mascota_animal1`
    FOREIGN KEY (`mascota_animal_id`)
    REFERENCES `Paw_Connect`.`animal` (`animal_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_mascota_color1`
    FOREIGN KEY (`mascota_color_id`)
    REFERENCES `Paw_Connect`.`color` (`color_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_mascota_habitacion1`
    FOREIGN KEY (`habitacion_id`)
    REFERENCES `Paw_Connect`.`habitacion` (`habitacion_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Paw_Connect`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Paw_Connect`.`usuario` (
  `usuario_id` INT NOT NULL AUTO_INCREMENT,
  `usuario_contraseña` VARCHAR(45) NOT NULL,
  `usuario_nombre` VARCHAR(25) NOT NULL,
  `usuario_email` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`usuario_id`),
  UNIQUE INDEX `usuario_id_UNIQUE` (`usuario_id` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Paw_Connect`.`mascota_raza`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Paw_Connect`.`mascota_raza` (
  `mascota_raza_id` INT NOT NULL AUTO_INCREMENT,
  `mascota_nombre` VARCHAR(30) NOT NULL,
  `animal_id` INT NOT NULL,
  PRIMARY KEY (`mascota_raza_id`, `animal_id`),
  UNIQUE INDEX `gato_raza_id_UNIQUE` (`mascota_raza_id` ASC) VISIBLE,
  INDEX `fk_mascota_raza_animal1_idx` (`animal_id` ASC) VISIBLE,
  CONSTRAINT `fk_mascota_raza_animal1`
    FOREIGN KEY (`animal_id`)
    REFERENCES `Paw_Connect`.`animal` (`animal_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Paw_Connect`.`mascota_has_mascota_raza`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Paw_Connect`.`mascota_has_mascota_raza` (
  `mascota_id` INT NOT NULL,
  `mascota_raza_id` INT NOT NULL,
  PRIMARY KEY (`mascota_id`, `mascota_raza_id`),
  INDEX `fk_gato_has_gato_raza_gato_raza1_idx` (`mascota_raza_id` ASC) VISIBLE,
  INDEX `fk_gato_has_gato_raza_gato_idx` (`mascota_id` ASC) VISIBLE,
  CONSTRAINT `fk_gato_has_gato_raza_gato`
    FOREIGN KEY (`mascota_id`)
    REFERENCES `Paw_Connect`.`mascota` (`mascota_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_gato_has_gato_raza_gato_raza1`
    FOREIGN KEY (`mascota_raza_id`)
    REFERENCES `Paw_Connect`.`mascota_raza` (`mascota_raza_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Paw_Connect`.`telefono`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Paw_Connect`.`telefono` (
  `telefono_id` INT NOT NULL AUTO_INCREMENT,
  `telefono` VARCHAR(15) NULL,
  `telefono_refugio_id` INT NOT NULL,
  PRIMARY KEY (`telefono_id`, `telefono_refugio_id`),
  UNIQUE INDEX `id_telefono_UNIQUE` (`telefono_id` ASC) VISIBLE,
  INDEX `fk_telefono_refugio1_idx` (`telefono_refugio_id` ASC) VISIBLE,
  CONSTRAINT `fk_telefono_refugio1`
    FOREIGN KEY (`telefono_refugio_id`)
    REFERENCES `Paw_Connect`.`refugio` (`refugio_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
