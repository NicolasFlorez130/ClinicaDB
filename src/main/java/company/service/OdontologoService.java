package company.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import company.entity.Odontologo;
import company.entity.OdontologoDTO;
import company.entity.Paciente;
import company.repository.IOdontologoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService {

    private static final Logger logger = LoggerFactory.getLogger(OdontologoService.class);

    @Autowired
    IOdontologoRepository oRep;

    @Autowired
    ObjectMapper mapper;

    public List<Odontologo> obtenerTodos() {
        return oRep.findAll();
    }

    public Odontologo buscar(int matricula) {
        Optional<Odontologo> odontologo = oRep.findByMatricula(matricula);
        if (odontologo.isPresent()) {
            return odontologo.get();
        } else {
            logger.error("No existe un odontologo con matricula " + matricula + ".");
            return null;
        }
    }

    public boolean agregar(Odontologo odontologo) {
        if (oRep.findByMatricula(odontologo.getMatricula()).isPresent()) {
            logger.error("Un odontologo con matricula " + odontologo.getMatricula() + " ya se encuentra registrado.");
            return false;
        } else {
            oRep.save(odontologo);
            return true;
        }
    }

    public boolean editar(Odontologo odontologo) {
        Odontologo aux = this.buscar(odontologo.getMatricula());
        if (aux == null) {
            logger.error("No existe un odontologo con matricula " + odontologo.getMatricula() + ".");
            return false;
        } else {
            odontologo.setId(aux.getId());
            oRep.save(odontologo);
            return true;
        }
    }

    public boolean eliminar(int matricula) {
        if (this.buscar(matricula) == null) {
            logger.error("No existe un odontologo con matricula " + matricula + ".");
            return false;
        } else {
            oRep.deleteByMatricula(matricula);
            return true;
        }
    }
}