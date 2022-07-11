package company.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import company.entity.*;
import company.service.OdontologoService;
import company.service.PacienteService;
import company.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/turnos")
public class TurnosControllers {

    @Autowired
    private ObjectMapper mapper;

    private final TurnoService turnoService;

    public TurnosControllers(TurnoService turnoService, PacienteService pacienteService, OdontologoService odontologoService){
        this.turnoService = turnoService;
    }

    @GetMapping("/list")
    public ResponseEntity<Object> listOdontologos() {
        List<Turno> turnos = turnoService.obtenerTodos();
        if (turnos.size() == 0) {
            return new ResponseEntity<>("No hay turnos para mostrar.", HttpStatus.OK);
        } else {
            List<TurnoDTO> turnoDTOS = new ArrayList<>();
            for (Turno turno : turnos) {
                turnoDTOS.add(mapper.convertValue(turno, TurnoDTO.class));
            }
            return new ResponseEntity<>(turnoDTOS, HttpStatus.OK);
        }
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<Object> searchTurnos(@PathVariable int id) {
        Turno turno = turnoService.buscar(id);
        if (turno == null) {
            return new ResponseEntity<>("No existe un turno con ID " + id + ".", HttpStatus.OK);
        } else {
            return new ResponseEntity<>(mapper.convertValue(turno, TurnoDTO.class), HttpStatus.OK);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createTurnos(@RequestBody TurnoDTO turno) {
        if(turno.getFecha() == null){
            return new ResponseEntity<>("Ingrese una fecha valida.", HttpStatus.BAD_REQUEST);
        }

        return switch (turnoService.agregar(mapper.convertValue(turno, Turno.class))) {
            case 0 -> new ResponseEntity<>("El paciente y/o odontologo no existe.", HttpStatus.BAD_REQUEST);
            case 1 -> new ResponseEntity<>("Turno agregado con exito.", HttpStatus.ACCEPTED);
            default -> new ResponseEntity<>("El odontologo ya tiene un turno programado para esa fecha.", HttpStatus.BAD_REQUEST);
        };
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTurnos(@PathVariable int id) {
        if (turnoService.eliminar(id)) {
            return new ResponseEntity<>("Se elimino el turno con ID " + id + ".", HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>("No existe un turno con ID " + id + ".", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/edit")
    public ResponseEntity<Object> editTurnos(@RequestBody TurnoDTO turno) {
        if(turno.getFecha() == null){
            return new ResponseEntity<>("Ingrese una fecha valida.", HttpStatus.BAD_REQUEST);
        }

        return switch (turnoService.editar(mapper.convertValue(turno, Turno.class))) {
            case 0 -> new ResponseEntity<>("El paciente y/o odontologo no existe.", HttpStatus.BAD_REQUEST);
            case 1 -> new ResponseEntity<>("Turno editado.", HttpStatus.ACCEPTED);
            default -> new ResponseEntity<>("No existe un turno con id " + turno.getId() + ".", HttpStatus.NOT_FOUND);
        };
    }
}
