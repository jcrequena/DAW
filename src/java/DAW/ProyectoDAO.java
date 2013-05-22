package DAW;

import static DAW.DAO.cnx;
import static DAW.DAO.openConexion;
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
    static List<Usuario> getUsuarios(Proyecto p) {
        List<Usuario> lu=new ArrayList<Usuario>();
        if (openConexion()!=null) {
            try {
                String qry="SELECT u.id,u.email,u.password,u.nombre,u.apellido,u.carrera,u.cv,u.roles FROM usuario_proyecto up INNER JOIN usuario u ON usuario_id=u.id AND proyecto_id=?";
                PreparedStatement stmn=cnx.prepareStatement(qry);
                stmn.setInt(1,p.getId());
                ResultSet rs=stmn.executeQuery();
                while (rs.next()) {
                    lu.add(UsuarioDAO.recuperaUsuario(rs));
                }
                rs.close(); //Liberar recursos!
                stmn.close();
                closeConexion();
            } catch (Exception ex) {
                Logger.getLogger(ProyectoDAO.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            }
        }
        return lu;
    }
    static void setUsuarios(Proyecto p, ArrayList<Integer> lu) {
        if (openConexion()!=null) {
            try {
                String qry="DELETE FROM usuario_proyecto WHERE proyecto_id=?";
                PreparedStatement stmn=cnx.prepareStatement(qry);
                stmn.setInt(1,p.getId());
                stmn.executeUpdate();
                stmn.close();
                qry="INSERT INTO usuario_proyecto (usuario_id,proyecto_id) VALUES (?,?)";
                stmn=cnx.prepareStatement(qry);
                for(Integer i: lu) {
                    stmn.setInt(1,i);
                    stmn.setInt(2,p.getId());
                    stmn.executeUpdate();
                }
                stmn.close();
                closeConexion();
            } catch (Exception ex) {
                Logger.getLogger(ProyectoDAO.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            }
        }
    }
}
