package company.service;

import company.entity.Domicilio;
import company.repository.IDomicilioRepository;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Data
public class DomicilioService {

    private static final Logger logger = LoggerFactory.getLogger(PacienteService.class);

    private IDomicilioRepository dRep;

    public DomicilioService(IDomicilioRepository dRep){
        this.dRep = dRep;
    }

    public int buscarId(Domicilio dom) {
        for (Domicilio dom1 : dRep.findAll()) {
            if (dom1.getCalle().equalsIgnoreCase(dom.getCalle()) &&
                    dom1.getNumero().equalsIgnoreCase(dom.getNumero()) &&
                    dom1.getLocalidad().equalsIgnoreCase(dom.getLocalidad()) &&
                    dom1.getProvincia().equalsIgnoreCase(dom.getProvincia())
            ) {
                return dom1.getId();
            }
        }
        return 0;
    }

    public void crear(Domicilio domicilio) {
        int id = buscarId(domicilio);
        if (id == 0) {
            dRep.save(domicilio);
        }
    }

    public boolean revisar(Domicilio domicilio) {
        boolean cond1 = domicilio.getCalle() != null, cond2 = domicilio.getNumero() != null,
                cond3 = domicilio.getLocalidad() != null, cond4 = domicilio.getProvincia() != null;
        return cond1 && cond2 && cond3 && cond4;

    }

    public Optional<Domicilio> buscar(int id) {
        Optional<Domicilio> domicilio = dRep.findById(id);
        if (domicilio.isPresent()) {
            return domicilio;
        } else {
            logger.error("No existe el domicilio con id " + id);
            return Optional.empty();
        }
    }

    public void eliminar(int id){
        dRep.deleteById(id);
    }
}
