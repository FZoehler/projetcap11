-- Create Correntista table
CREATE TABLE Correntista (
    IDCorrentista VARCHAR2(50) PRIMARY KEY,
    NomeCompleto VARCHAR2(100) NOT NULL,
    Telefone VARCHAR2(20) NOT NULL,
    Email VARCHAR2(100) UNIQUE NOT NULL,
    Endereco VARCHAR2(200) NOT NULL,
    DadosPessoais VARCHAR2(200),
    Password VARCHAR2(100) NOT NULL
);

-- Create ContaCorrente table
CREATE TABLE ContaCorrente (
    idContaCorrente VARCHAR2(50) PRIMARY KEY,
    limiteDeCredito NUMBER(10,2) NOT NULL,
    cartoes NUMBER(10,2) NOT NULL,
    chequeEspecial NUMBER(10,2) NOT NULL
);

-- Create Transfer table
CREATE TABLE Transfer (
    id VARCHAR2(50) PRIMARY KEY,
    amount NUMBER(10,2) NOT NULL,
    data VARCHAR2(50) NOT NULL,
    description VARCHAR2(200),
    sourceAccountId VARCHAR2(50) NOT NULL,
    targetAccountId VARCHAR2(50) NOT NULL,
    availableBalance NUMBER(10,2) NOT NULL
);

-- Create Deposito table
CREATE TABLE Deposito (
    id VARCHAR2(50) PRIMARY KEY,
    amount NUMBER(10,2) NOT NULL,
    data_operacao VARCHAR2(50) NOT NULL,
    description VARCHAR2(200),
    targetAccountId VARCHAR2(50) NOT NULL
);

-- Create Saques table
CREATE TABLE Saques (
    id VARCHAR2(50) PRIMARY KEY,
    amount NUMBER(10,2) NOT NULL,
    data VARCHAR2(50) NOT NULL,
    description VARCHAR2(200),
    sourceAccountId VARCHAR2(50) NOT NULL,
    availableBalance NUMBER(10,2) NOT NULL
);