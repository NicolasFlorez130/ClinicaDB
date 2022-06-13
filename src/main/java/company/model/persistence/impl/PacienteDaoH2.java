package company.model.persistence.impl;

import company.model.classes.Paciente;
import company.model.persistence.Dao;
import company.model.persistence.H2Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PacienteDaoH2 implements Dao<Paciente> {

    private static final Logger logger = LoggerFactory.getLogger(PacienteDaoH2.class);
    private static final DomicilioDaoH2 dDao = new DomicilioDaoH2();

    @Override
    public List<Paciente> read() {

        List<Paciente> pacientes = new ArrayList<Paciente>();

        H2Config.setDriver();
        Connection con = H2Config.getConnection();

        try {
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery("""
                    SELECT * FROM Paciente AS p
                    INNER JOIN Domicilio d
                    ON p.Domicilio_id = d.Id
                    """);
            while (rs.next()) {
                pacientes.add(new Paciente(
                        rs.getString("Nombre"),
                        rs.getString("Apellido"),
                        rs.getString("Dni"),
                        rs.getString("FechaDeIngreso"),
                        rs.getInt("Domicilio_id")
                ));
            }
        } catch (SQLException e) {
            pacientes = null;
            logger.error(String.valueOf(e));
        }
        return pacientes;
    }

    @Override
    public Paciente read(String value) {

        H2Config.setDriver();
        Connection con = H2Config.getConnection();
        try {
            PreparedStatement st = con.prepareStatement("""
                    SELECT * FROM Paciente AS p
                    INNER JOIN Domicilio d
                    ON p.Domicilio_id = d.Id
                    WHERE Dni = ?
                    """);
            st.setString(1, value);
            ResultSet rs = st.executeQuery();

            if (rs.last()) {

                return new Paciente(
                        rs.getString("Nombre"),
                        rs.getString("Apellido"),
                        rs.getString("Dni"),
                        rs.getString("FechaDeIngreso"),
                        rs.getInt("Domicilio_id"));
            }

        } catch (SQLException e) {
            logger.error(String.valueOf(e));
        }
        return null;
    }

    @Override
    public void create(Paciente paciente) {

        H2Config.setDriver();
        Connection con = H2Config.getConnection();

        try (PreparedStatement st = con.prepareStatement("""
                INSERT INTO Paciente (Nombre, Apellido, Dni, FechaDeIngreso, Domicilio_id)
                VALUES (?, ?, ?, ?, ?);
                """)) {

            con.setAutoCommit(false);
            st.setString(1, paciente.getNombre());
            st.setString(2, paciente.getApellido());
            st.setString(3, paciente.getDni());
            st.setString(4, paciente.getFechaDeIngreso());
            st.setInt(5, paciente.getDomicilioId());
            st.executeUpdate();
            con.commit();
            con.close();

        } catch (SQLException e) {
            logger.error(String.valueOf(e));
        }
    }

    @Override
    public void update(Paciente Obj) {
    }

    @Override
    public void delete(String dni) {

        H2Config.setDriver();
        Connection con = H2Config.getConnection();

        try (PreparedStatement st = con.prepareStatement("""
                DELETE FROM Paciente
                WHERE Dni = ?
                """)) {
            con.setAutoCommit(false);
            st.setString(1, dni);
            st.executeUpdate();
            con.commit();
            con.close();

        } catch (SQLException e) {
            logger.error(String.valueOf(e));
        }
    }
}
