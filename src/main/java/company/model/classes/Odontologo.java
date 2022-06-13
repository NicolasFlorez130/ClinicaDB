package company.model.classes;

public class Odontologo {
    private String matricula;
    private String nombre;
    private String apellido;

    public Odontologo(String matricula, String nombre, String apellido) {
        setMatricula(matricula);
        setNombre(nombre);
        setApellido(apellido);
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
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
}
