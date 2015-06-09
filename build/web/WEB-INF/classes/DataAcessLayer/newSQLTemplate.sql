create database locadora;


CREATE TABLE `locadora`.`tblusuario` (
  `intChave` INT NOT NULL,
  `strUsuario` VARCHAR(45) NOT NULL,
  `strSenha` VARCHAR(45) NOT NULL,
  `strNome` VARCHAR(45) NOT NULL,
  `intCargo` INT NOT NULL,
  PRIMARY KEY (`intChave`));

insert into tblusuario (intChave, strUsuario, strSenha, strNome, intCargo) values (1, 'neto', '123123', 'Divaldo Neto', 6);

CREATE TABLE `locadora`.`tblcliente` (
  `intChave` INT NOT NULL,
  `strNome` VARCHAR(45) NOT NULL,
  `strCPF` VARCHAR(45) NULL,
  `strEndereco` VARCHAR(45) NULL,
  `strTelefone` VARCHAR(45) NULL,
  `strEmail` VARCHAR(45) NOT NULL,
  `dblDivida` DOUBLE NOT NULL,
  PRIMARY KEY (`intChave`));

CREATE TABLE `locadora`.`tblcarro` (
  `intChave` INT NOT NULL,
  `strModelo` VARCHAR(45) NULL,
  `strMarca` VARCHAR(45) NULL,
  `intAno` INT NULL,
  `strPlaca` VARCHAR(45) NULL,
  `dblDiaria` DOUBLE NULL,
  `btFoto` MEDIUMBLOB NULL,
  PRIMARY KEY (`intChave`));


CREATE TABLE `locadora`.`tblaluguel` (
  `intChave` INT NOT NULL,
  `intChaveCliente` INT NULL,
  `intChaveCarro` INT NULL,
  `intDias` INT NULL,
  `dblValorTotal` DOUBLE NULL,
  `intChavePagamento` INT NULL,
  PRIMARY KEY (`intChave`));
