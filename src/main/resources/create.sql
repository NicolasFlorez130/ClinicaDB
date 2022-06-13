
CREATE TABLE IF NOT EXISTS Domicilio (
    Id int AUTO_INCREMENT,
    Calle varchar(255),
    Numero varchar(255),
    Localidad varchar(255),
    Provincia varchar(255),
    PRIMARY KEY (Id)
);

CREATE TABLE IF NOT EXISTS Paciente (
    Id int AUTO_INCREMENT,
    Nombre varchar(255),
    Apellido varchar(255),
    Dni varchar(255),
    FechaDeIngreso varchar(255),
    Domicilio_id int,
    PRIMARY KEY (Id),
    CONSTRAINT Paciente_Domicilio FOREIGN KEY (Domicilio_id) REFERENCES Domicilio(id)
);

CREATE TABLE IF NOT EXISTS Odontologos (
    Id int AUTO_INCREMENT,
    Matricula varchar(255),
    Nombre varchar(255),
    Apellido varchar(255),
    PRIMARY KEY (Id)
);

//INSERT INTO Odontologos (Matricula, Nombre, Apellido)
//VALUES ('321654', 'Pedro', 'Perez');


//INSERT INTO Domicilio (Calle, Numero, Localidad)
//VALUES ('129', '81', 'Ken');

/*INSERT INTO Paciente (Nombre, Apellido, Dni, FechaDeIngreso, Domicilio_id)
VALUES ('Federico', 'Monsalve', '111', 'Marzo', 1);

INSERT INTO Paciente (Nombre, Apellido, Dni, FechaDeIngreso, Domicilio_id)
VALUES ('Federico', 'Monsalve', '222', 'Marzo', 2);

INSERT INTO Paciente (Nombre, Apellido, Dni, FechaDeIngreso, Domicilio_id)
VALUES ('Federico', 'Monsalve', '333', 'Marzo', 3);

INSERT INTO Paciente (Nombre, Apellido, Dni, FechaDeIngreso, Domicilio_id)
VALUES ('Federico', 'Monsalve', '444', 'Marzo', 1);

INSERT INTO Paciente (Nombre, Apellido, Dni, FechaDeIngreso, Domicilio_id)
VALUES ('Federico', 'Monsalve', '555', 'Marzo', 2);

INSERT INTO Paciente (Nombre, Apellido, Dni, FechaDeIngreso, Domicilio_id)
VALUES ('Federico', 'Monsalve', '666', 'Marzo', 3);

INSERT INTO Paciente (Nombre, Apellido, Dni, FechaDeIngreso, Domicilio_id)
VALUES ('Federico', 'Monsalve', '777', 'Marzo', 1);

INSERT INTO Paciente (Nombre, Apellido, Dni, FechaDeIngreso, Domicilio_id)
VALUES ('Federico', 'Monsalve', '888', 'Marzo', 2);

INSERT INTO Paciente (Nombre, Apellido, Dni, FechaDeIngreso, Domicilio_id)
VALUES ('Federico', 'Monsalve', '999', 'Marzo', 3);
*/