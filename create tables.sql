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

