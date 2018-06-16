DROP TABLE OPERACIONDEMANTENIMIENTO;
DROP TABLE FLORESENLOTE;
DROP TABLE LOTE;

DROP TABLE TIPODEOPERACIONDEMANTENIMIENTO;
DROP TABLE TIPODEESTADODELOTE;

DROP TABLE LINEADEVENTA;
DROP TABLE VENTA;

DROP TABLE FACTURA;
DROP TABLE TRANSFERENCIA;

DROP TABLE LINEADEPEDIDO;
DROP TABLE PEDIDOAPROVEEDOR;
DROP TABLE PROVEEDOR;
DROP TABLE PRODUCTO;

DROP TABLE DISPONIBILIDADEMPLEADO;
DROP TABLE VINCULACIONCONLAEMPRESA;
DROP TABLE ROLESENEMPRESA;
DROP TABLE EMPLEADO;

DROP TABLE TIPODEDISPONIBILIDAD;
DROP TABLE TIPODEVINCULACION;
DROP TABLE TIPODEROL;

-- Enum
create table TIPODEROL
(
	IdTipo SMALLINT not null,
	nombreTipo VARCHAR(20) not null,
		PRIMARY KEY(IdTipo)
);

INSERT INTO TIPODEROL
VALUES  (1,'Supervisor'),
        (2,'Administrativo'),
        (3,'Operario'),
        (4,'Dependiente');

-- Enum
create table TIPODEVINCULACION
(
	IdTipo SMALLINT not null,
	NombreTipo VARCHAR(20) not null unique,
		PRIMARY KEY(IdTipo)

);

INSERT INTO TIPODEVINCULACION
VALUES  (1,'Contratado'),
        (2,'Despedido'),
        (3,'EnERTE');

-- Enum
create table TIPODEDISPONIBILIDAD
(
	IdTipo SMALLINT not null,
	NombreTipo VARCHAR(20) not null unique,
		PRIMARY KEY(IdTipo)
);

INSERT INTO TIPODEDISPONIBILIDAD
VALUES  (1,'Vacaciones'),
        (2,'BajaTemporal'),
		(3,'Trabajando');

-- Entity
create table EMPLEADO
(
	Nif VARCHAR(9) not null,
	Nombre VARCHAR(30) not null,
	Password VARCHAR(20) not null,
	FechaInicioEnEmpresa DATE not null,
		PRIMARY KEY(Nif)
);

INSERT INTO EMPLEADO
VALUES  ('11111111A','Pablo','123','2017-03-17'),
        ('22222222B','Maria','456','2017-05-13'),
		('33333333C','Sergio','789','2017-07-11'),
		('44444444D','Iris','111','2017-09-15'),
		('55555555E','Paco','112','2017-11-19'),
		('66666666F','Eva','113','2017-01-21');

-- Association
create table ROLESENEMPRESA
(
	ComienzoEnRol DATE not null,
	Empleado VARCHAR(9) not null,
	Rol SMALLINT not null,
            FOREIGN KEY(Empleado) REFERENCES EMPLEADO(Nif),
            FOREIGN KEY(Rol) REFERENCES TIPODEROL(IdTipo)
);

INSERT INTO ROLESENEMPRESA
VALUES  ('2017-03-18','11111111A',1),
                ('2017-04-18','11111111A',2),
		('2017-05-14','22222222B',2),
		('2017-07-12','33333333C',3),
		('2017-09-16','44444444D',4),
                ('2017-10-18','55555555E',2),
		('2017-11-14','66666666F',2);
		
                

-- Association
create table VINCULACIONCONLAEMPRESA
(
	inicio DATE not null,
	Empleado VARCHAR(9) not null,
	Vinculo SMALLINT not null,
		FOREIGN KEY(Empleado) REFERENCES EMPLEADO(Nif),
		FOREIGN KEY(Vinculo) REFERENCES TIPODEVINCULACION(IdTipo) 
);

INSERT INTO VINCULACIONCONLAEMPRESA
VALUES  ('2017-03-17','11111111A',1),
                ('2017-04-17','11111111A',1),
		('2017-05-13','22222222B',1),
		('2017-07-11','33333333C',1),
		('2017-09-15','44444444D',1),
		('2017-11-20','55555555E',2),
		('2017-01-22','66666666F',3);

-- Association
create table DISPONIBILIDADEMPLEADO
(
	Comienzo DATE not null,
	FinalPrevisto DATE,
	Empleado VARCHAR(9) not null,
	Disponibilidad SMALLINT not null,
		FOREIGN KEY(Empleado) REFERENCES EMPLEADO(Nif),
		FOREIGN KEY(Disponibilidad) REFERENCES TIPODEDISPONIBILIDAD(IdTipo)
);

INSERT INTO DISPONIBILIDADEMPLEADO
VALUES  ('2017-03-17','2018-03-17','11111111A',3),
		('2017-05-13','2018-05-13','22222222B',3),
		('2017-07-11','2017-07-21','33333333C',2),
		('2017-09-15','2017-09-30','44444444D',1);

-- Entity
create table PRODUCTO
(
	Codigo VARCHAR(20) not null,
	Nombre VARCHAR(50) not null,
	Descripcion VARCHAR(200) not null,
	Existencias SMALLINT not null,
	CantidadNecesaria SMALLINT not null, -- funde cantidadNecesaria con CantidadMinimaEnStock
	PrecioDeVenta FLOAT, -- not null para Vendibles => subtipo=="Planta" o "Flor"
	PrecioCompra FLOAT, -- not null para Comprables => subtipo=="Auxiliar" o "Planta"
	DiasParaEntregaDelProveedor SMALLINT, -- not null para Comprables => subtipo=="Auxiliar" o "Planta"
	TipoDeProductoAuxiliar VARCHAR(50), --not null para subtipo=="Auxiliar"
	Subtipo VARCHAR(50) not null, -- "Auxiliar", "Planta", "Flor"
	PlantaDeLaFlor VARCHAR(20), -- not null para subtipo "Flor"
		PRIMARY KEY(Codigo),
		FOREIGN KEY(PlantaDeLaFlor) REFERENCES PRODUCTO(Codigo)
);

INSERT INTO PRODUCTO
VALUES  ('A','Orquideas','Para los aficionados de plantas esta especie de plantas con flores sera de las mas faciles para tener en casa',20,5,15.0,13.0,4,null,'Planta',null),
		('B','Azalea','De las plantas con flores menos exigentes, azalea se sentira bien en tu casa si la pulverizas 1 vez al dia y la alejas de la calefacción',15,7,13.0,10.0,2,null,'Flor','A'),
		('C','Camelia','Es de las plantas con flores que se ven mas bonitas de todas. Sus flores son de color rosa vivo y ademas es resistente al frio',34,2,33.0,23.0,4,'Hogar','Auxiliar',null),
		('D','Crisantemo','Mientras en tu casa haya mucha luz, el crisantemo abrira sus flores. De las plantas con flores perfectas para interior',20,2,15.0,13.0,6,null,'Planta',null),
		('E','Rosa desierto','Aunque la rosa desierto es un arbusto que requiere mucho cuidado, es de las plantas con flores mas originales porque puede florecer hasta varias veces en un verano',12,2,17.0,16.0,4,null,'Flor','D'),
		('G','Flor de pascua','Esta flor, tambien conocida como Nochebuena, es un adorno de Navidad perfecto. De las plantas con flores mas conocidas',21,5,15.0,11.0,2,null,'Planta',null),
		('F','Brezo','Brezo es muy bonito y el mejor ambiente para el es un area sin calefaccion. Es de las plantas con flores que requieren regarse con agua sin cal y con pH reducido',25,8,10.0,9.0,8,null,'Flor','G'),
		('H','Gardenia','Hay una especie de gardenia que florece parecido a jazmin y llena su entorno con un aroma fabuloso. De las plantas con flores que tienes que alejar de las corrientes de aire',43,7,15.0,13.0,4,'Decoracion','Auxiliar',null);

-- Entity
create table PROVEEDOR
(
	Cif VARCHAR(9) not null,
	Nombre VARCHAR(50) not null,
	Telefono VARCHAR(15) not null,
	Email VARCHAR(50) not null,
		PRIMARY KEY(Cif)
);

INSERT INTO PROVEEDOR
VALUES  ('01A','Floristeria Amadeo','589654125','amadeo@gmail.com'),
		('02B','Repartidor Julio','645824578','julio@gmail.com'),
		('03C','Rosario Flores','542854815','flores@gmail.com'),
		('04D','Almacen Tulipan','849764542','almacen@gmail.com'),
		('05E','Sergio Ramos','142758645','ramos@gmail.com');

-- Entity
create table PEDIDOAPROVEEDOR
(
	NumeroDePedido INTEGER not null,
	FechaDeRealizacion DATE not null,
	EstaPendiente VARCHAR(1) not null,
	Proveedor VARCHAR(9) not null,
		PRIMARY KEY(NumeroDePedido),
		FOREIGN KEY(Proveedor) REFERENCES PROVEEDOR(Cif)
);

INSERT INTO PEDIDOAPROVEEDOR
VALUES  (1,'2018-02-12','1','01A'),
		(2,'2018-04-13','0','01A'),
		(3,'2018-01-26','1','01A'),
		(4,'2018-01-17','1','03C'),
		(5,'2018-02-13','1','01A'),
		(6,'2018-03-05','0','05E'),
		(7,'2018-05-05','1','05E');

-- Entity
create table LINEADEPEDIDO
(
	Cantidad SMALLINT not null,
	Pedido INTEGER not null,
	Producto VARCHAR(20) not null,
		FOREIGN KEY(Pedido) REFERENCES PEDIDOAPROVEEDOR(NumeroDePedido),
		FOREIGN KEY(Producto) REFERENCES PRODUCTO(Codigo)
);

INSERT INTO LINEADEPEDIDO
VALUES  (4,2,'A'),
		(2,3,'A'),
		(8,2,'B'),
		(3,2,'H'),
		(9,2,'C'),
		(4,3,'A');

-- Entity 
create table TRANSFERENCIA
(
	Id INTEGER not null,
	Importe FLOAT not null,
	FechaDeRealizacion DATE not null,
	Comprobada VARCHAR(1) not null,
	Administrativo VARCHAR(9) not null,
		PRIMARY KEY(Id),
		FOREIGN KEY(Administrativo) REFERENCES EMPLEADO(Nif)
);

INSERT INTO TRANSFERENCIA
VALUES  (1,10.0,'2018-05-05','1','22222222B'),
		(2,25.0,'2018-06-05','0','22222222B');

-- Entity
create table FACTURA
(
	Id INTEGER not null,
	FechaDeEmision DATE not null,
	Importe FLOAT not null,
	CuentaBancaria VARCHAR(34) not null,
	Pedido INTEGER not null,
	EnTransferencia INTEGER, --null si no se ha realizado transferencia
		PRIMARY KEY(Id),
		FOREIGN KEY(EnTransferencia) REFERENCES TRANSFERENCIA(Id)
);

INSERT INTO FACTURA
VALUES  (1,'2018-05-05',10.0,'55214578547836521',1,1),
        (2,'2018-05-05',11.0,'55214578547836522',4,1),
        (3,'2018-06-05',12.0,'55214578547836523',2,1);

-- Entity
create table VENTA
(
	IdDeVenta INTEGER not null,
	FechaDeVenta DATE not null,
	Dependiente VARCHAR(9) not null,
		PRIMARY KEY(IdDeVenta),
		FOREIGN KEY(Dependiente) REFERENCES EMPLEADO(Nif)

);

INSERT INTO VENTA
VALUES  (1,'2018-04-15','44444444D'),
		(2,'2018-05-16','44444444D'),
		(3,'2018-06-24','44444444D');

-- Entity
create table LINEADEVENTA
(
	Cantidad SMALLINT not null,
	Venta INTEGER not null,
	Producto VARCHAR(20) not null,
		FOREIGN KEY(Venta) REFERENCES VENTA(IdDeVenta),
		FOREIGN KEY(Producto) REFERENCES PRODUCTO(Codigo)
);

INSERT INTO LINEADEVENTA
VALUES  (10,1,'A'),
		(5,2,'C'),
		(6,3,'E');

-- Enum
create table TIPODEESTADODELOTE
(
	IdTipo SMALLINT not null,
	nombreTipo VARCHAR(20) not null,
		PRIMARY KEY(IdTipo)
);

INSERT INTO TIPODEESTADODELOTE
VALUES  (1,'PendienteDePlantar'),
        (2,'Plantado'),
        (3,'EnProduccion'),
        (4,'FinDeVidaUtil'),
        (5,'Eliminado');

-- Enum
create table TIPODEOPERACIONDEMANTENIMIENTO
(
	IdTipo SMALLINT not null,
	nombreTipo VARCHAR(20) not null,
		PRIMARY KEY(IdTipo)
);

INSERT INTO TIPODEOPERACIONDEMANTENIMIENTO
VALUES  (1,'Plantacion'),
        (2,'Poda'),
        (3,'Abonado'),
        (4,'Riego');

-- Entity
create table LOTE
(
	Id INTEGER not null,
	Cantidad SMALLINT not null,
	FechaDeCreacion DATE not null,
	Estado SMALLINT not null,
	Planta VARCHAR(20) not null,
		PRIMARY KEY(Id),
		FOREIGN KEY(Estado) REFERENCES TIPODEESTADODELOTE(IdTipo),
		FOREIGN KEY(Planta) REFERENCES PRODUCTO(Codigo)
);

INSERT INTO LOTE
VALUES  (1,5,'2018-01-01',1,'A'),
		(2,7,'2018-02-23',2,'A'),
		(3,12,'2018-03-12',3,'D'),
		(4,9,'2018-04-25',4,'D'),
		(5,2,'2018-06-17',5,'G');

-- Association
create table FLORESENLOTE
(
	Cantidad SMALLINT not null,
	Flor VARCHAR(20) not null,
	Lote INTEGER not null,
		FOREIGN KEY(Flor) REFERENCES PRODUCTO(Codigo),
		FOREIGN KEY(Lote) REFERENCES LOTE(Id)
	
);

INSERT INTO FLORESENLOTE
VALUES  (7,'B',1),
		(12,'E',2),
		(8,'F',3);

-- Entity
create table OPERACIONDEMANTENIMIENTO
(
	FechaDeRealizacion DATE not null,
	Tipo SMALLINT not null,
	Operario VARCHAR(9) not null,
		PRIMARY KEY(FechaDeRealizacion,Tipo),
		FOREIGN KEY(Tipo) REFERENCES TIPODEOPERACIONDEMANTENIMIENTO(IdTipo),
		FOREIGN KEY(Operario) REFERENCES EMPLEADO(Nif)
);

INSERT INTO OPERACIONDEMANTENIMIENTO
VALUES  ('2018-01-01',1,'33333333C'),
		('2018-02-12',2,'33333333C'),
		('2018-03-21',3,'33333333C'),
		('2018-04-27',4,'33333333C');