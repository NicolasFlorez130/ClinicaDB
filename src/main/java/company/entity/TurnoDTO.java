package company.entity;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TurnoDTO {

    private Integer id;
    private LocalDate fecha;
    private Paciente paciente;
    private Odontologo odontologo;
}
