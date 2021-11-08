<<<<<<< HEAD
-- INSERT INTO CLIENTE
-- (ID,ACTIVE, CREATED_AT, UPDATED_AT, NOME, TELEFONE, EMAIL, CPF, RG, RUA, CIDADE, ESTADO, CEP, PAIS)
-- VALUES
-- (RANDOM_UUID(), 1, CURRENT_DATE(), CURRENT_DATE(), 'Gilson', '3333-3333', 'alan@gmail.com', '112.332.222-21', '12980872',
-- 'RUA X', 'CIDADE X', 'ESTADO Y', '30510-210', 'Brasil'),
-- (RANDOM_UUID(), 1, CURRENT_DATE(), CURRENT_DATE(), 'Adamastor', '4444-4444', 'lululu@gmail.com', '112.999.222-21', '33380872',
--  'RUA tas', 'CIDADE ahaha', 'ESTADO uhuhuh', '30672-210', 'Bulgária'),
-- (RANDOM_UUID(), 1, CURRENT_DATE(), CURRENT_DATE(), 'Adamastor2', '4444-4344', 'luasd@gmail.com', '222.999.222-21', '39980872',
--   'RUA tas', 'CIDADE ahaha', 'ESTADO hihihi', '12672-210', 'Rússia'),
-- (RANDOM_UUID(), 0, CURRENT_DATE(), CURRENT_DATE(), 'Adamastor3', '4444-4344', 'luasd@gmail.com', '222.999.222-21', '39980872',
--   'RUA tas', 'CIDADE ahaha', 'ESTADO hihihi', '12672-210', 'Rússia');
--
   SET @Client_UUID1 = RANDOM_UUID();
   SET @Client_UUID2 = RANDOM_UUID();
   SET @Client_UUID3 = RANDOM_UUID();
   SET @Client_UUID4 = RANDOM_UUID();
   SET @Conta_UUID1 = RANDOM_UUID();
   SET @Conta_UUID2 = RANDOM_UUID();
   SET @Conta_UUID3 = RANDOM_UUID();
   SET @Conta_UUID4 = RANDOM_UUID();
   INSERT INTO CLIENTE
    (ID,ACTIVE, CREATED_AT, UPDATED_AT, NOME, TELEFONE, EMAIL, CPF, RG, RUA, CIDADE, ESTADO, CEP, PAIS)
    VALUES
    (@Client_UUID1, 1, CURRENT_DATE(), CURRENT_DATE(), 'Gilson', '3333-3333', 'alan@gmail.com', '112.332.222-21', '12980872',
    'RUA X', 'CIDADE X', 'ESTADO Y', '30510-210', 'Brasil'),
    (@Client_UUID2, 1, CURRENT_DATE(), CURRENT_DATE(), 'Adamastor', '4444-4444', 'lululu@gmail.com', '112.999.222-21', '33380872',
     'RUA tas', 'CIDADE ahaha', 'ESTADO uhuhuh', '30672-210', 'Bulgária'),
    (@Client_UUID3, 1, CURRENT_DATE(), CURRENT_DATE(), 'Adamastor2', '4444-4344', 'luasd@gmail.com', '222.999.222-21', '39980872',
      'RUA tas', 'CIDADE ahaha', 'ESTADO hihihi', '12672-210', 'Rússia'),
    (@Client_UUID4, 0, CURRENT_DATE(), CURRENT_DATE(), 'Adamastor3', '4444-4344', 'luasd@gmail.com', '222.999.222-21', '39980872',
      'RUA tas', 'CIDADE ahaha', 'ESTADO hihihi', '12672-210', 'Rússia');
   INSERT INTO CONTA
   (ID,ACTIVE, CREATED_AT, UPDATED_AT, AGENCIA, NUMERO_DA_CONTA, ID_CLIENT, SALDO )
   VALUES
       (@Conta_UUID1, 1, CURRENT_DATE(), CURRENT_DATE(), 1, 1, @Client_UUID1, 0),
       (@Conta_UUID2, 1, CURRENT_DATE(), CURRENT_DATE(), 200, 1, @Client_UUID2, 0),
       (@Conta_UUID3, 1, CURRENT_DATE(), CURRENT_DATE(), 2, 1, @Client_UUID3, 0),
       (@Conta_UUID4, 0, CURRENT_DATE(), CURRENT_DATE(), 1, 2, @Client_UUID4, 0);
=======
SET @Client_UUID1 = RANDOM_UUID();
SET @Client_UUID2 = RANDOM_UUID();
SET @Client_UUID3 = RANDOM_UUID();
SET @Client_UUID4 = RANDOM_UUID();
SET @Conta_UUID1 = RANDOM_UUID();
SET @Conta_UUID2 = RANDOM_UUID();
SET @Conta_UUID3 = RANDOM_UUID();
SET @Conta_UUID4 = RANDOM_UUID();
INSERT INTO CLIENTE
 (ID,ACTIVE, CREATED_AT, UPDATED_AT, NOME, TELEFONE, EMAIL, CPF, RG, RUA, CIDADE, ESTADO, CEP, PAIS)
 VALUES
 (@Client_UUID1, 1, CURRENT_DATE(), CURRENT_DATE(), 'Gilson', '3333-3333', 'alan@gmail.com', '112.332.222-21', '12980872',
 'RUA X', 'CIDADE X', 'ESTADO Y', '30510-210', 'Brasil'),
 (@Client_UUID2, 1, CURRENT_DATE(), CURRENT_DATE(), 'Adamastor', '4444-4444', 'lululu@gmail.com', '112.999.222-21', '33380872',
  'RUA tas', 'CIDADE ahaha', 'ESTADO uhuhuh', '30672-210', 'Bulgária'),
 (@Client_UUID3, 1, CURRENT_DATE(), CURRENT_DATE(), 'Adamastor2', '4444-4344', 'luasd@gmail.com', '222.999.222-21', '39980872',
   'RUA tas', 'CIDADE ahaha', 'ESTADO hihihi', '12672-210', 'Rússia'),
 (@Client_UUID4, 0, CURRENT_DATE(), CURRENT_DATE(), 'Adamastor3', '4444-4344', 'luasd@gmail.com', '222.999.222-21', '39980872',
   'RUA tas', 'CIDADE ahaha', 'ESTADO hihihi', '12672-210', 'Rússia');
INSERT INTO CONTA
(ID,ACTIVE, CREATED_AT, UPDATED_AT, AGENCIA, NUMERO_DA_CONTA, ID_CLIENT, SALDO )
VALUES
    (@Conta_UUID1, 1, CURRENT_DATE(), CURRENT_DATE(), 1, 1, @Client_UUID1, 0),
    (@Conta_UUID2, 1, CURRENT_DATE(), CURRENT_DATE(), 200, 1, @Client_UUID2, 0),
    (@Conta_UUID3, 1, CURRENT_DATE(), CURRENT_DATE(), 2, 1, @Client_UUID3, 0),
    (@Conta_UUID4, 0, CURRENT_DATE(), CURRENT_DATE(), 1, 2, @Client_UUID4, 0);
>>>>>>> develop
