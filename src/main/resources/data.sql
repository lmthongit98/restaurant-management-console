CREATE TABLE IF NOT EXISTS menus (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    price DOUBLE NOT NULL,
    image VARCHAR(255) NOT NULL,
    additionalDetails VARCHAR(255) NOT NULL,
    createdDate TIMESTAMP NOT NULL,
    updatedDate TIMESTAMP NOT NULL
    );

CREATE TABLE IF NOT EXISTS bills (
     id UUID PRIMARY KEY,
     createdDate TIMESTAMP NOT NULL,
     updatedDate TIMESTAMP NOT NULL,
     isPaid BOOLEAN NOT NULL
    );

CREATE TABLE IF NOT EXISTS order_items (
 id UUID PRIMARY KEY,
 bill_id UUID,
 menu_id UUID,
 quantity INT NOT NULL,
 price DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (bill_id) REFERENCES bills(id),
    FOREIGN KEY (menu_id) REFERENCES menus(id)
);


-- Insert mock data into menus table with UUID generation
INSERT INTO menus (id, name, description, price, image, additionalDetails, createdDate, updatedDate) VALUES
                                                                                                (RANDOM_UUID(), 'Pizza Margherita', 'Classic pizza with tomatoes and mozzarella cheese', 8.99, 'image1.jpg', 'Pizza', '2023-07-01 12:00:00', '2023-07-01 12:00:00'),
                                                                                                (RANDOM_UUID(), 'Spaghetti Carbonara', 'Pasta with eggs, cheese, pancetta, and pepper', 10.50, 'image2.jpg', 'Pasta', '2023-07-02 13:00:00', '2023-07-02 13:00:00'),
                                                                                                (RANDOM_UUID(), 'Caesar Salad', 'Salad with romaine lettuce, croutons, and Caesar dressing', 7.75, 'image3.jpg', 'Salad', '2023-07-03 14:00:00', '2023-07-03 14:00:00'),
                                                                                                (RANDOM_UUID(), 'Cheeseburger', 'Burger with cheese, lettuce, tomato, and onions', 9.25, 'image4.jpg', 'Burger', '2023-07-04 15:00:00', '2023-07-04 15:00:00'),
                                                                                                (RANDOM_UUID(), 'Chocolate Cake', 'Rich chocolate cake with chocolate frosting', 6.50, 'image5.jpg', 'Dessert', '2023-07-05 16:00:00', '2023-07-05 16:00:00');
