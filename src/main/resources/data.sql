 INSERT INTO CLIENTE
 (ID,ACTIVE, CREATED_AT, UPDATED_AT, NOME, TELEFONE, EMAIL, CPF, RG, RUA, CIDADE, ESTADO, CEP, PAIS)
 VALUES
 (RANDOM_UUID(), 1, CURRENT_DATE(), CURRENT_DATE(), 'Gilson', '3333-3333', 'alan@gmail.com', '112.332.222-21', '12980872',
 'RUA X', 'CIDADE X', 'ESTADO Y', '30510-210', 'Brasil'),
 (RANDOM_UUID(), 1, CURRENT_DATE(), CURRENT_DATE(), 'Adamastor', '4444-4444', 'lululu@gmail.com', '112.999.222-21', '33380872',
  'RUA tas', 'CIDADE ahaha', 'ESTADO uhuhuh', '30672-210', 'Bulgária'),
 (RANDOM_UUID(), 1, CURRENT_DATE(), CURRENT_DATE(), 'Adamastor2', '4444-4344', 'luasd@gmail.com', '222.999.222-21', '39980872',
   'RUA tas', 'CIDADE ahaha', 'ESTADO hihihi', '12672-210', 'Rússia'),
 (RANDOM_UUID(), 0, CURRENT_DATE(), CURRENT_DATE(), 'Adamastor3', '4444-4344', 'luasd@gmail.com', '222.999.222-21', '39980872',
   'RUA tas', 'CIDADE ahaha', 'ESTADO hihihi', '12672-210', 'Rússia');