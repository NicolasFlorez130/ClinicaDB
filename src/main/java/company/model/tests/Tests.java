package company.model.tests;

import company.model.classes.Domicilio;
import company.model.classes.Paciente;
import company.model.persistence.impl.DomicilioDaoH2;
import company.model.persistence.impl.PacienteDaoH2;
import company.model.services.PacienteService;
//import org.junit.Assert;
//import org.junit.Test;

import java.util.List;

public class Tests {
    public static final PacienteDaoH2 pDao = new PacienteDaoH2();
    public static final DomicilioDaoH2 dDao = new DomicilioDaoH2();

    public static void main(String[] args) {
        Domicilio domicilio = new Domicilio("127b bis", "51a", "Suba", "Bogotá");
        PacienteService.agregar(new Paciente("Nicolas", "Roman", "1081052559", "Febrero", domicilio));
        PacienteService.eliminar("1081052559");
        List<Paciente> pacientes = PacienteService.obtenerTodos();
        for (Paciente paciente : pacientes) {
            System.out.println(paciente.getNombre() + " " + paciente.getApellido() + " - " + paciente.getDni() + " / " +
                    paciente.getDomicilio().getCalle() + " " + paciente.getDomicilio().getNumero() + " " + paciente.getDomicilio().getLocalidad());
        }
    }

    /*@Test
    public void crearPaciente() {
        Domicilio domicilio = new Domicilio("127b bis", "51a", "Suba", "Bogotá");
        PacienteService.agregar(new Paciente("Nicolas", "Roman", "1081052559", "Febrero", domicilio));

        Assert.assertNotNull(PacienteService.buscar("1081052559"));
    }

    @Test
    public void eliminarPaciente() {
        PacienteService.eliminar("1081052559");

        Assert.assertNull(PacienteService.buscar("1081052559"));
    }

    @Test
    public void buscarTodosLosPacientes() {
        List<Paciente> pacientes = PacienteService.obtenerTodos();
        for (Paciente paciente : pacientes) {
            System.out.println(paciente.getNombre() + " " + paciente.getApellido() + " - " + paciente.getDni() + " / " +
                    paciente.getDomicilio().getCalle() + " " + paciente.getDomicilio().getNumero() + " " + paciente.getDomicilio().getLocalidad());
        }

        Assert.assertTrue(pacientes.size() > 0);

    }*/
}
