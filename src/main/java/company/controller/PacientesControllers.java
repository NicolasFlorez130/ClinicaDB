package company.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import company.entity.Paciente;
import company.entity.PacienteDTO;
import company.service.DomicilioService;
import company.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/pacientes")
public class PacientesControllers {

    @Autowired
    private ObjectMapper mapper;

    private final DomicilioService domicilioService;
    private final PacienteService pacienteService;

    public PacientesControllers(DomicilioService domicilioService, PacienteService pacienteService){
        this.domicilioService = domicilioService;
        this.pacienteService = pacienteService;
    }

    @GetMapping("/list")
    public ResponseEntity<Object> listPacientes() {
        List<Paciente> pacientes = pacienteService.obtenerTodos();
        if (pacientes.size() == 0) {
            return new ResponseEntity<>("No hay pacientes para mostrar.", HttpStatus.OK);
        } else {
            List<PacienteDTO> pacienteDTOS = new ArrayList<>();
            for (Paciente paciente : pacientes) {
                pacienteDTOS.add(mapper.convertValue(paciente, PacienteDTO.class));
            }
            return new ResponseEntity<>(pacienteDTOS, HttpStatus.OK);
        }
    }

    @GetMapping("/search/{dni}")
    public ResponseEntity<Object> searchPaciente(@PathVariable int dni) {
        Paciente paciente = pacienteService.buscar(dni);
        if (paciente != null) {
            return new ResponseEntity<>(mapper.convertValue(paciente, PacienteDTO.class), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No existe un paciente con DNI " + dni + ".", HttpStatus.OK);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createPaciente(@RequestBody Paciente paciente) {
        if (!domicilioService.revisar(paciente.getDomicilio()))
            return new ResponseEntity<>("Ingrese un paciente con un domicilio valido por favor.", HttpStatus.BAD_REQUEST);

        if (pacienteService.agregar(paciente)) {
            return new ResponseEntity<>("Paciente registrado.", HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>("Un usuario con DNI " + paciente.getDni() + " ya se encuentra registrado.", HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/edit")
    public ResponseEntity<Object> editPaciente(@RequestBody Paciente paciente) {
        if (!domicilioService.revisar(paciente.getDomicilio())) {
            return new ResponseEntity<>("Ingrese un paciente con un domicilio valido por favor.", HttpStatus.CONFLICT);
        }

        if (pacienteService.editar(paciente)) {
            return new ResponseEntity<>("Paciente editado.", HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>("No existe un paciente con DNI " + paciente.getDni() + ".", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{dni}")
    public ResponseEntity<String> deletePaciente(@PathVariable int dni) {
        if (pacienteService.eliminar(dni)) {
            return new ResponseEntity<>("Se elimino el paciente con DNI " + dni + ".", HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>("No existe un paciente con DNI " + dni + ".", HttpStatus.NOT_FOUND);
        }
    }
}
