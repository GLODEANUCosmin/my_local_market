INSERT INTO "Users" ("Name", "Wallet", "Password") VALUES ( 'User1', 1.45, 'Password1');
INSERT INTO "Users" ("Name", "Wallet", "Password") VALUES ( 'User2', 3.45, 'Password2');
INSERT INTO "Users" ("Name", "Wallet", "Password") VALUES ( 'User3', 10, 'Password3');
INSERT INTO "Users" ("Name", "Wallet", "Password") VALUES ( 'User4', 500.0, 'Password4');

INSERT INTO "Markets" ("Name" , "Description") VALUES ('Market1', 'for someone  somewhere anywhere at anytime!');
INSERT INTO "Markets" ("Name" , "Description") VALUES ('Market2', 'for someone  somewhere anywhere sometimes!');
INSERT INTO "Markets" ("Name" , "Description") VALUES ('Market3', 'for someone  somewhere someplace at anytime!');
INSERT INTO "Markets" ("Name" , "Description") VALUES ('Market4', 'for someone  somewhere someplace sometimes!');

INSERT INTO "Providers"("UserID", "CodCUI") VALUES ( 1, 'abc123');
INSERT INTO "Providers"("UserID", "CodCUI") VALUES ( 2, 'def456');
INSERT INTO "Providers"("UserID", "CodCUI") VALUES ( 3, 'ghi789');


INSERT INTO "Stands"("ProviderID", "MarketID", "Name") VALUES ( 1, 1, 'apples@co');
INSERT INTO "Stands"("ProviderID", "MarketID", "Name") VALUES ( 1, 2, 'circus@co');
INSERT INTO "Stands"("ProviderID", "MarketID", "Name") VALUES ( 1, 3, 'specialApples@co');
INSERT INTO "Stands"("ProviderID", "MarketID", "Name") VALUES ( 2, 1, 'pears');
INSERT INTO "Stands"("ProviderID", "MarketID", "Name") VALUES ( 2, 1, 'plums');
INSERT INTO "Stands"("ProviderID", "MarketID", "Name") VALUES ( 2, 3, 'Convenience Store');
INSERT INTO "Stands"("ProviderID", "MarketID", "Name") VALUES ( 3, 1, 'Oil Rig products');
INSERT INTO "Stands"("ProviderID", "MarketID", "Name") VALUES ( 3, 3, 'Cotton Plantation Fruits of Labour');
INSERT INTO "Stands"("ProviderID", "MarketID", "Name") VALUES ( 3, 4, 'Authentic Chinese Clothing');
INSERT INTO "Stands"("ProviderID", "MarketID", "Name") VALUES ( 3, 4, 'SlickBags other Products');

INSERT INTO "Products"("ProviderID", "Price", "Amount", "StandID", "Name", "Type") VALUES ( 1, 2.45, 30, 1, 'Apples', 'Cores');
INSERT INTO "Products"("ProviderID", "Price", "Amount", "StandID", "Name", "Type") VALUES ( 1, 6.99, 12, NULL, 'Golden Apples', 'Cores');
INSERT INTO "Products"("ProviderID", "Price", "Amount", "StandID", "Name", "Type") VALUES ( 1, 200, 5, 3, 'Adam Apples', 'Unknown');
INSERT INTO "Products"("ProviderID", "Price", "Amount", "StandID", "Name", "Type") VALUES ( 1, 5, 100, 2, 'Candy Apples', 'Cores');
INSERT INTO "Products"("ProviderID", "Price", "Amount", "StandID", "Name", "Type") VALUES ( 1, 7.89, 23, 2, 'Large Apples', 'Cores');
INSERT INTO "Products"("ProviderID", "Price", "Amount", "StandID", "Name", "Type") VALUES ( 1, 15.99, 23, 2, 'Pineapples', 'Cores');
INSERT INTO "Products"("ProviderID", "Price", "Amount", "StandID", "Name", "Type") VALUES ( 1, 6.66, 66, 3, 'Alluring Apples', 'Unknown');
INSERT INTO "Products"("ProviderID", "Price", "Amount", "StandID", "Name", "Type") VALUES ( 2, 80, 10, 6, 'Engine Parts', 'NonEdible');
INSERT INTO "Products"("ProviderID", "Price", "Amount", "StandID", "Name", "Type") VALUES ( 2, 16, 30, 4, 'Pears', 'Cores');
INSERT INTO "Products"("ProviderID", "Price", "Amount", "StandID", "Name", "Type") VALUES ( 2, 20, 15, 6, 'Pears', 'Cores');
INSERT INTO "Products"("ProviderID", "Price", "Amount", "StandID", "Name", "Type") VALUES ( 2, 100, 8, 4, 'Pear Trees', 'NonEdible');
INSERT INTO "Products"("ProviderID", "Price", "Amount", "StandID", "Name", "Type") VALUES ( 2, 2, 30, 5, 'Plums', 'Cores');
INSERT INTO "Products"("ProviderID", "Price", "Amount", "StandID", "Name", "Type") VALUES ( 2, 30.5, 18, 6, 'Onions', 'Allium');
INSERT INTO "Products"("ProviderID", "Price", "Amount", "StandID", "Name", "Type") VALUES ( 2, 25.5, 12, 6, 'Carrots', 'Root');
INSERT INTO "Products"("ProviderID", "Price", "Amount", "StandID", "Name", "Type") VALUES ( 2, 29.99, 9, 6, 'Garlic', 'Allium');
INSERT INTO "Products"("ProviderID", "Price", "Amount", "StandID", "Name", "Type") VALUES ( 3, 10, 30, 7, 'Aluminium Pipes', 'NonEdible');
INSERT INTO "Products"("ProviderID", "Price", "Amount", "StandID", "Name", "Type") VALUES ( 3, 5.99, 15, 8, 'Cotton Fabric', 'NonEdible');
INSERT INTO "Products"("ProviderID", "Price", "Amount", "StandID", "Name", "Type") VALUES ( 3, 15.99, 5, 8, 'High Quality Cotton Fabric', 'NonEdible');
INSERT INTO "Products"("ProviderID", "Price", "Amount", "StandID", "Name", "Type") VALUES ( 3, 12.99, 30, 10, 'Beef', 'Meat');
INSERT INTO "Products"("ProviderID", "Price", "Amount", "StandID", "Name", "Type") VALUES ( 3, 10.99, 30, 10, 'Pork', 'Meat');




