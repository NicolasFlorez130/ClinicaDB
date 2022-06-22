package company.repository.impl;

import company.entity.Odontologo;
import company.repository.Dao;
import company.repository.utils.H2Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OdontologoDaoH2 implements Dao<Odontologo> {

    private static final Logger logger = LoggerFactory.getLogger(OdontologoDaoH2.class);


    @Override
    public List<Odontologo> read() {
        List<Odontologo> odontologos = new ArrayList<>();

        H2Config.setDriver();
        Connection con = H2Config.getConnection();

        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("""
                    SELECT * FROM Odontologos;
                    """);
            while (rs.next()) {
                odontologos.add(new Odontologo(
                        rs.getString("Matricula"),
                        rs.getString("Nombre"),
                        rs.getString("Apellido")
                ));
            }
        } catch (SQLException e) {
            odontologos = null;
            logger.error(String.valueOf(e));
        }
        return odontologos;
    }

    @Override
    public Odontologo read(String value) {

        H2Config.setDriver();
        Connection con = H2Config.getConnection();
        try {
            PreparedStatement st = con.prepareStatement("SELECT * FROM Odontologos WHERE Matricula = ?");
            st.setString(1, value);
            ResultSet rs = st.executeQuery();

            if (rs.last()) {
                return new Odontologo(
                        rs.getString("Matricula"),
                        rs.getString("Nombre"),
                        rs.getString("Apellido")
                );
            }
        } catch (SQLException e) {
            logger.error(String.valueOf(e));
        }
        return null;
    }

    @Override
    public void create(Odontologo odontologo) {
        H2Config.setDriver();
        Connection con = H2Config.getConnection();

        try (PreparedStatement st = con.prepareStatement("""
                INSERT INTO Odontologos (Matricula, Nombre, Apellido)
                VALUES (?, ?, ?);
                """)) {
            con.setAutoCommit(false);
            st.setString(1, odontologo.getMatricula());
            st.setString(2, odontologo.getNombre());
            st.setString(3, odontologo.getApellido());
            st.executeUpdate();
            con.commit();
            con.close();

        } catch (SQLException e) {
            logger.error(String.valueOf(e));
        }
    }

    @Override
    public void update(Odontologo Obj) {

    }

    @Override
    public void delete(String matricula) {

        H2Config.setDriver();
        Connection con = H2Config.getConnection();

        try (PreparedStatement st = con.prepareStatement("""
                DELETE FROM Odontologos
                WHERE Matricula = ?
                """)) {
            con.setAutoCommit(false);
            st.setString(1, matricula);
            st.executeUpdate();
            con.commit();
            con.close();

        } catch (SQLException e) {
            logger.error(String.valueOf(e));
        }
    }
}
