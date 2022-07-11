package company.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import company.entity.*;
import company.service.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/odontologos")
public class OdontologosControllers {

    @Autowired
    private ObjectMapper mapper;

    private final OdontologoService odontologoService;

    public OdontologosControllers(OdontologoService odontologoService){
        this.odontologoService = odontologoService;
    }

    @GetMapping("/list")
    public ResponseEntity<Object> listOdontologos() {
        List<Odontologo> odontologos = odontologoService.obtenerTodos();
        if (odontologos.size() == 0) {
            return new ResponseEntity<>("No hay odontologos para mostrar.", HttpStatus.OK);
        } else {
            List<OdontologoDTO> odontologoDTOS = new ArrayList<>();
            for (Odontologo odontologo : odontologos) {
                odontologoDTOS.add(mapper.convertValue(odontologo, OdontologoDTO.class));
            }
            return new ResponseEntity<>(odontologoDTOS, HttpStatus.OK);
        }
    }

    @GetMapping("/search/{matricula}")
    public Object searchOdontologo(@PathVariable int matricula) {
        Odontologo odontologo = odontologoService.buscar(matricula);
        if (odontologo == null) {
            return new ResponseEntity<>("No existe un odontologo con matricula " + matricula + ".", HttpStatus.OK);
        } else {
            return new ResponseEntity<>(mapper.convertValue(odontologo, OdontologoDTO.class), HttpStatus.OK);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createOdontologo(@RequestBody OdontologoDTO odontologo) {
        if (odontologoService.agregar(mapper.convertValue(odontologo, Odontologo.class))) {
            return new ResponseEntity<>("Odontologo registrado.", HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>("Un odontologo con matricula " + odontologo.getMatricula() + " ya se encuentra registrado.", HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/delete/{matricula}")
    public ResponseEntity<String> deleteOdontologo(@PathVariable int matricula) {
        if (odontologoService.eliminar(matricula)) {
            return new ResponseEntity<>("Se elimino el odontologo con matricula " + matricula + ".", HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>("No existe un odontologo con matricula " + matricula + ".", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/edit")
    public ResponseEntity<Object> editOdontologo(@RequestBody OdontologoDTO odontologo) {
        if (odontologoService.editar(mapper.convertValue(odontologo, Odontologo.class))) {
            return new ResponseEntity<>("Odontologo editado.", HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>("No existe un odontologo con matricula " + odontologo.getMatricula() + ".", HttpStatus.NOT_FOUND);
        }
    }
}
