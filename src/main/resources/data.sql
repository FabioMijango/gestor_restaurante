-- Dishes

INSERT INTO dishes (id, description, is_available, created_at, created_by, updated_at, updated_by, name, price, category_id)
VALUES
('cfeb706d-b7a1-4ba6-b6aa-a0909ffcb303','Fresh Atlantic salmon grilled to perfection with aromatic herbs and lemon',True,now(),'test',now(),'test','Grilled Salmon with Herbs','18.99','1'),
('0f685bac-952d-4dbe-a22b-a5473506530b','100% natural orange juice freshly squeezed daily',True,now(),'test',now(),'test','Fresh Squeezed Orange Juice','4.5','2'),
('df7ba63f-0423-40a0-8bc3-60069a483edf','Decadent layered chocolate cake with rich ganache frosting',True,now(),'test',now(),'test','Triple Chocolate Cake','7.99','3'),
('c28046d8-bb00-4945-8ae9-1ef8b606a172','Lightly breaded and fried calamari served with marinara sauce',True,now(),'test',now(),'test','Crispy Calamari Rings','9.25','4'),
('ef2eb2ad-dd3a-4919-aba0-7d206a4c2668','Mixed greens with tomatoes, cucumbers, olives and feta cheese',True,now(),'test',now(),'test','Mediterranean Garden Salad','8.75','5'),
('58199e77-4843-464f-a117-3eed7ed33eaf','Homemade soup with fresh broccoli and sharp cheddar cheese',True,now(),'test',now(),'test','Creamy Broccoli Cheddar Soup','5.5','6'),
('409d8865-792d-4c26-af9d-a80db04733c5','Crispy french fries tossed with truffle oil and parmesan',True,now(),'test',now(),'test','Truffle Parmesan Fries','6.25','7'),
('1b83488b-2d59-4bb6-9fb1-e0923801e7e5','Pan-seared beef medallions with red wine reduction',True,now(),'test',now(),'test','Beef Tenderloin Medallions','26.5','1'),
('c69fe714-2a10-4896-a20d-05183c7ad79b','Refreshing blend of fresh mango, pineapple and coconut milk',True,now(),'test',now(),'test','Tropical Mango Smoothie','5.99','2'),
('e5765a1f-4752-44c3-9bc3-d25465e6fb63','Italian dessert with espresso-soaked ladyfingers and mascarpone cream',True,now(),'test',now(),'test','Classic Tiramisu','8.5','3'),
('692ea314-6576-4e99-9edc-563a827bb2c5','Warm creamy dip with spinach and artichokes, served with tortilla chips',True,now(),'test',now(),'test','Spinach Artichoke Dip','8.99','4'),
('d2f639c9-4aa6-4f52-8e30-8857654a72ec','Grilled chicken over mixed greens with sesame ginger dressing',True,now(),'test',now(),'test','Asian Sesame Chicken Salad','11.5','5'),
('fa0f600f-1b02-4bca-993d-6d39d852b3bd','Caramelized onions in rich beef broth topped with melted gruyere',True,now(),'test',now(),'test','French Onion Soup','6.75','6'),
('e4f469df-bf1f-4d35-ba42-c91d9fd4650d','Seasonal vegetables roasted with garlic and olive oil',True,now(),'test',now(),'test','Roasted Garlic Vegetables','5.75','7'),
('e16925c4-c6fc-40cb-ba72-c42d09f3e660','Tender chicken breast in creamy marsala wine mushroom sauce',True,now(),'test',now(),'test','Chicken Marsala','17.25','1'),
('f292765d-c182-407b-9fb9-e2db7e90d274','Homemade lemonade infused with fresh strawberries',True,now(),'test',now(),'test','Strawberry Lemonade','4.25','2'),
('a6192ede-f6b4-48c2-ba66-f7e4feffd0dd','Creamy classic cheesecake with graham cracker crust',True,now(),'test',now(),'test','New York Cheesecake','7.5','3'),
('ab3c72a9-13a4-4296-b623-27182cb7a095','Crispy wings tossed in tangy barbecue sauce',True,now(),'test',now(),'test','BBQ Chicken Wings','10.99','4'),
('0f6b6ae9-9648-4b3e-aa67-b6850ef2e059','Crisp romaine lettuce with parmesan, croutons and caesar dressing',True,now(),'test',now(),'test','Caesar Salad','8.25','5'),
('12146e11-f8c8-4c0c-b128-5e24bcbd7361','Velvety smooth soup with roasted butternut squash and hint of nutmeg',True,now(),'test',now(),'test','Butternut Squash Soup','5.99','6'),
('a0c003f1-3782-4022-b7b6-7fa956da070e','Creamy homemade macaroni and cheese with three cheese blend',True,now(),'test',now(),'test','Mac and Cheese','6.5','7');


-- Tables

INSERT INTO tables (id, created_at, created_by, updated_at, updated_by, table_number, state_id)
VALUES
('f89a0a24-8c83-45d5-97f6-9a2b3377fb50', now(), 'test', now(), 'test',1, 1),
('b366b1c4-2c3d-4191-bca8-2021c9a1ef18', now(), 'test', now(), 'test',2, 2),
('4e5a04b1-064c-4fdf-9d40-4e64f81e9e09', now(), 'test', now(), 'test',3, 1),
('18224af9-b33a-4c4f-8551-dc50fd6adc9f', now(), 'test', now(), 'test',4, 3),
('3396c9d6-e654-4612-aec6-c5ba47eea833', now(), 'test', now(), 'test',5, 4),
('ad36ba30-2d78-4aa8-81cd-fea660615803', now(), 'test', now(), 'test',6, 2),
('97878e2e-1ca0-432b-8753-566cebac898f', now(), 'test', now(), 'test',7, 1),
('9ea6504d-ed50-4e5b-826e-1b30d3a45532', now(), 'test', now(), 'test',8, 3),
('34d9c894-c6f3-491b-9bd2-0ac79436cb98', now(), 'test', now(), 'test',9, 1),
('8383fa2d-399a-4e83-b616-9ba692a5eed8', now(), 'test', now(), 'test',10, 5);
