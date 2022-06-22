package company.entity;

import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan
public class Domicilio {

    private Integer id;
    private String calle;
    private String numero;
    private String localidad;
    private String provincia;

    public Domicilio(Integer id, String calle, String numero, String localidad, String provincia) {
        setId(id);
        setCalle(calle);
        setNumero(numero);
        setLocalidad(localidad);
        setProvincia(provincia);
    }

    public Domicilio(String calle, String numero, String localidad, String provincia) {
        setCalle(calle);
        setNumero(numero);
        setLocalidad(localidad);
        setProvincia(provincia);
    }

    public Domicilio(){}

    public Domicilio(Integer id) {
        setId(id);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }
}
