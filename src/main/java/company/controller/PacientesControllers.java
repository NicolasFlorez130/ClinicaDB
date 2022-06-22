package company.controller;


import company.entity.Paciente;
import company.service.DomicilioService;
import company.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/pacientes")
public class PacientesControllers {

    @Autowired
    PacienteService pacienteService;

    @GetMapping("/list")
    public ResponseEntity<Object> listPacientes() {
        List<Paciente> pacientes = pacienteService.obtenerTodos();
        if (pacientes.size() == 0) {
            return new ResponseEntity<>("No hay pacientes para mostrar.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>(pacientes, HttpStatus.OK);
        }
    }

    @GetMapping("/search/{dni}")
    public ResponseEntity<Object> searchPaciente(@PathVariable String dni) {
        Paciente paciente = pacienteService.buscar(dni);
        return new ResponseEntity<>(Objects.requireNonNullElseGet(paciente, () -> "No existe un paciente con DNI " + dni + "."), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createPaciente(@RequestBody Paciente paciente) {
        if (DomicilioService.revisar(paciente.getDomicilio()))
            return new ResponseEntity<>("Ingrese un paciente con un domicilio valido por favor.", HttpStatus.BAD_REQUEST);

        if (pacienteService.agregar(paciente)) {
            return new ResponseEntity<>(pacienteService.buscar(paciente.getDni()), HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>("Un usuario con DNI " + paciente.getDni() + " ya se encuentra registrado.", HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @DeleteMapping("/delete/{dni}")
    public ResponseEntity<String> deletePaciente(@PathVariable String dni) {
        if (pacienteService.eliminar(dni)) {
            return new ResponseEntity<>("Se elimino el paciente con DNI " + dni + ".", HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>("No existe un paciente con DNI " + dni + ".", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/edit")
    public ResponseEntity<Object> editPaciente(@RequestBody Paciente paciente) {
        if (DomicilioService.revisar(paciente.getDomicilio())) {
            return new ResponseEntity<>("Ingrese un paciente con un domicilio valido por favor.", HttpStatus.NOT_FOUND);
        }

        if (pacienteService.editar(paciente)) {
            return new ResponseEntity<>("Paciente editado.", HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>("No existe un paciente con DNI " + paciente.getDni() + ".", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/test/{date}")
    public Object test(@PathVariable String date) {
        return LocalDate.parse(date);
    }
}
