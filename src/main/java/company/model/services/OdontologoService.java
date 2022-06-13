package company.model.services;

import company.model.classes.Odontologo;
import company.model.persistence.impl.OdontologoDaoH2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class OdontologoService {
    private static final Logger logger = LoggerFactory.getLogger(OdontologoService.class);
    private static final OdontologoDaoH2 oDao = new OdontologoDaoH2();

    public static void agregar(Odontologo odontologo) {
        if (oDao.read(odontologo.getMatricula()) != null) {
            logger.error("Un odontologo con matricula " + odontologo.getMatricula() + " ya se encuentra registrado.");
        } else {
            oDao.create(odontologo);
        }
    }

    public static Odontologo buscar(String value) {
        Odontologo odontologo = oDao.read(value);
        if (odontologo != null) {
            logger.error("No existe un odontologo con matricula " + value);
            return odontologo;
        } else {
            return null;
        }
    }

    public static List<Odontologo> obtenerTodos() {
        return oDao.read();
    }

    public static void eliminar(String matricula) {
        if (buscar(matricula) == null) {
            logger.error("No existe un odontologo con matricula " + matricula);
        } else {
            oDao.delete(matricula);
        }
    }
}
