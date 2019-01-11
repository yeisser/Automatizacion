-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema tatiendo
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `tatiendo` ;

-- -----------------------------------------------------
-- Schema tatiendo
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `tatiendo` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `tatiendo` ;

-- -----------------------------------------------------
-- Table `tatiendo`.`Categoria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tatiendo`.`Categoria` (
  `idCategoria` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NULL,
  `habilitado` BIT NULL,
  PRIMARY KEY (`idCategoria`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tatiendo`.`Producto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tatiendo`.`Producto` (
  `idProducto` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NULL,
  `descripcion` VARCHAR(45) NULL,
  `idCategoria` INT NOT NULL,
  `esInterno` TINYINT(1) NULL,
  `ubicacionProducto` INT NULL COMMENT '1 Local Central\n2 Local Almacen\n3 Sucursales',
  `precio` DECIMAL(18,2) NULL,
  `cantidad` DECIMAL(18,2) NULL,
  `habilitado` BIT NULL,
  PRIMARY KEY (`idProducto`),
  INDEX `fk_Producto_Categoria_idx` (`idCategoria` ASC),
  CONSTRAINT `fk_Producto_Categoria`
    FOREIGN KEY (`idCategoria`)
    REFERENCES `tatiendo`.`Categoria` (`idCategoria`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tatiendo`.`Cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tatiendo`.`Cliente` (
  `idCliente` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NULL,
  `apellido` VARCHAR(45) NULL,
  `correo` VARCHAR(45) NULL,
  `clave` VARCHAR(45) NULL,
  `tipoCliente` INT NULL COMMENT 'Los clientes del tipo 1 son administradores, los clientes del tipo 2 son almaceneros, los cliente del tipo 3 pueden realizar compras y registrarse en el sistema.',
  `habilitado` BIT NULL,
  PRIMARY KEY (`idCliente`),
  UNIQUE INDEX `correo_UNIQUE` (`correo` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tatiendo`.`Pedido`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tatiendo`.`Pedido` (
  `idPedido` INT NOT NULL AUTO_INCREMENT,
  `idCliente` INT NOT NULL,
  `fecha` DATETIME NULL,
  `total` DECIMAL(18,2) NULL,
  `atendido` BIT NULL,
  PRIMARY KEY (`idPedido`),
  INDEX `fk_Venta_Cliente1_idx` (`idCliente` ASC),
  CONSTRAINT `fk_Venta_Cliente1`
    FOREIGN KEY (`idCliente`)
    REFERENCES `tatiendo`.`Cliente` (`idCliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tatiendo`.`DetallePedido`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tatiendo`.`DetallePedido` (
  `idPedido` INT NOT NULL,
  `idProducto` INT NOT NULL,
  `cantidad` DECIMAL(18,2) NULL,
  `subTotal` DECIMAL(18,2) NULL,
  PRIMARY KEY (`idPedido`, `idProducto`),
  INDEX `fk_DetallePedido_Pedido1_idx` (`idPedido` ASC),
  CONSTRAINT `fk_DetallePedido_Producto1`
    FOREIGN KEY (`idProducto`)
    REFERENCES `tatiendo`.`Producto` (`idProducto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_DetallePedido_Pedido1`
    FOREIGN KEY (`idPedido`)
    REFERENCES `tatiendo`.`Pedido` (`idPedido`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

USE `tatiendo`;

DELIMITER $$
USE `tatiendo`$$
CREATE DEFINER = CURRENT_USER TRIGGER `tatiendo`.`DetallePedido_BEFORE_INSERT` BEFORE INSERT ON `DetallePedido` FOR EACH ROW
BEGIN
UPDATE Producto SET
cantidad = cantidad - NEW.cantidad
WHERE
idProducto = NEW.idProducto;
END
$$


DELIMITER ;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `tatiendo`.`Categoria`
-- -----------------------------------------------------
START TRANSACTION;
USE `tatiendo`;
INSERT INTO `tatiendo`.`Categoria` (`nombre`, `habilitado`) VALUES ( 'CARAMELOS', 1);
INSERT INTO `tatiendo`.`Categoria` (`nombre`, `habilitado`) VALUES ( 'CHOCOLATES', 1);
INSERT INTO `tatiendo`.`Categoria` (`nombre`, `habilitado`) VALUES ( 'GASEOSAS', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `tatiendo`.`Producto`
-- -----------------------------------------------------
START TRANSACTION;
USE `tatiendo`;
INSERT INTO `tatiendo`.`Producto` ( `nombre`, `descripcion`, `idCategoria`, `esInterno`, `ubicacionProducto`, `precio`, `cantidad`, `habilitado`) VALUES ( 'LIMON ARCOR', 'BOLSA DE 100 UNIDADES', 1, 1, 1, 0.10, 100, 1);
INSERT INTO `tatiendo`.`Producto` ( `nombre`, `descripcion`, `idCategoria`, `esInterno`, `ubicacionProducto`, `precio`, `cantidad`, `habilitado`) VALUES ( 'FRESA AMBROSOLI', 'BOLSA DE 100 UNIDADES', 1, 1, 2, 0.20, 200, 1);
INSERT INTO `tatiendo`.`Producto` ( `nombre`, `descripcion`, `idCategoria`, `esInterno`, `ubicacionProducto`, `precio`, `cantidad`, `habilitado`) VALUES ( 'PRINCESA', 'CAJA DE 50 UNIDADES', 2, 1, 1, 1.20, 50, 1);
INSERT INTO `tatiendo`.`Producto` ( `nombre`, `descripcion`, `idCategoria`, `esInterno`, `ubicacionProducto`, `precio`, `cantidad`, `habilitado`) VALUES ( 'SUBLIME', 'CAJA DE 100 UNIDADES', 2, 1, 3, 1.00, 80, 1);
INSERT INTO `tatiendo`.`Producto` ( `nombre`, `descripcion`, `idCategoria`, `esInterno`, `ubicacionProducto`, `precio`, `cantidad`, `habilitado`) VALUES ( 'COCA COLA', '', 3, 1, 1, 1.00, 200, 1);
INSERT INTO `tatiendo`.`Producto` ( `nombre`, `descripcion`, `idCategoria`, `esInterno`, `ubicacionProducto`, `precio`, `cantidad`, `habilitado`) VALUES ( 'INCA COLA', '', 3, 1, 2, 1.00, 500, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `tatiendo`.`Cliente`
-- -----------------------------------------------------
START TRANSACTION;
USE `tatiendo`;
INSERT INTO `tatiendo`.`Cliente` ( `nombre`, `apellido`, `correo`, `clave`, `tipoCliente`, `habilitado`) VALUES ( 'HENRY', 'WONG', 'hwong@tatiendo.com', 'clave', 1, 1);
INSERT INTO `tatiendo`.`Cliente` ( `nombre`, `apellido`, `correo`, `clave`, `tipoCliente`, `habilitado`) VALUES ( 'JOSE', 'PEREZ', 'jperez@tatiendo.com', 'clave', 2, 1);

COMMIT;

