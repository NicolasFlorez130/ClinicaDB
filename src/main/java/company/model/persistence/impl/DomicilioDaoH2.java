package company.model.persistence.impl;

import company.model.classes.Domicilio;
import company.model.persistence.Dao;
import company.model.persistence.H2Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DomicilioDaoH2 implements Dao<Domicilio> {

    private static final Logger logger = LoggerFactory.getLogger(OdontologoDaoH2.class);

    public int getId(Domicilio d1) {
        for (Domicilio d2 : read()) {
            if (
                    d1.getCalle().equals(d2.getCalle()) &&
                            d1.getNumero().equals(d2.getNumero()) &&
                            //d1.getProvincia().equals(d2.getProvincia()) &&
                            d1.getLocalidad().equals(d2.getLocalidad())
            ) {
                return d2.getId();
            }
        }
        return 0;
    }

    @Override
    public List<Domicilio> read() {

        List<Domicilio> domicilios = new ArrayList<Domicilio>();

        H2Config.setDriver();
        Connection con = H2Config.getConnection();

        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("""
                    SELECT * FROM Domicilio
                    """);

            while (rs.next()) {
                domicilios.add(new Domicilio(
                        rs.getInt("Id"),
                        rs.getString("Calle"),
                        rs.getString("Numero"),
                        rs.getString("Localidad"),
                        rs.getString("Provincia")));
            }
        } catch (SQLException e) {
            logger.error(String.valueOf(e));
        }
        return domicilios;
    }

    @Override
    public Domicilio read(String value) {

        H2Config.setDriver();
        Connection con = H2Config.getConnection();

        try (PreparedStatement st = con.prepareStatement("""
                SELECT * FROM Domicilio
                WHERE Id = ?
                """)) {

            st.setString(1, value);
            ResultSet rs = st.executeQuery();
            if (rs.last()) {

                return new Domicilio(
                        rs.getInt("Id"),
                        rs.getString("Calle"),
                        rs.getString("Numero"),
                        rs.getString("Localidad"),
                        rs.getString("Provincia"));

            }
        } catch (SQLException e) {
            logger.error(String.valueOf(e));
        }
        return null;
    }

    @Override
    public void create(Domicilio domicilio) {

        H2Config.setDriver();
        Connection con = H2Config.getConnection();

        try (PreparedStatement st = con.prepareStatement("""
                INSERT INTO Domicilio (Calle, Numero, Localidad)
                VALUES (?, ?, ?);
                """)) {
            con.setAutoCommit(false);
            st.setString(1, domicilio.getCalle());
            st.setString(2, domicilio.getNumero());
            st.setString(3, domicilio.getLocalidad());
            st.executeUpdate();
            con.commit();
            con.close();

        } catch (SQLException e) {
            logger.error(String.valueOf(e));
            try {
                con.rollback();
            } catch (SQLException e1) {
                logger.error(String.valueOf(e1));
            }
        }
    }

    @Override
    public void update(Domicilio Obj) {
    }

    @Override
    public void delete(String  id) {

        H2Config.setDriver();
        Connection con = H2Config.getConnection();

        try (PreparedStatement st = con.prepareStatement("""
                DELETE FROM Domicilio
                WHERE Id = ?
                """)) {
            con.setAutoCommit(false);
            st.setString(1, id);
            st.executeUpdate();
            con.commit();
            con.close();

        } catch (SQLException e) {
            logger.error(String.valueOf(e));
        }
    }
}
