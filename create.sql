CREATE TABLE IF NOT EXISTS Pacientes (
    Nombre varchar(255),
    Apellido varchar(255),
    Dni varchar(255),
    FechaDeIngreso DATE,
    Domicilio_id int,
    PRIMARY KEY (Dni),
    CONSTRAINT Paciente_domicilio FOREIGN KEY (Domicilio_id) REFERENCES Domicilio(id)
);

CREATE TABLE IF NOT EXISTS Domicilio (
    Id int AUTO_INCREMENT,
    Calle varchar(255),
    Numero varchar(255),
    Localidad varchar(255),
    Provincia varchar(255),
    PRIMARY KEY (Id)
);

CREATE TABLE IF NOT EXISTS Odontologos (
    Matricula varchar(255),
    Nombre varchar(255),
    Apellido varchar(255),
    PRIMARY KEY (Matricula)
);

/*CREATE TABLE IF NOT EXISTS Turnos (
    Id int AUTO_INCREMENT,
    PacienteDni VARCHAR(255),
    OdontologoMatricula VARCHAR(255),
    Fecha DATE;
)*/