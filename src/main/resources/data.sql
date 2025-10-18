-- Dishes

INSERT INTO dishes (id, description, is_available, name, price, category_id)
VALUES
('30f60d00-5f20-4642-8ff0-a0946f9f3f3c', 'Classic Margherita pizza with fresh tomatoes, mozzarella cheese, and basil.', true, 'Margherita Pizza', 8.99, 1),
('6985f40e-81e0-4f13-93ba-e1879b634929', 'Pepperoni pizza topped with spicy pepperoni slices and mozzarella cheese.', true, 'Pepperoni Pizza', 9.99, 1),
('a3d7930d-7fcd-480e-a029-3aaa4fed278b', 'Caesar salad with romaine lettuce, croutons, Parmesan cheese, and Caesar dressing.', true, 'Caesar Salad', 6.49, 5),
('ed42e57e-63e7-487f-9350-f074f46902d8', 'Grilled chicken sandwich with lettuce, tomato, and mayo on a toasted bun.', true, 'Grilled Chicken Sandwich', 7.99, 7),
('f5958ddb-e471-4e23-a849-f16dc2a96c67', 'Spaghetti Bolognese with rich meat sauce and Parmesan cheese.', true, 'Spaghetti Bolognese', 10.49, 4),
('54415b6b-c2d0-44cd-902c-a1b3ff6b6037', 'Chocolate lava cake with a molten chocolate center served with vanilla ice cream.', true, 'Chocolate Lava Cake', 5.99, 3),
('d998742d-a57f-44a0-b164-d46ffa19954c', 'Fresh garden salad with mixed greens, cherry tomatoes, cucumbers, and vinaigrette.', true, 'Garden Salad', 5.49, 5),
('63bbd3ad-6950-428b-9027-3d2ec7fdf04a', 'BBQ ribs glazed with tangy barbecue sauce served with coleslaw.', true, 'BBQ Ribs', 12.99, 7),
('e0f243d9-cbce-4b32-afdd-19eff6067575', 'Vegetarian wrap with hummus, grilled vegetables, and fresh greens.', true, 'Vegetarian Wrap', 7.49, 4),
('10c89430-73cf-4a5b-9b3a-e5568dd26280', 'Classic cheeseburger with lettuce, tomato, pickles, and cheddar cheese.', true, 'Cheeseburger', 8.49, 1);


-- Tables

INSERT INTO tables (id, table_number, state_id)
VALUES
('f89a0a24-8c83-45d5-97f6-9a2b3377fb50', 1, 1),
('b366b1c4-2c3d-4191-bca8-2021c9a1ef18', 2, 2),
('4e5a04b1-064c-4fdf-9d40-4e64f81e9e09', 3, 1),
('18224af9-b33a-4c4f-8551-dc50fd6adc9f', 4, 3),
('3396c9d6-e654-4612-aec6-c5ba47eea833', 5, 4),
('ad36ba30-2d78-4aa8-81cd-fea660615803', 6, 2),
('97878e2e-1ca0-432b-8753-566cebac898f', 7, 1),
('9ea6504d-ed50-4e5b-826e-1b30d3a45532', 8, 3),
('34d9c894-c6f3-491b-9bd2-0ac79436cb98', 9, 1),
('8383fa2d-399a-4e83-b616-9ba692a5eed8', 10, 5);
