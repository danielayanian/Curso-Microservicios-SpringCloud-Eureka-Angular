-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         11.1.2-MariaDB - mariadb.org binary distribution
-- SO del servidor:              Win64
-- HeidiSQL Versión:             12.3.0.6589
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Volcando estructura de base de datos para db_microservicios_examenes
DROP DATABASE IF EXISTS `db_microservicios_examenes`;
CREATE DATABASE IF NOT EXISTS `db_microservicios_examenes` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;
USE `db_microservicios_examenes`;

-- Volcando estructura para tabla db_microservicios_examenes.asignaturas
DROP TABLE IF EXISTS `asignaturas`;
CREATE TABLE IF NOT EXISTS `asignaturas` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  `padre_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1a657vrlox5uthk8wbwrxh6e8` (`padre_id`),
  CONSTRAINT `FK1a657vrlox5uthk8wbwrxh6e8` FOREIGN KEY (`padre_id`) REFERENCES `asignaturas` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Volcando datos para la tabla db_microservicios_examenes.asignaturas: ~25 rows (aproximadamente)
DELETE FROM `asignaturas`;
INSERT INTO `asignaturas` (`id`, `nombre`, `padre_id`) VALUES
	(1, 'Matemáticas', NULL),
	(2, 'Lenguaje', NULL),
	(3, 'Inglés', NULL),
	(4, 'Ciencias Naturales', NULL),
	(5, 'Ciencias Sociales y Historia', NULL),
	(6, 'Música', NULL),
	(7, 'Artes', NULL),
	(8, 'Algebra', 1),
	(9, 'Aritmética', 1),
	(10, 'Trigonometría', 1),
	(11, 'Lectura y comprensión', 2),
	(12, 'Verbos', 2),
	(13, 'Gramática', 2),
	(14, 'Inglés', 3),
	(15, 'Gramática', 3),
	(16, 'Verbos', 3),
	(17, 'Ciencias Naturales', 4),
	(18, 'Biología', 4),
	(19, 'Física', 4),
	(20, 'Quimica', 4),
	(21, 'Historia', 5),
	(22, 'Ciencias Sociales', 5),
	(23, 'Filosofía', 5),
	(24, 'Música', 6),
	(25, 'Artes', 7);

-- Volcando estructura para tabla db_microservicios_examenes.cursos
DROP TABLE IF EXISTS `cursos`;
CREATE TABLE IF NOT EXISTS `cursos` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_at` datetime(6) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Volcando datos para la tabla db_microservicios_examenes.cursos: ~8 rows (aproximadamente)
DELETE FROM `cursos`;
INSERT INTO `cursos` (`id`, `create_at`, `nombre`) VALUES
	(1, '2023-09-17 22:26:07.000000', '1ro Primaria'),
	(2, '2023-09-17 22:26:07.000000', '2do Primaria'),
	(3, '2023-09-17 22:26:07.000000', '3ro Primaria'),
	(4, '2023-09-17 22:26:07.000000', '4to Primaria'),
	(5, '2023-09-17 22:26:07.000000', '1ro Secundaria'),
	(6, '2023-09-17 22:26:07.000000', '2do Secundaria'),
	(7, '2023-09-17 22:26:07.000000', '3ro Secundaria'),
	(8, '2023-09-17 22:26:07.000000', '4to Secundaria');

-- Volcando estructura para tabla db_microservicios_examenes.cursos_alumnos
DROP TABLE IF EXISTS `cursos_alumnos`;
CREATE TABLE IF NOT EXISTS `cursos_alumnos` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `alumno_id` bigint(20) DEFAULT NULL,
  `curso_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_pb88irw9u3c0h2lsf3q1kluv9` (`alumno_id`),
  KEY `FKb90xg2w8jai6w555c3erim0cv` (`curso_id`),
  CONSTRAINT `FKb90xg2w8jai6w555c3erim0cv` FOREIGN KEY (`curso_id`) REFERENCES `cursos` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Volcando datos para la tabla db_microservicios_examenes.cursos_alumnos: ~2 rows (aproximadamente)
DELETE FROM `cursos_alumnos`;
INSERT INTO `cursos_alumnos` (`id`, `alumno_id`, `curso_id`) VALUES
	(1, 2, 1),
	(4, 5, 7);

-- Volcando estructura para tabla db_microservicios_examenes.cursos_examenes
DROP TABLE IF EXISTS `cursos_examenes`;
CREATE TABLE IF NOT EXISTS `cursos_examenes` (
  `curso_id` bigint(20) NOT NULL,
  `examenes_id` bigint(20) NOT NULL,
  KEY `FK6ags9h8g0q074pch8ckfy8nw5` (`examenes_id`),
  KEY `FKbj3nwplxm8hswqcbt0tmrqagj` (`curso_id`),
  CONSTRAINT `FK6ags9h8g0q074pch8ckfy8nw5` FOREIGN KEY (`examenes_id`) REFERENCES `examenes` (`id`),
  CONSTRAINT `FKbj3nwplxm8hswqcbt0tmrqagj` FOREIGN KEY (`curso_id`) REFERENCES `cursos` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Volcando datos para la tabla db_microservicios_examenes.cursos_examenes: ~0 rows (aproximadamente)
DELETE FROM `cursos_examenes`;
INSERT INTO `cursos_examenes` (`curso_id`, `examenes_id`) VALUES
	(7, 1);

-- Volcando estructura para tabla db_microservicios_examenes.examenes
DROP TABLE IF EXISTS `examenes`;
CREATE TABLE IF NOT EXISTS `examenes` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_at` datetime(6) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `asignatura_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6ti4mhut3mays6044rt8syqd8` (`asignatura_id`),
  CONSTRAINT `FK6ti4mhut3mays6044rt8syqd8` FOREIGN KEY (`asignatura_id`) REFERENCES `asignaturas` (`id`),
  CONSTRAINT `FK_examenes_asignaturas` FOREIGN KEY (`asignatura_id`) REFERENCES `asignaturas` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Volcando datos para la tabla db_microservicios_examenes.examenes: ~0 rows (aproximadamente)
DELETE FROM `examenes`;
INSERT INTO `examenes` (`id`, `create_at`, `nombre`, `asignatura_id`) VALUES
	(1, '2023-09-18 02:29:43.123000', 'Examen de historia', NULL);

-- Volcando estructura para tabla db_microservicios_examenes.preguntas
DROP TABLE IF EXISTS `preguntas`;
CREATE TABLE IF NOT EXISTS `preguntas` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `texto` varchar(255) DEFAULT NULL,
  `examen_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9hlw51x7hfqs1tv3sviwqycqi` (`examen_id`),
  CONSTRAINT `FK9hlw51x7hfqs1tv3sviwqycqi` FOREIGN KEY (`examen_id`) REFERENCES `examenes` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Volcando datos para la tabla db_microservicios_examenes.preguntas: ~3 rows (aproximadamente)
DELETE FROM `preguntas`;
INSERT INTO `preguntas` (`id`, `texto`, `examen_id`) VALUES
	(7, 'Quien descubrio America', 1),
	(9, 'Cual es la capital de Francia', 1),
	(10, 'Quien es Messi', 1);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
