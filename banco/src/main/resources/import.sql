/* Creamos algunos usuarios con sus roles */
INSERT INTO `usuario` (NIF, Nombre, Apellidos, password, Anyo_de_Nacimiento, Direccion, Telefono, Email) VALUES ('1','Andres','Prueba1','admin', curdate(), 'calle', '655852545', 'andres@gamil.com');
INSERT INTO `usuario` (NIF, Nombre, Apellidos, password, Anyo_de_Nacimiento, Direccion, Telefono, Email) VALUES ('2','Pepe','Prueba2','user', curdate(), 'avd', '957700698', 'pepe@gmail.com');

INSERT INTO `authorities` (NIF, authority) VALUES (1,'ROLE_USER');
INSERT INTO `authorities` (NIF, authority) VALUES (2,'ROLE_ADMIN');