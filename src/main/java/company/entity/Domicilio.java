package company.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "Domicilios")
public class Domicilio {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String calle;
    private String numero;
    private String localidad;
    private String provincia;

    @OneToMany(mappedBy = "domicilio", fetch = FetchType.LAZY)
    @JsonIgnore
    @ToString.Exclude
    private List<Paciente> pacientes;
}
