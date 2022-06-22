package company.service;

import company.entity.Odontologo;
import company.repository.impl.OdontologoDaoH2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OdontologoService {
    private static final Logger logger = LoggerFactory.getLogger(OdontologoService.class);

    @Autowired
    OdontologoDaoH2 oDao;

    public boolean agregar(Odontologo odontologo) {
        if (oDao.read(odontologo.getMatricula()) != null) {
            logger.error("Un odontologo con matricula " + odontologo.getMatricula() + " ya se encuentra registrado.");
            return false;
        } else {
            oDao.create(odontologo);
            return true;
        }
    }

    public Odontologo buscar(String matricula) {
        Odontologo odontologo = oDao.read(matricula);
        if (odontologo != null) {
            return odontologo;
        } else {
            logger.error("No existe un odontologo con matricula " + matricula);
            return null;
        }
    }

    public List<Odontologo> obtenerTodos() {
        return oDao.read();
    }

    public boolean eliminar(String matricula) {
        if (buscar(matricula) == null) {
            logger.error("No existe un odontologo con matricula " + matricula);
            return false;
        } else {
            oDao.delete(matricula);
            return true;
        }
    }

    public boolean editar(Odontologo odontologo) {
        if (buscar(odontologo.getMatricula()) == null) {
            return false;
        } else {
            eliminar(odontologo.getMatricula());
            agregar(odontologo);
            return true;
        }
    }
}