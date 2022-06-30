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

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/turnos")
public class TurnosControllers {

    @Autowired
    TurnoService turnoService;

    @Autowired
    PacienteService pacienteService;

    @Autowired
    OdontologoService odontologoService;

    @Autowired
    ObjectMapper mapper;

    @GetMapping("/list")
    public ResponseEntity<Object> listOdontologos() {
        List<Turno> turnos = turnoService.obtenerTodos();
        if (turnos.size() == 0) {
            return new ResponseEntity<>("No hay turnos para mostrar.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>(turnos, HttpStatus.OK);
        }
    }

    @GetMapping("/search/{id}")
    public Object searchTurnos(@PathVariable int id) {
        Turno turno = turnoService.buscar(id);
        if (turno == null) {
            return new ResponseEntity<>("No existe un turno con ID " + id + ".", HttpStatus.OK);
        } else {
            return new ResponseEntity<>(turno, HttpStatus.OK);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createTurnos(@RequestBody Turno turno) {
        if (turnoService.agregar(turno)) {
            return new ResponseEntity<>("Turno agregado con exito.", HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>("El paciente y/o odontologo no existe.", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTurnos(@PathVariable int id) {
        if (turnoService.buscar(id) != null) {
            turnoService.eliminar(id);
            return new ResponseEntity<>("Se elimino el turno con ID " + id + ".", HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>("No existe un turno con ID " + id + ".", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/edit")
    public ResponseEntity<Object> editTurnos(@RequestBody Turno turno) {
        return switch (turnoService.editar(turno)) {
            case 0 -> new ResponseEntity<>("El paciente y/o odontologo no existe.", HttpStatus.BAD_REQUEST);
            case 1 -> new ResponseEntity<>("Turno editado.", HttpStatus.ACCEPTED);
            default -> new ResponseEntity<>("No existe un turno con id " + turno.getId() + ".", HttpStatus.NOT_FOUND);
        };
    }
}
