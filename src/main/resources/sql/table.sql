CREATE TABLE `proyecto_db`.`inscripciones` (
  `id_inscripcion` INT NOT NULL AUTO_INCREMENT,
  `nombre_estudiante` VARCHAR(45) NULL,
  `edad` INT NULL,
  `id_curso` INT NULL,
  `calificaciones` INT NULL,
  PRIMARY KEY (`id_inscripcion`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = ascii;
