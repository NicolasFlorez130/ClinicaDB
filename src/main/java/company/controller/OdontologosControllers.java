package company.controller;

import company.entity.Odontologo;
import company.entity.OdontologoDTO;
import company.entity.PacienteDTO;
import company.service.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/odontologos")
public class OdontologosControllers {

    @Autowired
    OdontologoService odontologoService;

    @GetMapping("/list")
    public ResponseEntity<Object> listOdontologos() {
        List<Odontologo> odontologos = odontologoService.obtenerTodos();
        if (odontologos.size() == 0) {
            return new ResponseEntity<>("No hay odontologos para mostrar.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>(odontologos, HttpStatus.OK);
        }
    }

    @GetMapping("/search/{matricula}")
    public Object searchOdontologo(@PathVariable int matricula) {
        Odontologo odontologo = odontologoService.buscar(matricula);
        if (odontologo == null) {
            return new ResponseEntity<>("No existe un odontologo con matricula " + matricula + ".", HttpStatus.OK);
        } else {
            return new ResponseEntity<>(odontologo, HttpStatus.OK);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createOdontologo(@RequestBody Odontologo odontologo) {
        if (odontologoService.agregar(odontologo)) {
            return new ResponseEntity<>(odontologoService.buscar(odontologo.getMatricula()), HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>("Un odontologo con matricula " + odontologo.getMatricula() + " ya se encuentra registrado.", HttpStatus.NOT_ACCEPTABLE);
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
    public ResponseEntity<Object> editOdontologo(@RequestBody Odontologo odontologo) {
        if (odontologoService.editar(odontologo)) {
            return new ResponseEntity<>("Odontologo editado.", HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>("No existe un odontologo con matricula " + odontologo.getMatricula() + ".", HttpStatus.NOT_FOUND);
        }
    }
}
