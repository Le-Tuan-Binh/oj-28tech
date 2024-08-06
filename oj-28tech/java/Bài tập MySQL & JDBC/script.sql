USE northwind

-- 1. Truy vấn các thành phố khác nhau xuất hiện trong bảng Customers

SELECT DISTINCT Address
FROM customers

-- 2.Liệt kê những khách hàng sống tại Berlin trong bảng Customers

SELECT *
FROM customers as cus
WHERE cus.City LIKE "Berlin"

-- 3.Liệt kê những khách hàng không sống tại Berlin trong bảng Customers

SELECT *
FROM customers as cus
WHERE cus.City NOT LIKE "Berlin"


-- 4.Liệt kê những khách hàng sống tại thành phố London và có mã khách hàng là số chẵn

SELECT *
FROM customers AS cus
WHERE cus.City = 'London' AND cus.CustomerID % 2 = 0;


-- 5.Liệt kê những khách hàng có tên bắt đầu bằng chữ A

SELECT *
FROM customers AS cus
WHERE cus.CustomerName LIKE 'A%';


-- 6. Liệt kê những khách hàng có tên bắt đầu bằng chữ A, B hoặc D

SELECT *
FROM customers AS cus
WHERE cus.CustomerName REGEXP '^[ABD]';


SELECT *
FROM customers AS cus
WHERE cus.CustomerName LIKE 'A%'
   OR cus.CustomerName LIKE 'B%'
   OR cus.CustomerName LIKE 'D%';

-- 7. Liệt kê những khách hàng có tên bắt đầu bằng chữ A và kết thúc bằng chữ e

SELECT *
FROM customers AS cus
WHERE cus.CustomerName LIKE 'A%e';

-- 8. Liệt kê những khách hàng có tên có độ dài tối thiểu là 25

SELECT *
FROM customers AS cus
WHERE LENGTH(cus.CustomerName) >= 25;


-- 9. Liệt kê những khách hàng có tên kết thúc bởi “sen”

SELECT *
FROM customers AS cus
WHERE cus.CustomerName LIKE '%sen';

-- 10. Liệt kê những khách hàng có tên bắt đầu bởi “cen”

SELECT *
FROM customers AS cus
WHERE cus.CustomerName LIKE 'cen%';

-- 11. Liệt kê những khách hàng có tên có thứ tự từ điển lớn hơn “Harani Canes”

SELECT *
FROM customers AS cus
WHERE cus.CustomerName > 'Harani Canes';


-- 12.Liệt kê nhân viên sinh năm 1968

SELECT *
FROM employees
WHERE YEAR(BirthDate) = 1968;


-- 13. Liệt kê nhân viên sinh vào tháng 2

SELECT *
FROM employees
WHERE MONTH(BirthDate) = 2;

-- 14. Liệt kê nhân viên sinh ngày mồng 2

SELECT *
FROM employees
WHERE DAY(BirthDate) = 2;


-- 15.Liệt kê khách hàng trong tên chứa cụm “ar”

SELECT *
FROM customers
WHERE CustomerName LIKE '%ar%';


-- 16.Liệt kê khách hàng có tên bắt đầu bằng “B” và kết thúc bằng “e”

SELECT *
FROM customers
WHERE CustomerName LIKE 'B%e';


-- 17. Liệt kê khách hàng có chữ cái thứ 2 trong tên là chữ “r”

SELECT *
FROM customers
WHERE CustomerName LIKE '_r%';


-- 18. Liệt kê khách hàng có chữ cái thứ 3 từ cuối về trong tên là chữ “h”

SELECT *
FROM customers
WHERE CustomerName LIKE '%h__';

-- 19. Liệt kê khách hàng có tên bắt đầu bằng 1 trong các chữ cái ‘a’, ‘b’, ‘h’, ‘d’

SELECT *
FROM customers
WHERE CustomerName REGEXP '^[abhd]';

-- 20. Liệt kê khách hàng có tên kết thúc bằng 1 trong các chữ cái ‘a’, ‘b’, ‘h’, ‘d’

SELECT *
FROM customers
WHERE CustomerName REGEXP '[abhd]$';

-- 21.Liệt kê những khách hàng và thông tin đơn hàng đã đặt hàng vào tháng 7 năm 1996

SELECT cus.*, ord.*
FROM customers AS cus
JOIN orders AS ord ON cus.CustomerID = ord.CustomerID
WHERE MONTH(ord.OrderDate) = 7
  AND YEAR(ord.OrderDate) = 1996;
  
SELECT cus.*, ord.*
FROM customers AS cus
JOIN (
    SELECT CustomerID, OrderID, OrderDate
    FROM orders
    WHERE MONTH(OrderDate) = 7 AND YEAR(OrderDate) = 1996
) AS ord ON cus.CustomerID = ord.CustomerID;

-- Sử Dụng CTE (Common Table Expression) và LATERAL JOIN 
WITH JulyOrders AS (
    SELECT CustomerID, OrderID, OrderDate
    FROM orders
    WHERE MONTH(OrderDate) = 7 AND YEAR(OrderDate) = 1996
)
SELECT cus.*, ord.*
FROM customers AS cus
JOIN JulyOrders AS ord ON cus.CustomerID = ord.CustomerID;

-- 22. Liệt kê những khách hàng và thông tin đơn hàng đã đặt hàng vào tháng 12 năm 1996 và sống tại Mỹ

SELECT cus.*, ord.*
FROM customers AS cus
JOIN orders AS ord ON cus.CustomerID = ord.CustomerID
WHERE MONTH(ord.OrderDate) = 12
  AND YEAR(ord.OrderDate) = 1996
  AND cus.Country = 'USA';

-- Sử Dụng CTE (Common Table Expression) và LATERAL JOIN 
WITH DecemberOrders AS (
    SELECT OrderID, CustomerID, OrderDate
    FROM orders
    WHERE EXTRACT(MONTH FROM OrderDate) = 12
      AND EXTRACT(YEAR FROM OrderDate) = 1996
)
SELECT cus.*, ord.*
FROM customers AS cus
JOIN DecemberOrders AS ord ON cus.CustomerID = ord.CustomerID
WHERE cus.Country = 'USA';


-- 23. Liệt kê những khách hàng và thông tin đơn hàng đã đặt hàng sau ngày 09-10-1996 và sống tại London

SELECT cus.*, ord.*
FROM customers AS cus
JOIN orders AS ord ON cus.CustomerID = ord.CustomerID
WHERE ord.OrderDate > '1996-10-09'
  AND cus.City = 'London';


-- 24. Liệt kê những khách hàng đã đặt hàng vào tháng 10 năm 1996 và được ship bởi shipper có ID là 3

SELECT cus.*
FROM customers AS cus
JOIN orders AS ord ON cus.CustomerID = ord.CustomerID
WHERE MONTH(ord.OrderDate) = 10
  AND YEAR(ord.OrderDate) = 1996
  AND ord.ShipperID = 3;


-- 25. Liệt kê những khách hàng và thông tin đơn hàng được xử lý bởi nhân viên có ID là 4

SELECT cus.*, ord.*
FROM customers AS cus
JOIN orders AS ord ON cus.CustomerID = ord.CustomerID
WHERE ord.EmployeeID = 4;


-- 26.Liệt kê thông tin những đơn hàng được xử lý bởi nhân viên có FirstName là “Nancy”

SELECT ord.*
FROM orders AS ord
JOIN employees AS emp ON ord.EmployeeID = emp.EmployeeID
WHERE emp.FirstName = 'Nancy';

-- 27.Liệt kê những sản phẩm được mua trong đơn hàng có ID là 10249

SELECT p.ProductID, p.ProductName, od.Quantity
FROM orderdetails AS od
JOIN products AS p ON od.ProductID = p.ProductID
WHERE od.OrderID = 10249;
