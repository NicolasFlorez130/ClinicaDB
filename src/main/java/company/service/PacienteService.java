package company.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import company.entity.Paciente;
import company.entity.PacienteDTO;
import company.repository.IOdontologoRepository;
import company.repository.IPacienteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    private static final Logger logger = LoggerFactory.getLogger(PacienteService.class);

    private final IPacienteRepository pRep;
    private final DomicilioService domicilioService;
    private ObjectMapper mapper;

    public PacienteService(IPacienteRepository pRep, DomicilioService domicilioService){
        this.pRep = pRep;
        this.domicilioService = domicilioService;
    }

    public List<Paciente> obtenerTodos() {
        return pRep.findAll();
    }

    public Paciente buscar(int dni) {
        Optional<Paciente> paciente = pRep.findByDni(dni);
        if (paciente.isPresent()) {
            return paciente.get();
        } else {
            logger.error("No existe un usuario con DNI " + dni + ".");
            return null;
        }
    }

    public boolean agregar(Paciente paciente) {
        if (pRep.findByDni(paciente.getDni()).isEmpty()) {
            if (domicilioService.buscarId(paciente.getDomicilio()) == 0) {
                domicilioService.crear(paciente.getDomicilio());
            }
            paciente.getDomicilio().setId(domicilioService.buscarId(paciente.getDomicilio()));
            pRep.save(paciente);
            return true;
        } else {
            logger.error("Un usuario con DNI " + paciente.getDni() + " ya se encuentra registrado.");
            return false;
        }
    }

    public boolean editar(Paciente paciente) {
        Paciente aux = this.buscar(paciente.getDni());
        if (aux == null) {
            logger.error("No existe un paciente con DNI " + paciente.getDni() + ".");
            return false;
        } else {
            paciente.setId(aux.getId());
        }

        int domId = aux.getDomicilio().getId();

        if (domicilioService.buscarId(paciente.getDomicilio()) == 0) {
            domicilioService.crear(paciente.getDomicilio());
        }

        paciente.getDomicilio().setId(domicilioService.buscarId(paciente.getDomicilio()));
        pRep.save(paciente);

        int count = 0;
        for (Paciente paciente1 : pRep.findAll()) {
            if (paciente1.getDomicilio().getId() == domId) count++;
        }

        if (count == 0) domicilioService.eliminar(domId);

        return true;
    }

    public boolean eliminar(int dni) {
        Paciente paciente = this.buscar(dni);
        if (paciente == null) {
            logger.error("No existe un paciente con DNI " + dni + ".");
            return false;
        } else {
            int domId = paciente.getDomicilio().getId();
            pRep.deleteByDni(dni);
            int count = 0;
            for (Paciente paciente1 : pRep.findAll()) {
                if (paciente1.getDomicilio().getId() == domId) count++;
            }

            if (count == 0) domicilioService.eliminar(domId);

            return true;
        }
    }
}