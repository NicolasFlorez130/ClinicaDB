package company.model.classes;

public class Paciente {

    private String nombre;
    private String apellido;
    private String dni;
    private String fechaDeIngreso;
    private Domicilio domicilio;
    private int domicilioId;

    public Paciente(String nombre, String apellido, String dni, String fechaDeIngreso, Domicilio domicilio) {
        setNombre(nombre);
        setApellido(apellido);
        setDni(dni);
        setFechaDeIngreso(fechaDeIngreso);
        setDomicilio(domicilio);
    }

    public Paciente(String nombre, String apellido, String dni, String fechaDeIngreso, int domId) {
        setNombre(nombre);
        setApellido(apellido);
        setDni(dni);
        setFechaDeIngreso(fechaDeIngreso);
        setDomicilioId(domId);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getFechaDeIngreso() {
        return fechaDeIngreso;
    }

    public void setFechaDeIngreso(String fechaDeIngreso) {
        this.fechaDeIngreso = fechaDeIngreso;
    }

    public Domicilio getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }

    public int getDomicilioId() {
        return domicilioId;
    }

    public void setDomicilioId(int domicilioId) {
        this.domicilioId = domicilioId;
    }
}
