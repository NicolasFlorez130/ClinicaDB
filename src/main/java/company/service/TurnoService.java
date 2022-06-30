package company.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import company.entity.*;
import company.repository.ITurnoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TurnoService {

    private static final Logger logger = LoggerFactory.getLogger(OdontologoService.class);

    @Autowired
    private ITurnoRepository tRep;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    PacienteService pacienteService;

    @Autowired
    OdontologoService odontologoService;

    public List<Turno> obtenerTodos() {
        return tRep.findAll();
    }

    public Turno buscar(int id) {
        Optional<Turno> turno = tRep.findById(id);
        if (turno.isPresent()) {
            return turno.get();
        } else {
            logger.error("No existe un turno con ID " + id + ".");
            return null;
        }
    }

    public boolean agregar(Turno turno) {
        Paciente paciente = pacienteService.buscar(turno.getPaciente().getDni());
        Odontologo odontologo = odontologoService.buscar(turno.getOdontologo().getMatricula());

        if(paciente != null && odontologo != null){
            turno.getOdontologo().setId(odontologo.getId());
            turno.getPaciente().setId(paciente.getId());
            tRep.save(turno);
            return true;
        }else{
            return false;
        }
    }

    public int editar(Turno turno){
        Paciente paciente = pacienteService.buscar(turno.getPaciente().getDni());
        Odontologo odontologo = odontologoService.buscar(turno.getOdontologo().getMatricula());

        if (paciente == null || odontologo == null) {
            logger.error("El paciente y/o odontologo no existe.");
            return 0;
        }

        if (this.buscar(turno.getId()) != null) {
            this.agregar(turno);
            return 1;
        } else {
            logger.error("No existe un turno con id " + turno.getId() + ".");
            return 2;
        }
    }

    public boolean eliminar(int id) {
        if (this.buscar(id) == null) {
            logger.error("No existe un turno con ID " + id + ".");
            return false;
        } else {
            tRep.deleteById(id);
            return true;
        }
    }
}
