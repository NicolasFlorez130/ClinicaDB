package company.repository.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class H2Config {

    public static final Logger logger = LoggerFactory.getLogger(H2Config.class);

    private static final String driver = "org.h2.Driver";
    private static final String uri = "jdbc:h2:~/db_odontologos;INIT=RUNSCRIPT FROM 'create.sql'";
    private static final String user = "sa";
    private static final String pass = "";

    public static void setDriver() {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            logger.error(String.valueOf(e));
        }
    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(uri, user, pass);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }

}
