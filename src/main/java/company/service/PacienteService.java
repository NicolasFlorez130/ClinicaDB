package company.service;

import company.entity.Domicilio;
import company.entity.DomicilioFactory;
import company.entity.Paciente;
import company.repository.impl.DomicilioDaoH2;
import company.repository.impl.PacienteDaoH2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class PacienteService {

    private static final Logger logger = LoggerFactory.getLogger(PacienteService.class);

    @Autowired
    PacienteDaoH2 pDao;

    @Autowired
    DomicilioDaoH2 dDao;

    public boolean agregar(Paciente paciente) {
        if (pDao.read(paciente.getDni()) == null) {
            if (dDao.getId(paciente.getDomicilio()) == 0) {
                dDao.create(paciente.getDomicilio());
            }
            paciente.setDomicilioId(dDao.getId(paciente.getDomicilio()));
            pDao.create(paciente);
            return true;
        } else {
            logger.error("Un usuario con DNI " + paciente.getDni() + " ya se encuentra registrado.");
            return false;
        }
    }

    public Paciente buscar(String dni) {
        Paciente paciente = pDao.read(dni);
        if (paciente != null) {
            Domicilio domicilio = DomicilioFactory.getDomicilio(paciente.getDomicilioId());
            if (domicilio.getCalle() == null) {
                DomicilioFactory.setDomicilio(dDao.read(Integer.toString(paciente.getDomicilioId())));
                domicilio = DomicilioFactory.getDomicilio(paciente.getDomicilioId());
            }
            paciente.setDomicilio(domicilio);
        } else {
            logger.error("No existe un usuario con DNI " + dni);
            return null;
        }
        return paciente;
    }

    public List<Paciente> obtenerTodos() {
        List<Paciente> pacientes = pDao.read();

        for (Paciente paciente : pacientes) {
            paciente.setDomicilio(dDao.read(Integer.toString(paciente.getDomicilioId())));
        }

        return pacientes;
    }

    public boolean eliminar(String dni) {
        if (buscar(dni) == null) {
            logger.error("No existe un paciente con dni " + dni);
            return false;
        } else {
            int domId = Objects.requireNonNull(buscar(dni)).getDomicilioId();
            pDao.delete(dni);
            int acum = 0;
            for (Paciente paciente : pDao.read()) {
                if (paciente.getDomicilioId() == domId) acum++;
            }
            if (acum == 0) {
                dDao.delete(Integer.toString(domId));
            }
            return true;
        }
    }

    public boolean editar(Paciente paciente) {
        if (buscar(paciente.getDni()) == null) {
            return false;
        } else {
            eliminar(paciente.getDni());
            agregar(paciente);
            return true;
        }
    }
}
