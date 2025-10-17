-- Dish Categories

INSERT INTO dish_categories (id, category)
VALUES (1, 'Main Dish'),
       (2, 'Drink'),
       (3, 'Dessert'),
       (4, 'Appetizer'),
       (5, 'Salad'),
       (6, 'Soup'),
       (7, 'Side Dish');

-- Table States

INSERT INTO table_states (id, state)
VALUES (1, 'Available'),
       (2, 'Reserved'),
       (3, 'Occupied'),
       (4, 'Cleaning'),
       (5, 'Out of Service');

-- Order States

INSERT INTO order_states (id, state)
VALUES (1, 'Pending'),
       (2, 'In Preparation'),
       (3, 'Ready'),
       (4, 'Served'),
       (5, 'Cancelled'),
       (6, 'Paid');

-- User Roles

INSERT INTO user_roles
VALUES (1, 'ADMIN'),
       (2, 'WAITER'),
       (3, 'CHEF');
