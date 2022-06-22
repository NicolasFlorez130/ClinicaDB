package company.service;

import company.entity.Domicilio;
import company.repository.impl.DomicilioDaoH2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DomicilioService {

    private static final Logger logger = LoggerFactory.getLogger(PacienteService.class);

    @Autowired
    DomicilioDaoH2 dDao;

    public static boolean revisar(Domicilio domicilio) {
        boolean cond1 = domicilio.getCalle() != null, cond2 = domicilio.getNumero() != null,
                cond3 = domicilio.getLocalidad() != null, cond4 = domicilio.getProvincia() != null;
        return !cond1 || !cond2 || !cond3 || !cond4;
    }
}
