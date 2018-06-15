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
	(3, 'Trabajando');

-- Entity
create table EMPLEADO
(
	Nif VARCHAR(9) not null,
	Nombre VARCHAR(30) not null,
	Password VARCHAR(20) not null,
	FechaInicioEnEmpresa DATE not null,
		PRIMARY KEY(Nif)
);

-- Association
create table ROLESENEMPRESA
(
	ComienzoEnRol DATE not null,
	Empleado VARCHAR(9) not null,
	Rol SMALLINT not null,
            FOREIGN KEY(Empleado) REFERENCES EMPLEADO(Nif),
            FOREIGN KEY(Rol) REFERENCES TIPODEROL(IdTipo)
);

-- Association
create table VINCULACIONCONLAEMPRESA
(
	inicio DATE not null,
	Empleado VARCHAR(9) not null,
	Vinculo SMALLINT not null,
		FOREIGN KEY(Empleado) REFERENCES EMPLEADO(Nif),
		FOREIGN KEY(Vinculo) REFERENCES TIPODEVINCULACION(IdTipo)
);

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

-- Entity
create table PROVEEDOR
(
	Cif VARCHAR(9) not null,
	Nombre VARCHAR(50) not null,
	Telefono VARCHAR(15) not null,
	Email VARCHAR(50) not null,
		PRIMARY KEY(Cif)
);

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

-- Entity
create table LINEADEPEDIDO
(
	Cantidad SMALLINT not null,
	Pedido INTEGER not null,
	Producto VARCHAR(20) not null,
		FOREIGN KEY(Pedido) REFERENCES PEDIDOAPROVEEDOR(NumeroDePedido),
		FOREIGN KEY(Producto) REFERENCES PRODUCTO(Codigo)
);

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

-- Entity
create table VENTA
(
	IdDeVenta INTEGER not null,
	FechaDeVenta DATE not null,
	Dependiente VARCHAR(9) not null,
		PRIMARY KEY(IdDeVenta),
		FOREIGN KEY(Dependiente) REFERENCES EMPLEADO(Nif)

);

-- Entity
create table LINEADEVENTA
(
	Cantidad SMALLINT not null,
	Venta INTEGER not null,
	Producto VARCHAR(20) not null,
		FOREIGN KEY(Venta) REFERENCES VENTA(IdDeVenta),
		FOREIGN KEY(Producto) REFERENCES PRODUCTO(Codigo)
);

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

-- Association
create table FLORESENLOTE
(
	Cantidad SMALLINT not null,
	Flor VARCHAR(20) not null,
	Lote INTEGER not null,
		FOREIGN KEY(Flor) REFERENCES PRODUCTO(Codigo),
		FOREIGN KEY(Lote) REFERENCES LOTE(Id)

);

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
