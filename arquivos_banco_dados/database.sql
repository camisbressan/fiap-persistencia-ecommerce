
-- -----------------------------------------------------
-- Schema loja_virtual
-- -----------------------------------------------------
DROP DATABASE IF EXISTS `loja_virtual`;
CREATE DATABASE `loja_virtual`;
USE loja_virtual;

-- -----------------------------------------------------
-- Table `loja_virtual`.`endereco`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `loja_virtual`.`endereco` (
  `id` INT(11) NOT NULL,
  `logradouro` VARCHAR(100) NULL,
  `numero` VARCHAR(20) NULL,
  `complemento` VARCHAR(45) NULL,
  `bairro` VARCHAR(100) NULL,
  `cidade` VARCHAR(100) NULL,
  `estado` VARCHAR(50) NULL,
  `cep` INT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `loja_virtual`.`cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `loja_virtual`.`cliente` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(100) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `telefone` INT NULL,
  `id_endereco` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_cliente_endereco1_idx` (`id_endereco` ASC) VISIBLE,
  CONSTRAINT `fk_cliente_endereco1`
    FOREIGN KEY (`id_endereco`)
    REFERENCES `loja_virtual`.`endereco` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `loja_virtual`.`pedido`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `loja_virtual`.`pedido` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `id_cliente` INT(11) NOT NULL,
  `valor_total` DECIMAL(10,2) NOT NULL,
  `data_compra` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  INDEX `id_cliente` (`id_cliente` ASC) VISIBLE,
  CONSTRAINT `fk_pedido_id_cliente`
    FOREIGN KEY (`id_cliente`)
    REFERENCES `loja_virtual`.`cliente` (`id`)
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `loja_virtual`.`produto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `loja_virtual`.`produto` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `descricao` VARCHAR(45) NULL DEFAULT NULL,
  `quantidade` INT(11) NOT NULL DEFAULT '0',
  `preco` DECIMAL(11,2) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `loja_virtual`.`pedido_carrinho`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `loja_virtual`.`pedido_carrinho` (
  `id_pedido` INT(11) NOT NULL,
  `id_produto` INT(11) NOT NULL,
  `quantidade` INT(11) NOT NULL DEFAULT '0',
  UNIQUE INDEX `id_pedido_id_produto` (`id_pedido` ASC, `id_produto` ASC) VISIBLE,
  INDEX `id_pedido` (`id_pedido` ASC) VISIBLE,
  INDEX `id_produto` (`id_produto` ASC) VISIBLE,
  CONSTRAINT `fk_carrinho_compra_id_pedido`
    FOREIGN KEY (`id_pedido`)
    REFERENCES `loja_virtual`.`pedido` (`id`)
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_carrinho_compra_id_produto`
    FOREIGN KEY (`id_produto`)
    REFERENCES `loja_virtual`.`produto` (`id`)
    ON UPDATE NO ACTION)
ENGINE = InnoDB;