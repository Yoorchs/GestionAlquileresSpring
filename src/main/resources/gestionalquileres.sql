-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 18-04-2017 a las 17:49:32
-- Versión del servidor: 5.6.17
-- Versión de PHP: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `gestionalquileres`
--

DELIMITER $$
--
-- Procedimientos
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `inquilinocreate`(IN `pnombre` VARCHAR(50), IN `papellidos` VARCHAR(100), IN `pemail` VARCHAR(100), IN `pdni` VARCHAR(9), IN `ptelefono` VARCHAR(9), OUT `pcodigo` INT)
BEGIN
	INSERT INTO inquilino(nombre,apellidos,dni,email,telefono, activo)
    VALUES(LOWER(pnombre),LOWER(papellidos),LOWER(pdni),LOWER(pemail),ptelefono, TRUE);
    SET pcodigo=LAST_INSERT_ID();
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `inquilinodelete`(IN `pcodigo` INT)
BEGIN
	UPDATE inquilino as i 
    SET i.activo = false
    WHERE codigo = pcodigo;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `inquilinogetall`()
BEGIN
	SELECT i.codigo as codigo, 
		i.nombre as nombre, 
		i.apellidos as apellidos, 
		i.telefono as telefono, 
		i.email as email, 
		i.dni as dni 
    FROM inquilino as i WHERE i.activo=true;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `inquilinogetbyid`(IN `pcodigo` INT)
BEGIN
	SELECT i.codigo as codigo, i.nombre as nombre, i.apellidos as apellidos, i.telefono as telefono, i.email as email, i.dni as dni FROM inquilino as i
    WHERE codigo = pcodigo AND i.activo=true;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `inquilinoupdate`(IN `pnombre` VARCHAR(50), IN `papellidos` VARCHAR(100), IN `pdni` VARCHAR(9), IN `pemail` VARCHAR(100), IN `ptelefono` VARCHAR(9), IN `pcodigo` INT(11))
    NO SQL
BEGIN
	UPDATE inquilino 
SET nombre = LOWER(pnombre), apellidos = LOWER(papellidos), dni = LOWER(pdni), email = LOWER(pemail), telefono=ptelefono
WHERE codigo = pcodigo;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `pisocreate`(IN `pdireccion` VARCHAR(200), IN `psuperficie` INT(11), IN `pprecionoche` DOUBLE, IN `preferenciacatastral` VARCHAR(45), OUT `pcodigo` INT(11))
BEGIN
	INSERT INTO piso(direccion, superficie, precionoche, referenciacatastral, activo)
    VALUES(LOWER(pdireccion), psuperficie, pprecionoche, LOWER(preferenciacatastral), TRUE);
    SET pcodigo=LAST_INSERT_ID();
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `pisodelete`(IN `pcodigo` INT)
BEGIN
	UPDATE piso as pi 
    SET pi.activo = false
    WHERE codigo = pcodigo;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `pisogetall`()
BEGIN
	SELECT p.codigo as codigo, 
		p.direccion as direccion, 
		p.superficie as superficie, 
		p.precionoche as precionoche,
        p.referenciacatastral as referenciacatastral,
        p.propietario_codigo as propietario_codigo,
		p.alquilado as alquilado
    FROM piso as p
    WHERE p.activo=true;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `pisogetbyid`(IN `pcodigo` INT)
BEGIN
	SELECT p.codigo as codigo, 
    p.direccion as direccion, 
    p.precionoche as precionoche, 
    p.referenciacatastral as referenciacatastral, 
    p.superficie as superficie, 
    p.alquilado as alquilado,
    p.propietario_codigo as propietario_codigo
    FROM piso as p
    WHERE codigo = pcodigo;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `pisoupdate`(IN `pdireccion` VARCHAR(200), IN `psuperficie` INT(11), IN `pprecionoche` DOUBLE, IN `preferenciacatastral` VARCHAR(45), IN `pcodigo` INT(11))
BEGIN
	UPDATE piso 
	SET direccion = LOWER(pdireccion), 
	superficie = psuperficie, 
    precionoche = pprecionoche, 
    referenciacatastral = LOWER(preferenciacatastral)
WHERE codigo = pcodigo;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `propietariocreate`(IN `pnombre` VARCHAR(50), IN `papellidos` VARCHAR(100), IN `pdni` VARCHAR(9), IN `pemail` VARCHAR(100), IN `ptelefono` VARCHAR(9), IN `pnss` BIGINT(12), OUT `pcodigo` INT(11))
BEGIN
	INSERT INTO propietario(nombre,apellidos,dni,email,telefono,nss, activo)
    VALUES(LOWER(pnombre),LOWER(papellidos),LOWER(pdni),LOWER(pemail),ptelefono,pnss, TRUE);
    SET pcodigo=LAST_INSERT_ID();
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `propietariodelete`(IN `pcodigo` INT)
BEGIN

	UPDATE piso, propietario 
    SET propietario.activo = false, piso.activo = false
	WHERE propietario.codigo = piso.propietario_codigo AND propietario.codigo = pcodigo;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `propietariogetall`()
BEGIN
	SELECT p.codigo as codigo, 
		p.nombre as nombre, 
		p.apellidos as apellidos, 
		p.telefono as telefono, 
		p.email as email, 
		p.dni as dni,
        p.nss as nss
    FROM propietario as p
    WHERE p.activo = true;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `propietariogetbyid`(IN `pcodigo` INT)
BEGIN
	SELECT p.codigo as codigo, 
    p.nombre as nombre, 
    p.apellidos as apellidos, 
    p.telefono as telefono, 
    p.email as email, 
    p.dni as dni 
    FROM propietario as p
    WHERE codigo = pcodigo;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `propietarioinforme`(IN `pcodigo` INT)
BEGIN
	
	SELECT 
    p.codigo as codigo, 
    p.nombre as nombre, 
    p.apellidos as apellidos, 
    p.telefono as telefono, 
    p.email as email, 
    p.dni as dni, 
    p.nss as nss,
	pi.codigo as codigopiso, 
    pi.direccion as direccion, 
    pi.superficie as superficie, 
    pi.referenciacatastral as referenciacatastral,
    pi.precionoche as precionoche,
    pi.alquilado as alquilado,
    pi.propietario_codigo
            /*,SUM(cd.precio) as preciocurso*/
    FROM propietario as p
        LEFT JOIN piso as pi ON pi.propietario_codigo = p.codigo
    WHERE p.codigo = pcodigo;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `propietarioupdate`(IN `pnombre` VARCHAR(50), IN `papellidos` VARCHAR(100), IN `pdni` VARCHAR(9), IN `ptelefono` VARCHAR(9), IN `pcodigo` INT(11), IN `pnss` VARCHAR(9), IN `pemail` VARCHAR(150))
    NO SQL
BEGIN
	UPDATE propietario 
SET nombre = LOWER(pnombre), apellidos = LOWER(papellidos), dni = LOWER(pdni), email = LOWER(pemail), telefono=ptelefono, nss=pnss
WHERE codigo = pcodigo;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `alquiler`
--

CREATE TABLE IF NOT EXISTS `alquiler` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `piso_codigo` int(11) NOT NULL,
  `inquilino_codigo` int(11) NOT NULL,
  `fecha_entrada` date NOT NULL,
  `fecha_salida` date NOT NULL,
  PRIMARY KEY (`codigo`),
  KEY `fk_alquiler_piso_codigo_idx` (`piso_codigo`),
  KEY `fk_alquiler_inquilino_codigo_idx` (`inquilino_codigo`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=7 ;

--
-- Volcado de datos para la tabla `alquiler`
--

INSERT INTO `alquiler` (`codigo`, `piso_codigo`, `inquilino_codigo`, `fecha_entrada`, `fecha_salida`) VALUES
(6, 1, 1, '0000-00-00', '0000-00-00');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `inquilino`
--

CREATE TABLE IF NOT EXISTS `inquilino` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `dni` varchar(9) COLLATE utf8_unicode_ci NOT NULL,
  `nombre` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `apellidos` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `telefono` varchar(9) COLLATE utf8_unicode_ci NOT NULL,
  `activo` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=9 ;

--
-- Volcado de datos para la tabla `inquilino`
--

INSERT INTO `inquilino` (`codigo`, `dni`, `nombre`, `apellidos`, `email`, `telefono`, `activo`) VALUES
(1, '14843348f', 'david scott', 'mustaine', 'dmustaine@gmail.com', '685444350', 1),
(2, '16087174P', 'David', 'Ellefson', 'dellefson@yahoo.com', '642585999', 1),
(3, '63355780A', 'Christopher', 'Poland', 'cpoland@aol.com', '653214578', 1),
(4, '12735260E', 'Martin Adam', 'Friedman', 'mfriedman@gmail.com', '666554433', 1),
(5, '45838486S', 'Nick', 'Menza', 'nmenza@hotmail.com', '656656323', 1),
(6, '14612750c', 'james', 'hetfield', 'james@hetfield.com', '123456789', NULL),
(7, '14843348d', 'james', 'hetfield', 'james@hetfield.com', '685444359', NULL),
(8, '33537379k', 'james', 'hetfield', 'james@hetfield.com.fu', '685444359', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `piso`
--

CREATE TABLE IF NOT EXISTS `piso` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `direccion` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `superficie` int(11) NOT NULL,
  `propietario_codigo` int(11) NOT NULL,
  `precionoche` double NOT NULL,
  `referenciacatastral` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `alquilado` tinyint(4) NOT NULL,
  `activo` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`codigo`),
  UNIQUE KEY `codigo_UNIQUE` (`codigo`),
  UNIQUE KEY `referenciacatastral_UNIQUE` (`referenciacatastral`),
  KEY `fk_piso_propietario_codigo_idx` (`propietario_codigo`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=8 ;

--
-- Volcado de datos para la tabla `piso`
--

INSERT INTO `piso` (`codigo`, `direccion`, `superficie`, `propietario_codigo`, `precionoche`, `referenciacatastral`, `alquilado`, `activo`) VALUES
(1, 'Kalea 1', 80, 1, 800, '13077A018000390000FP', 0, 1),
(2, 'Kalea 2', 70, 2, 550, '23077A018000390000FP', 1, 1),
(3, 'Kalea 3', 90, 3, 900, '33077A018000390000FP', 0, 1),
(4, 'Kalea 4', 50, 4, 580, '43077A018000390000FP', 0, 1),
(7, 'Kalea 5', 34, 1, 350, '53077A018000390000FP', 1, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `propietario`
--

CREATE TABLE IF NOT EXISTS `propietario` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `apellidos` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `dni` varchar(9) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `telefono` varchar(9) COLLATE utf8_unicode_ci NOT NULL,
  `nss` varchar(9) COLLATE utf8_unicode_ci NOT NULL,
  `activo` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=5 ;

--
-- Volcado de datos para la tabla `propietario`
--

INSERT INTO `propietario` (`codigo`, `nombre`, `apellidos`, `dni`, `email`, `telefono`, `nss`, `activo`) VALUES
(1, 'tomas enrique', 'araya', '57308715y', 'tommyaraya@gmail.com', '666666666', 'W4040020B', 1),
(2, 'Kerry Ray', 'King', '46970115K', 'kking@aol.com', '626353595', '123456789', 1),
(3, 'Paul', 'Bostaph', '30263054E', 'pbostaph@hotmail.com', '616458923', '222222222', 1),
(4, 'David', 'Lombardo', '02206449J', 'dlombardo@aol.com', '646561289', '333333333', 1);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `alquiler`
--
ALTER TABLE `alquiler`
  ADD CONSTRAINT `fk_alquiler_inquilino_codigo` FOREIGN KEY (`inquilino_codigo`) REFERENCES `inquilino` (`codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_alquiler_piso_codigo` FOREIGN KEY (`piso_codigo`) REFERENCES `piso` (`codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `piso`
--
ALTER TABLE `piso`
  ADD CONSTRAINT `fk_piso_propietario_codigo` FOREIGN KEY (`propietario_codigo`) REFERENCES `propietario` (`codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
