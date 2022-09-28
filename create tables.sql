CREATE DATABASE F22;
USE F22;

CREATE TABLE toppings
(
	toppingID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    toppingName VARCHAR(30)
);

CREATE TABLE pizzas
(
	pizzaID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    dough VARCHAR(15),
    crustStyle VARCHAR(15),
    sauce VARCHAR(10),
    delivery BOOLEAN,
    price DEC(5,2)
);

CREATE TABLE toppingsOnPizza
(
	toppingOnPizzaID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    pizzaID INT NOT NULL,
    toppingID INT NOT NULL,
    FOREIGN KEY (pizzaID) REFERENCES pizzas(pizzaID),
    FOREIGN KEY (toppingID) REFERENCES toppings(toppingID)
);

INSERT INTO toppings (toppingName) VALUES ('cheese'),('olives'),('pepperoni'),
										('anchovies'),('pineapple');
									
SELECT * FROM toppings;

INSERT INTO pizzas (dough, crustStyle, sauce, delivery, price) VALUES
	('whole wheat','thin','tomato',true,35.99);

SELECT * FROM pizzas;

INSERT INTO toppingsOnPizza (pizzaID, toppingID) VALUES (1,1);
INSERT INTO toppingsOnPizza (pizzaID, toppingID) VALUES (1,2);
INSERT INTO toppingsOnPizza (pizzaID, toppingID) VALUES (1,3);
INSERT INTO toppingsOnPizza (pizzaID, toppingID) VALUES (1,4);
INSERT INTO toppingsOnPizza (pizzaID, toppingID) VALUES (1,5);

SELECT * FROM pizzas
INNER JOIN toppingsOnPizza
INNER JOIN toppings
WHERE pizzas.pizzaID = toppingsOnPizza.pizzaID && 
		toppingsOnPizza.toppingID = toppings.toppingID;