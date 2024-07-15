-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema paw_connect
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema paw_connect
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `paw_connect` DEFAULT CHARACTER SET utf8 ;
USE `paw_connect` ;

-- -----------------------------------------------------
-- Table `paw_connect`.`direccion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `paw_connect`.`direccion` (
  `direccion_id` INT NOT NULL AUTO_INCREMENT,
  `direccion_calle` VARCHAR(30) NULL,
  `direccion_altura` VARCHAR(30) NULL,
  `direccion_departamento` VARCHAR(15) NULL,
  PRIMARY KEY (`direccion_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `paw_connect`.`administrador`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `paw_connect`.`administrador` (
  `administrador_id` INT NOT NULL AUTO_INCREMENT,
  `administrador_contrasenia` VARCHAR(45) NOT NULL,
  `administrador_nombre` VARCHAR(25) NOT NULL,
  `administrador_email` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`administrador_id`),
  UNIQUE INDEX `usuario_id_UNIQUE` (`administrador_id` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `paw_connect`.`telefono`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `paw_connect`.`telefono` (
  `telefono_id` INT NOT NULL AUTO_INCREMENT,
  `telefono_telefono` VARCHAR(15) NULL,
  PRIMARY KEY (`telefono_id`),
  UNIQUE INDEX `id_telefono_UNIQUE` (`telefono_id` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `paw_connect`.`refugio`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `paw_connect`.`refugio` (
  `refugio_id` INT NOT NULL AUTO_INCREMENT,
  `direccion_id` INT NOT NULL,
  `refugio_nombre` VARCHAR(45) NOT NULL,
  `administrador_id` INT NOT NULL,
  `telefono_id` INT NOT NULL,
  PRIMARY KEY (`refugio_id`, `direccion_id`, `administrador_id`, `telefono_id`),
  INDEX `fk_refugio_direccion1_idx` (`direccion_id` ASC) VISIBLE,
  INDEX `fk_refugio_administrador1_idx` (`administrador_id` ASC) VISIBLE,
  INDEX `fk_refugio_telefono1_idx` (`telefono_id` ASC) VISIBLE,
  CONSTRAINT `fk_refugio_direccion1`
    FOREIGN KEY (`direccion_id`)
    REFERENCES `paw_connect`.`direccion` (`direccion_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_refugio_administrador1`
    FOREIGN KEY (`administrador_id`)
    REFERENCES `paw_connect`.`administrador` (`administrador_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_refugio_telefono1`
    FOREIGN KEY (`telefono_id`)
    REFERENCES `paw_connect`.`telefono` (`telefono_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `paw_connect`.`salud`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `paw_connect`.`salud` (
  `salud_id` INT NOT NULL AUTO_INCREMENT,
  `salud_vacunado` TINYINT(1) NOT NULL,
  `salud_desparacitado` TINYINT(1) NOT NULL,
  PRIMARY KEY (`salud_id`),
  UNIQUE INDEX `salud_id_UNIQUE` (`salud_id` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `paw_connect`.`animal`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `paw_connect`.`animal` (
  `animal_id` INT NOT NULL AUTO_INCREMENT,
  `animal_nombre` VARCHAR(20) NOT NULL,
  `animal_tamanio` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`animal_id`),
  UNIQUE INDEX `animal_id_UNIQUE` (`animal_id` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `paw_connect`.`habitacion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `paw_connect`.`habitacion` (
  `habitacion_id` INT NOT NULL AUTO_INCREMENT,
  `habitacion_otras_mascotas` TINYINT NOT NULL,
  `habitacion_ninios` TINYINT NOT NULL,
  PRIMARY KEY (`habitacion_id`),
  UNIQUE INDEX `opciones_id_UNIQUE` (`habitacion_id` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `paw_connect`.`mascota`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `paw_connect`.`mascota` (
  `mascota_id` INT NOT NULL AUTO_INCREMENT,
  `mascota_nombre` VARCHAR(30) NULL,
  `mascota_edad` VARCHAR(20) NULL,
  `mascota_descripcion` MEDIUMTEXT NULL,
  `mascota_recomendacion` MEDIUMTEXT NULL,
  `refugio_id` INT NOT NULL,
  `salud_id` INT NOT NULL,
  `animal_id` INT NOT NULL,
  `habitacion_id` INT NOT NULL,
  `mascota_foto` VARCHAR(50) NULL,
  PRIMARY KEY (`mascota_id`, `refugio_id`, `salud_id`, `animal_id`, `habitacion_id`),
  UNIQUE INDEX `gato_id_UNIQUE` (`mascota_id` ASC) VISIBLE,
  INDEX `fk_gato_refugio1_idx` (`refugio_id` ASC) VISIBLE,
  INDEX `fk_gato_salud1_idx` (`salud_id` ASC) VISIBLE,
  INDEX `fk_mascota_animal1_idx` (`animal_id` ASC) VISIBLE,
  INDEX `fk_mascota_habitacion1_idx` (`habitacion_id` ASC) VISIBLE,
  CONSTRAINT `fk_gato_refugio1`
    FOREIGN KEY (`refugio_id`)
    REFERENCES `paw_connect`.`refugio` (`refugio_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_gato_salud1`
    FOREIGN KEY (`salud_id`)
    REFERENCES `paw_connect`.`salud` (`salud_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_mascota_animal1`
    FOREIGN KEY (`animal_id`)
    REFERENCES `paw_connect`.`animal` (`animal_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_mascota_habitacion1`
    FOREIGN KEY (`habitacion_id`)
    REFERENCES `paw_connect`.`habitacion` (`habitacion_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `paw_connect`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `paw_connect`.`usuario` (
  `usuario_id` INT NOT NULL AUTO_INCREMENT,
  `usuario_contrasenia` VARCHAR(45) NOT NULL,
  `usuario_nombre` VARCHAR(25) NOT NULL,
  `usuario_email` VARCHAR(45) NOT NULL,
  `telefono_id` INT NOT NULL,
  PRIMARY KEY (`usuario_id`, `telefono_id`),
  UNIQUE INDEX `usuario_id_UNIQUE` (`usuario_id` ASC) VISIBLE,
  INDEX `fk_usuario_telefono1_idx` (`telefono_id` ASC) VISIBLE,
  CONSTRAINT `fk_usuario_telefono1`
    FOREIGN KEY (`telefono_id`)
    REFERENCES `paw_connect`.`telefono` (`telefono_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `paw_connect`.`mascota_raza`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `paw_connect`.`mascota_raza` (
  `mascota_raza_id` INT NOT NULL AUTO_INCREMENT,
  `mascota_nombre` VARCHAR(30) NOT NULL,
  `animal_id` INT NOT NULL,
  PRIMARY KEY (`mascota_raza_id`, `animal_id`),
  UNIQUE INDEX `gato_raza_id_UNIQUE` (`mascota_raza_id` ASC) VISIBLE,
  INDEX `fk_mascota_raza_animal1_idx` (`animal_id` ASC) VISIBLE,
  CONSTRAINT `fk_mascota_raza_animal1`
    FOREIGN KEY (`animal_id`)
    REFERENCES `paw_connect`.`animal` (`animal_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `paw_connect`.`mascota_has_mascota_raza`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `paw_connect`.`mascota_has_mascota_raza` (
  `mascota_id` INT NOT NULL,
  `mascota_raza_id` INT NOT NULL,
  PRIMARY KEY (`mascota_id`, `mascota_raza_id`),
  INDEX `fk_gato_has_gato_raza_gato_raza1_idx` (`mascota_raza_id` ASC) VISIBLE,
  INDEX `fk_gato_has_gato_raza_gato_idx` (`mascota_id` ASC) VISIBLE,
  CONSTRAINT `fk_gato_has_gato_raza_gato`
    FOREIGN KEY (`mascota_id`)
    REFERENCES `paw_connect`.`mascota` (`mascota_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_gato_has_gato_raza_gato_raza1`
    FOREIGN KEY (`mascota_raza_id`)
    REFERENCES `paw_connect`.`mascota_raza` (`mascota_raza_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
