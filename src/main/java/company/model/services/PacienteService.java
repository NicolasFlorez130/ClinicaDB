package company.model.services;

import company.model.classes.Domicilio;
import company.model.classes.DomicilioFactory;
import company.model.classes.Paciente;
import company.model.persistence.impl.DomicilioDaoH2;
import company.model.persistence.impl.PacienteDaoH2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class PacienteService {

    private static final Logger logger = LoggerFactory.getLogger(PacienteService.class);
    private static final PacienteDaoH2 pDao = new PacienteDaoH2();
    private static final DomicilioDaoH2 dDao = new DomicilioDaoH2();

    public static void agregar(Paciente paciente) {
        if (pDao.read(paciente.getDni()) == null) {
            if (dDao.getId(paciente.getDomicilio()) == 0) {
                dDao.create(paciente.getDomicilio());
            }
            paciente.setDomicilioId(dDao.getId(paciente.getDomicilio()));
            pDao.create(paciente);
        } else {
            logger.error("Un usuario con DNI " + paciente.getDni() + " ya se encuentra registrado.");
        }
    }

    public static Paciente buscar(String dni) {
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
        }
        return paciente;
    }

    public static List<Paciente> obtenerTodos() {
        List<Paciente> pacientes = new ArrayList<Paciente>();
        pacientes = pDao.read();

        for (Paciente paciente : pacientes) {
            paciente.setDomicilio(dDao.read(Integer.toString(paciente.getDomicilioId())));
        }

        return pacientes;
    }

    public static void eliminar(String dni) {
        if (buscar(dni) == null) {
            logger.error("No existe un paciente con dni " + dni);
        } else {
            int domId = buscar(dni).getDomicilioId();
            pDao.delete(dni);
            int acum = 0;
            for (Paciente paciente : pDao.read()) {
                if (paciente.getDomicilioId() == domId) acum++;
            }
            if (acum == 0) {
                dDao.delete(Integer.toString(domId));
            }

        }
    }
}
