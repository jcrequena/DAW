package DAW;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Antoine Reneleau
 */
class ProyectoDAO extends DAO {
    /**Recupera un Proyecto del registro actual del RS*/
    public static Proyecto recuperaProyecto(ResultSet rs) {
        Proyecto p=null;
        try {
            p=new Proyecto(rs.getInt("id"), rs.getString("nombre"), rs.getString("resumen"), rs.getString("enlace"));
        } catch (Exception ex) {
            Logger.getLogger(ProyectoDAO.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
        return p;
    }
    static Proyecto buscarId(int id) {
        Proyecto p=null;
        if (openConexion()!=null) {
            try {
                String qry="SELECT * FROM proyecto WHERE id=?";
                PreparedStatement stmn=cnx.prepareStatement(qry);
                stmn.setInt(1,id);
                ResultSet rs=stmn.executeQuery();
                rs.next();
                p=recuperaProyecto(rs);
                rs.close(); //Liberar recursos!
                stmn.close();
                closeConexion();
            } catch (Exception ex) {
                Logger.getLogger(ProyectoDAO.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            }
        }
        return p;
    }
    static List<Proyecto> buscarTodos() {
        List<Proyecto> lp=new ArrayList<Proyecto>();
        if (openConexion()!=null) {
            try {
                Statement stmn=cnx.createStatement();
                ResultSet rs=stmn.executeQuery("SELECT * FROM proyecto");
                while (rs.next()) {
                    lp.add(recuperaProyecto(rs));
                }
                rs.close(); //Liberar recursos!
                stmn.close();
                closeConexion();
            } catch (Exception ex) {
                Logger.getLogger(ProyectoDAO.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            }
        }
        return lp;
    }
    static void crear(Proyecto p) {
        if (openConexion()!=null) {
            try {
                String qry="INSERT INTO proyecto (nombre,resumen,enlace) VALUES (?,?,?)";
                PreparedStatement stmn=cnx.prepareStatement(qry);
                stmn.setString(1,p.getNombre());
                stmn.setString(2,p.getResumen());
                stmn.setString(3,p.getEnlace());
                stmn.executeUpdate();
                stmn.close();
                closeConexion();
            } catch (Exception ex) {
                Logger.getLogger(ProyectoDAO.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            }
        }
    }
    static void guardar(Proyecto p) {
        if (openConexion()!=null) {
            try {
                String qry="UPDATE proyecto SET nombre=?, resumen=?, enlace=? WHERE id=?";
                PreparedStatement stmn=cnx.prepareStatement(qry);
                stmn.setString(1,p.getNombre());
                stmn.setString(2,p.getResumen());
                stmn.setString(3,p.getEnlace());
                stmn.setInt(4,p.getId()); //Localizar el Proyecto
                stmn.executeUpdate();
                stmn.close();
                closeConexion();
            } catch (Exception ex) {
                Logger.getLogger(ProyectoDAO.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            }
        }
    }
    static void suprimir(int id) {
        if (openConexion()!=null) {
            try {
                String qry="DELETE FROM proyecto WHERE id=?";
                PreparedStatement stmn=cnx.prepareStatement(qry);
                stmn.setInt(1,id);
                stmn.executeUpdate();
                stmn.close();
                closeConexion();
            } catch (Exception ex) {
                Logger.getLogger(ProyectoDAO.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            }
        }
    }
}
