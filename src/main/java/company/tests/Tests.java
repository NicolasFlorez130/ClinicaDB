package company.tests;

import company.repository.impl.DomicilioDaoH2;
import company.repository.impl.PacienteDaoH2;

public class Tests {
    public static final PacienteDaoH2 pDao = new PacienteDaoH2();
    public static final DomicilioDaoH2 dDao = new DomicilioDaoH2();

    public static void main(String[] args) {
        /*Domicilio domicilio = new Domicilio("127b bis", "51a", "Suba", "Bogotá");
        PacienteService.agregar(new Paciente("Nicolas", "Roman", "1081052559", "Febrero", domicilio));
        PacienteService.eliminar("1081052559");
        List<Paciente> pacientes = PacienteService.obtenerTodos();
        for (Paciente paciente : pacientes) {
            System.out.println(paciente.getNombre() + " " + paciente.getApellido() + " - " + paciente.getDni() + " / " +
                    paciente.getDomicilio().getCalle() + " " + paciente.getDomicilio().getNumero() + " " + paciente.getDomicilio().getLocalidad());
        }*/
    }
}
