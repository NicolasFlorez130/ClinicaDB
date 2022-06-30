package company.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity(name = "Turnos")
@Table(name = "Turnos")
public class Turno {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private LocalDate fecha;

    @ManyToOne
    @JoinColumn
    private Paciente paciente;

    @ManyToOne
    @JoinColumn
    private Odontologo odontologo;
}
