package DAW;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 *
 * @author Antoine Reneleau
 */
public class DAO {
    protected static Connection cnx;
    private static String connPoolName="daw";
    
    public static Connection openConexion() {
        cnx=null;
        try {
            Context context = new InitialContext();
            DataSource ds = (DataSource)context.lookup("jdbc/"+connPoolName);
            cnx = ds.getConnection();
        } catch (Exception ex ) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
        return cnx;
    }

    public static void closeConexion() {
        try {
            cnx.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
