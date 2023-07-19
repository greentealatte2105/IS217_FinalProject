-- Create database
CREATE DATABASE QuanLyQuanCafe;

USE QuanLyQuanCafe;
SET sql_mode=(SELECT REPLACE(@@sql_mode,'ONLY_FULL_GROUP_BY',''));

-- DELETE FROM Bill WHERE id > 0;
-- ALTER TABLE Bill AUTO_INCREMENT = 1;

-- get auto increment id in table
SELECT AUTO_INCREMENT
FROM information_schema.TABLES
WHERE TABLE_SCHEMA = 'quanlyquancafe'
    AND TABLE_NAME = 'Account';

-- Create table Account
CREATE TABLE Account
(
	id int auto_increment primary key,
    userName NVARCHAR(100) not null,
    password NVARCHAR(1000) NOT NULL DEFAULT '123456',
    email NVARCHAR(100) not null,
    phoneNumber nvarchar(100), 
    role nvarchar(100)
);

-- Create Table StaffMangement
CREATE TABLE StaffManagement
(
	id int primary key,
    timeCount float default 0,
		
    foreign key (id) references Account(id)
);

-- Create table ProductCategory
CREATE TABLE ProductCategory
(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name NVARCHAR(100) NOT NULL DEFAULT 'Chưa cập nhật'
);

CREATE TABLE CustomerCategory
(
	id int primary key,
    name nvarchar(20) not null,
    expense int not null,
    discount int not null
);

CREATE TABLE Customer
(
	id int auto_increment primary key,
    idRank int not null,
    phoneNumber NVARCHAR(100) not null,
    total int default 0,
    
    FOREIGN KEY (idRank) REFERENCES CustomerCategory(id)
);

-- Create table Product
CREATE TABLE Product
(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name NVARCHAR(100) NOT NULL DEFAULT 'Chưa cập nhật',
    idCategory INT NOT NULL DEFAULT 1,
    price FLOAT NOT NULL DEFAULT 0,

    FOREIGN KEY (idCategory) REFERENCES ProductCategory(id)
);

-- Create table Bill
CREATE TABLE Bill
(
    id INT AUTO_INCREMENT PRIMARY KEY,
    idStaff int not null,
    dateCheckIn DATE NOT NULL,
    discount INT DEFAULT 0,
    totalPrice FLOAT DEFAULT 0,
	-- sdt khách hàng ?? 
    foreign key (idStaff) references Account(id)
);

-- Create table BillInfo
CREATE TABLE BillInfo
(
    id INT AUTO_INCREMENT PRIMARY KEY,
    idBill INT NOT NULL,
    idProduct INT NOT NULL,
    count INT NOT NULL DEFAULT 0,

    FOREIGN KEY (idBill) REFERENCES Bill(id),
    FOREIGN KEY (idProduct) REFERENCES Product(id)
);

CREATE TABLE Bill_Customer
(
	idBill INT NOT NULL PRIMARY KEY,
    idCustomer INT NOT NULL,
    
    FOREIGN KEY (idBill) REFERENCES Bill(id),
    FOREIGN KEY (idCustomer) REFERENCES Customer(id)
);

CREATE TABLE Salary
(
	idStaff INT NOT NULL PRIMARY KEY,
    salary INT NOT NULL DEFAULT 0,
    
    FOREIGN KEY (idStaff) REFERENCES Account(id)
);

-- INSERT DATA ----------------------------------------------------------------------------
-- Insert data into Account table
INSERT INTO Account
    (userName, password, email, phoneNumber, role)
VALUES
    ('admin', '1',"admin@gmail.com" ,'0123456789', "admin"),
    ('nhanvien1', '1', 'staff@gmail.com', '0123456789', "staff"),
    ('nhanvien2', '1', 'staff2@gmail.com', '0901491812', 'staff');

-- Insert data into ProductCategory
INSERT INTO ProductCategory (name)
VALUES
    ('Cafe'),
    ('Nước đóng chai'),
    ('Trà'),
    ('Sinh Tố'),
    ('Hồng Trà'),
    ('Topping'),
    ('Trà Sữa');

-- Insert data into Product
INSERT INTO Product (name, idCategory, price)
VALUES
    ('Cafe đá', 1, 18000),
    ('Cafe sữa', 1, 20000),
    ('Espresso', 1, 24000),
    ('Latte Macchiato', 1, 24000),
    ('Cappuccino', 1, 24000),
    ('Cafe Latte', 1, 24000),
    ('Cafe Mocha', 1, 24000),
    ('Americano', 1, 24000),
    ('Espresso Con Panna', 1, 24000),
    ('Cappuccino Viennese', 1, 24000),
    ('Cafe Dubai', 1, 1500000),

    ('Red bull', 2, 12000),
    ('7-up', 2, 12000),
    ('Aquafina', 2, 12000),
    ('Dasani', 2, 12000),
    ('Lavie', 2, 12000),
    ('Mirinda soda kem', 2, 12000),
    ('Pepsi light', 2, 12000),
    ('Mirinda xá xị', 2, 12000),
    ('Moutain dew', 2, 12000),
    ('Trà ô long tea plus', 2, 12000),

    ('Trà dưa hấu bạc hà', 3, 24000),
    ('Trà đào cam sả', 3, 30000),
    ('Trà xoài', 3, 16000),
    ('Trà bí đao', 3, 16000),
    ('Lục trà xí muội', 3, 16000),

    ('Sinh tố kiwi', 4, 22000),
    ('Sinh tố dâu', 4, 22000),
    ('Sinh tố đậu xanh', 4, 17000),
    ('Sinh tố cà rốt', 4, 22000),
    ('Sinh tố chuối', 4, 17000),
    ('Sinh tố cà chua', 4, 17000),
    ('Sinh tố rau má', 4, 12000),

    ('Hồng trà đào', 5, 22000),
    ('Hồng trà nhiệt đới', 5, 24000),
    ('Hồng trà táo', 5, 22000),
    ('Hồng trà chanh mật ong', 5, 22000),
    ('Hồng trà japan', 5, 22000),

    ('Topping flan chocolate', 6, 7000),
    ('Topping flan trứng gà', 6, 7000),
    ('Topping sương sáo', 6, 7000),
    ('Topping thạch khoai môn', 6, 7000),
    ('Topping thạch trái cây', 6, 7000),
    ('Topping thạch phô mai', 6, 7000),
    ('Topping trân châu trắng', 6, 7000),
    ('Topping trân châu đen', 6, 7000),

    ('Trà sữa matcha', 7, 22000),
    ('Trà sữa việt quốc', 7, 24000),
    ('Trà sữa dâu', 7, 25000),
    ('Trà sữa kem trứng nướng', 7, 25000);
    
-- Insert data into customercategory table
INSERT INTO CustomerCategory
    (id, name, expense, discount)
VALUES
    (1, "Normal", 0, 0),
    (2, "Silver", 500000, 2),
    (3, "Golden", 1500000, 5),
    (4, "Platinum", 3000000, 10);
    
INSERT INTO Customer 
	(idRank, phoneNumber, total)
VALUES 
	(1, '0000000000', 0),
	(2, '1111111111', 600000),
    (1, '3333333333', 1800000),
	(4, '4444444444', 50000000);


-- INSERT DATA TO BILL
INSERT INTO Bill 
	(idStaff, dateCheckIn, discount)
VALUES 
	(1, STR_TO_DATE('3-1-2023', '%d-%m-%Y'), DEFAULT),
    (3, STR_TO_DATE('25-2-2023', '%d-%m-%Y'), DEFAULT),
    (2, STR_TO_DATE('23-2-2023', '%d-%m-%Y'), 5),
    (1, STR_TO_DATE('10-3-2023', '%d-%m-%Y'), 8),
    (2, STR_TO_DATE('10-4-2023', '%d-%m-%Y'), 5),
    (1, STR_TO_DATE('10-5-2023', '%d-%m-%Y'), 10),
	(2, STR_TO_DATE('1-5-2023', '%d-%m-%Y'), 5),
    (3, CURDATE(), DEFAULT),
	(1, CURDATE(), 10);
    
INSERT INTO Bill_Customer 
	(idBill, idCustomer)
VALUES 
	(1, 2),
    (2, 3),
    (3, 2),
    (4, 4),
    (5, 1),
    (6, 1),
    (7, 3),
    (8, 4),
    (9, 2);
    
SELECT * 
FROM Bill
WHERE dateCheckIn = STR_TO_DATE('11-6-2023', '%d-%m-%Y');
-- WHERE dateCheckIn = CURDATE(); 

-- DATA FOR BILLINFO
INSERT INTO BillInfo
	(idBill, idProduct, count)
VALUES 
	(1, 1, 3),
	(1, 12, 1),
    (2, 30, 1),
    (2, 5, 5),
    (3, 2, 2),
    (4, 3, 1),
    (4, 9, 12),
    (5, 32, 5),
    (5, 44, 2),
    (6, 12, 12),
    (7, 31, 9),
    (8, 24, 2),
    (8, 6, 3),
    (8, 23, 5),
    (9, 11, 2);
    
INSERT INTO Salary
	(idStaff, salary)
VALUES 
	(1, 50000),
    (2, 30000),
    (3, 25000);

-- SELECT SUM(p.price * bi.count) AS TotalPrice
-- FROM Bill b
-- JOIN BillInfo bi ON b.id = bi.idBill 
-- JOIN Product p ON bi.idProduct = p.id
-- WHERE b.id = 2;


-- FOR StaffManagement
INSERT INTO StaffManagement
	(id, timeCount)
VALUES
	(2, 12);


-- INSERT PROCEDURES AND FUNCTIONS ----------------------------------------------------------------------------
-- FOR ACCOUNT
DELIMITER //
CREATE FUNCTION CheckLogin(username VARCHAR(8), password_p VARCHAR(20)) RETURNS BOOLEAN
DETERMINISTIC
BEGIN
    DECLARE login_status BOOLEAN;
    SET login_status = EXISTS (SELECT username FROM Account WHERE username = username AND password = password_p);
    RETURN login_status;
END;//


DELIMITER //
CREATE PROCEDURE USP_Login(IN p_username VARCHAR(100), IN p_password VARCHAR(100))
BEGIN
    DECLARE user_id INT DEFAULT 0;
    SELECT id INTO user_id FROM Account WHERE username = p_username AND password = p_password;
    SELECT user_id AS id;
END;//

CALL USP_Login('testne', 'test');


DELIMITER //
CREATE PROCEDURE USP_AddAccount(
IN p_username NVARCHAR(100), IN p_password NVARCHAR(1000),
IN p_email NVARCHAR(100), IN p_phoneNumber NVARCHAR(100),
IN p_role NVARCHAR(100))
BEGIN
	INSERT INTO Account
		(userName, password, email, phoneNumber, role)
	VALUES 
		(p_userName, p_password, p_email, p_phoneNumber, p_role);
END//

DELIMITER //
CREATE PROCEDURE USP_DeleteAccount(
IN p_id INT)
BEGIN
	DELETE FROM Account WHERE id = p_id;
END
//

DELIMITER //
CREATE PROCEDURE USP_UpdateAccount(
IN p_username NVARCHAR(100), IN p_password NVARCHAR(1000),
IN p_email NVARCHAR(100), IN p_phoneNumber NVARCHAR(100),
IN p_role NVARCHAR(100), IN p_id INT)
BEGIN
	UPDATE Account
	SET userName = p_username, password = p_password, email = p_email,
    phoneNumber = p_phoneNumber, role = p_role
	WHERE id = P_id;
END//

-- CALL USP_AddAccount('test', '123', 'thien@gmail.com', '0123401', 'staff');
-- CALL USP_UpdateAccount('test', 'testedit', 'editable@gmail.com', '123148712', 'staff', 3);
-- CALL USP_DeleteAccount(3);

-- FOR PRODUCT
DELIMITER //
CREATE PROCEDURE USP_AddProduct(
IN p_name NVARCHAR(100), p_category INT, p_price FLOAT)
BEGIN
	INSERT INTO Product(name, idCategory, price)
	VALUES(p_name, p_category, p_price);
END//


DELIMITER //
CREATE PROCEDURE USP_DeleteProduct(
IN p_id INT)
BEGIN
	DELETE FROM Product WHERE id = p_id;
END//

DELIMITER //
CREATE PROCEDURE USP_UpdateProduct(
IN p_name NVARCHAR(100), p_category INT, 
p_price FLOAT, p_id INT)
BEGIN
	UPDATE Product
    SET name = p_name, idCategory = p_category, price = p_price
    WHERE id = p_id;
END//

-- CALL USP_AddProduct('món mới nè', 2, 100000);
-- CALL USP_UpdateProduct('món edit', 3, 1, 51);
-- CALL USP_DeleteProduct(51);

-- FOR PRODUCT CATEGORY
DELIMITER //
CREATE PROCEDURE USP_AddProductCategory(
IN p_name NVARCHAR(100))
BEGIN
	INSERT INTO ProductCategory (name)
	VALUES (p_name);
END//

DELIMITER //
CREATE PROCEDURE USP_DeleteProductCategory(
IN p_id INT)
BEGIN
	DELETE FROM ProductCategory WHERE id = p_id;
END//

-- FOR BILL
DELIMITER //
CREATE PROCEDURE USP_AddBill(
IN p_idStaff INT, p_dateCheckIn DATE, p_discount INT)
BEGIN
	INSERT INTO Bill(idStaff, dateCheckIn, discount)
    VALUES (p_idStaff, p_dateCheckIn, p_discount);
END//

DELIMITER //
CREATE PROCEDURE USP_AddBillInfo(
IN p_idBill INT, p_idProduct INT, p_count INT)
BEGIN
	INSERT INTO BillInfo(idBill, idProduct, count)
    VALUES (p_idBill, p_idProduct, p_count);
END//

DELIMITER //
CREATE PROCEDURE USP_CalculateUpdateBillTotal(
IN id_bill INT)
BEGIN
	DECLARE v_total_price FLOAT;

	SELECT SUM(p.price * bi.count) INTO v_total_price
	FROM Bill b
	JOIN BillInfo bi ON b.id = bi.idBill 
	JOIN Product p ON bi.idProduct = p.id
	WHERE b.id = id_bill;
    
    UPDATE Bill
    SET totalPrice = v_total_price * (100 - discount)/100 
    WHERE id = id_bill;
    
    SELECT v_total_price as Total_Bill_Price;
END//

DELIMITER //
CREATE PROCEDURE USP_CalculateBill(
IN numBill INT)
BEGIN
	DECLARE i INT DEFAULT 1;
    
	WHILE i <= numBill DO
		CALL USP_CalculateUpdateBillTotal(i);
		SET i = i + 1;
	END WHILE;
END//
DELIMITER ;


CALL USP_CalculateBill(10);

DELIMITER ;

SELECT DATE_FORMAT(DateCheckIn, '%M') AS `Month`,
       SUM(BillInfo.count) AS Products
FROM Bill
JOIN BillInfo ON BillInfo.idBill = Bill.id
GROUP BY DATE_FORMAT(DateCheckIn, '%M')
ORDER BY DateCheckIn DESC LIMIT 12;

-- SELECT QUARTER(b.dateCheckIn) AS Quarter,
--        SUM(b.totalPrice) AS TotalPrice,
--        SUM(bi.count) AS ProductCount
-- FROM Bill b
-- JOIN BillInfo bi ON b.id = bi.idBill
-- GROUP BY QUARTER(b.dateCheckIn) ORDER BY b.dateCheckIn DESC;


-- SELECT p.name as ProductName, SUM(count) as Quantity 
-- FROM BillInfo bi
-- JOIN Product p ON bi.idProduct = p.id 
-- JOIN Bill b on b.id = bi.idBill
-- -- WHERE QUARTER(b.dateCheckIn) = QUARTER(CURRENT_DATE())
-- WHERE MONTH(b.dateCheckIn) = MONTH(CURRENT_DATE())
-- GROUP BY bi.idProduct ORDER BY SUM(count) DESC limit 5;

-- FOR CUSTOMER AND RANK
DELIMITER //
CREATE PROCEDURE USP_AddCustomer(
p_idRank INT, p_phone NVARCHAR(100), p_total INT)
BEGIN
	INSERT INTO Customer (idRank, phoneNumber, total)
    VALUES (p_idRank, p_phone, p_total);
END//

DELIMITER //
CREATE PROCEDURE USP_UpdateCustomerRank()
BEGIN
	UPDATE Customer c
	SET c.idRank = (
		SELECT id
        FROM CustomerCategory
		WHERE expense <= c.total
        ORDER BY expense DESC
        LIMIT 1);
END//


DELIMITER //
CREATE PROCEDURE USP_UpdateCustomerTotal(
IN p_idCustomer INT, p_idBill INT)
BEGIN
	UPDATE Customer c
    SET c.total = c.total + (
		SELECT totalPrice 
        FROM Bill b WHERE b.id = p_idBill)
	WHERE c.id = p_idCustomer; 
END//

DELIMITER //
CREATE PROCEDURE USP_AddBill_Customer(
IN p_idBill INT, p_idCustomer INT)
BEGIN
	INSERT INTO Bill_Customer(idBill, idCustomer)
    VALUES (p_idBill, p_idCustomer);
END//

DELIMITER //
CREATE PROCEDURE USP_UpdateSalary(
IN p_idStaff INT, p_salary INT)
BEGIN
	UPDATE Salary
    SET salary = p_salary
	WHERE idStaff = p_idStaff; 
END//

DELIMITER ;

-- staff, date, total
SELECT a.userName as staffName, b.dateCheckin, b.discount, b.totalPrice
FROM Account a 
JOIN Bill b ON a.id = b.idStaff
JOIN bill_customer bc ON b.id = bc.idBill
WHERE bc.idCustomer = 2;

-- product, quantity, total
SELECT p.name, p.price, bi.count
FROM Product p
JOIN BillInfo bi ON p.id = bi.idProduct
WHERE bi.idBill = 1


