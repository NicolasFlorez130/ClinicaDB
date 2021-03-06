package company.entity;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PacienteDTO {

    private Integer dni;
    private String nombre;
    private String apellido;
    private LocalDate fechaDeIngreso;
    private DomicilioDTO domicilio;
}
