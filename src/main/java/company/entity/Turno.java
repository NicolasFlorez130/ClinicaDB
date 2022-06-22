package company.entity;

import java.time.LocalDate;

public class Turno {

    private Integer id;
    private String pacienteDni;
    private String odontologoMatricula;
    private LocalDate fecha;

    public Turno(String dni, String matricula, LocalDate fecha) {
        setPacienteDni(dni);
        setOdontologoMatricula(matricula);
        setFecha(fecha);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPacienteDni() {
        return pacienteDni;
    }

    public void setPacienteDni(String pacienteDni) {
        this.pacienteDni = pacienteDni;
    }

    public String getOdontologoMatricula() {
        return odontologoMatricula;
    }

    public void setOdontologoMatricula(String odontologoMatricula) {
        this.odontologoMatricula = odontologoMatricula;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}
