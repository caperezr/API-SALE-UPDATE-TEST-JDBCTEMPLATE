USE DATABASE trainingFinal;

CREATE TABLE IF NOT EXISTS WarehouseType (
  id INT(11) NOT NULL AUTO_INCREMENT,
  name VARCHAR(255),
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS Warehouse (
  id INT(11) NOT NULL AUTO_INCREMENT,
  idWarehouseType INT(11),
  name VARCHAR(255),
  PRIMARY KEY (id),
  FOREIGN KEY (idWarehouseType) REFERENCES WarehouseType(id)
);

CREATE TABLE IF NOT EXISTS ProductType (
  id INT(11) NOT NULL AUTO_INCREMENT,
  name VARCHAR(255),
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS Product (
  id INT(11) NOT NULL AUTO_INCREMENT,
  idProductType INT(11),
  name VARCHAR(255),
  sku VARCHAR(255),
  partNumber VARCHAR(255),
  cost DECIMAL(19,2),
  totalStock INT(11),
  PRIMARY KEY (id),
  FOREIGN KEY (idProductType) REFERENCES ProductType(id)
);

CREATE TABLE IF NOT EXISTS WarehouseXProduct (
  id INT(11) NOT NULL AUTO_INCREMENT,
  idWarehouse INT(11),
  idProduct INT(11),
  stock INT(11),
  PRIMARY KEY (id),
  FOREIGN KEY (idWarehouse) REFERENCES Warehouse(id),
  FOREIGN KEY (idProduct) REFERENCES Product(id)
);

DROP PROCEDURE IF EXISTS ListAllWarehouseTypes;
CREATE PROCEDURE ListAllWarehouseTypes()
BEGIN
  SELECT * FROM WarehouseType;
END;

DROP PROCEDURE IF EXISTS GetWarehouseTypeById;
CREATE PROCEDURE GetWarehouseTypeById(IN id INT)
BEGIN
  SELECT * FROM WarehouseType WHERE id = id;
END;

DROP PROCEDURE IF EXISTS InsertWarehouseType;
CREATE PROCEDURE InsertWarehouseType(IN name VARCHAR(255))
BEGIN
  INSERT INTO WarehouseType(name) VALUES (name);
END;


-- Listar todas las Warehouse
DROP PROCEDURE IF EXISTS ListAllWarehouses;
CREATE PROCEDURE ListAllWarehouses()
BEGIN
  SELECT * FROM Warehouse;
END;

-- Listar una Warehouse por ID
DROP PROCEDURE IF EXISTS GetWarehouseById;
CREATE PROCEDURE GetWarehouseById(IN id INT)
BEGIN
  SELECT * FROM Warehouse WHERE id = id;
END;

-- Insertar una nueva Warehouse
DROP PROCEDURE IF EXISTS InsertWarehouse;
CREATE PROCEDURE InsertWarehouse(IN idWarehouseType INT, IN name VARCHAR(255))
BEGIN
  INSERT INTO Warehouse(idWarehouseType, name) VALUES (idWarehouseType, name);
END;

-- Listar todas las WarehouseXProduct
DROP PROCEDURE IF EXISTS ListAllWarehouseXProducts;
CREATE PROCEDURE ListAllWarehouseXProducts()
BEGIN
  SELECT * FROM WarehouseXProduct;
END;

-- Listar una WarehouseXProduct por ID
DROP PROCEDURE IF EXISTS GetWarehouseXProductById;
CREATE PROCEDURE GetWarehouseXProductById(IN id INT)
BEGIN
  SELECT * FROM WarehouseXProduct WHERE id = id;
END;

-- Insertar una nueva WarehouseXProduct
DROP PROCEDURE IF EXISTS InsertWarehouseXProduct;
CREATE PROCEDURE InsertWarehouseXProduct(IN idWarehouse INT, IN idProduct INT, IN stock INT)
BEGIN
  INSERT INTO WarehouseXProduct(idWarehouse, idProduct, stock) VALUES (idWarehouse, idProduct, stock);
END;

-- Listar todas las Product
DROP PROCEDURE IF EXISTS ListAllProducts;
CREATE PROCEDURE ListAllProducts()
BEGIN
  SELECT * FROM Product;
END;

-- Listar una Product por ID
DROP PROCEDURE IF EXISTS GetProductById;
CREATE PROCEDURE GetProductById(IN id INT)
BEGIN
  SELECT * FROM Product WHERE id = id;
END;

-- Insertar una nueva Product
DROP PROCEDURE IF EXISTS InsertProduct;
CREATE PROCEDURE InsertProduct(IN idProductType INT, IN name VARCHAR(255), IN sku VARCHAR(255), IN partNumber VARCHAR(255), IN cost DECIMAL(19,2), IN totalStock INT(11))
BEGIN
  INSERT INTO Product(idProductType, name, sku, partNumber, cost, totalStock) VALUES (idProductType, name, sku, partNumber, cost, totalStock);
END;

-- Listar todas las ProductType
DROP PROCEDURE IF EXISTS ListAllProductTypes;
CREATE PROCEDURE ListAllProductTypes()
BEGIN
  SELECT * FROM ProductType;
END;

-- Listar una ProductType por ID
DROP PROCEDURE IF EXISTS GetProductTypeById;
CREATE PROCEDURE GetProductTypeById(IN id INT)
BEGIN
  SELECT * FROM ProductType WHERE id = id;
END;

-- Insertar una nueva ProductType
DROP PROCEDURE IF EXISTS InsertProductType;
CREATE PROCEDURE InsertProductType(IN name VARCHAR(255))
BEGIN
  INSERT INTO ProductType(name) VALUES (name);
END;



