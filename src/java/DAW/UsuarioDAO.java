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
class UsuarioDAO extends DAO {
    /**Recupera un Usuario del registro actual del RS*/
    public static Usuario recuperaUsuario(ResultSet rs) {
        Usuario u=null;
        try {
            u=new Usuario(
                    rs.getInt("id"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getString("carrera"),
                    rs.getString("cv"),
                    rs.getString("roles")
            );
        } catch (Exception ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
        return u;
    }
    static Usuario buscarId(int id) {
        Usuario u=null;
        if (openConexion()!=null) {
            try {
                String qry="SELECT * FROM usuario WHERE id=?";
                PreparedStatement stmn=cnx.prepareStatement(qry);
                stmn.setInt(1,id);
                ResultSet rs=stmn.executeQuery();
                rs.next();
                u=recuperaUsuario(rs);
                rs.close(); //Liberar recursos!
                stmn.close();
                closeConexion();
            } catch (Exception ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            }
        }
        return u;
    }
    static List<Usuario> buscarTodos() {
        List<Usuario> lu=new ArrayList<Usuario>();
        if (openConexion()!=null) {
            try {
                Statement stmn=cnx.createStatement();
                ResultSet rs=stmn.executeQuery("SELECT * FROM usuario");
                while (rs.next()) {
                    lu.add(recuperaUsuario(rs));
                }
                rs.close(); //Liberar recursos!
                stmn.close();
                closeConexion();
            } catch (Exception ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            }
        }
        return lu;
    }
    static void crear(Usuario u) {
        if (openConexion()!=null) {
            try {
                String qry="INSERT INTO usuario (email,password,nombre,apellido,carrera,cv,roles) VALUES (?,?,?,?,?,?,?)";
                PreparedStatement stmn=cnx.prepareStatement(qry);
                stmn.setString(1,u.getEmail());
                stmn.setString(2,u.getPassword());
                stmn.setString(3,u.getNombre());
                stmn.setString(4,u.getApellido());
                stmn.setString(5,u.getCarrera());
                stmn.setString(6,u.getCv());
                stmn.setString(7,u.getRoles());
                stmn.executeUpdate();
                stmn.close();
                closeConexion();
            } catch (Exception ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            }
        }
    }
    static void guardarAdmin(Usuario u) {
        if (openConexion()!=null) {
            try {
                String qry="UPDATE usuario SET email=?, nombre=?, apellido=?, carrera=?, cv=?, roles=? WHERE id=?";
                PreparedStatement stmn=cnx.prepareStatement(qry);
                stmn.setString(1,u.getEmail());
                stmn.setString(2,u.getNombre());
                stmn.setString(3,u.getApellido());
                stmn.setString(4,u.getCarrera());
                stmn.setString(5,u.getCv());
                stmn.setString(6,u.getRoles());
                stmn.setInt(7,u.getId()); //Localizar el Usuario
                stmn.executeUpdate();
                stmn.close();
                closeConexion();
            } catch (Exception ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            }
        }
    }
    static void guardar(Usuario u) {
        if (openConexion()!=null) {
            try {
                String qry="UPDATE usuario SET email=?, nombre=?, apellido=?, carrera=?, cv=? WHERE id=?";
                PreparedStatement stmn=cnx.prepareStatement(qry);
                stmn.setString(1,u.getEmail());
                stmn.setString(2,u.getNombre());
                stmn.setString(3,u.getApellido());
                stmn.setString(4,u.getCarrera());
                stmn.setString(5,u.getCv());
                stmn.setInt(6,u.getId()); //Localizar el Usuario
                stmn.executeUpdate();
                stmn.close();
                closeConexion();
            } catch (Exception ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            }
        }
    }
    static void suprimir(int id) {
        if (openConexion()!=null) {
            try {
                String qry="DELETE FROM usuario WHERE id=?";
                PreparedStatement stmn=cnx.prepareStatement(qry);
                stmn.setInt(1,id);
                stmn.executeUpdate();
                stmn.close();
                closeConexion();
            } catch (Exception ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            }
        }
    }

    static Usuario login(String email, String password) {
        Usuario u = null;
        if (openConexion()!=null) {
            try {
                String qry="SELECT * FROM usuario WHERE email=? AND password=?";
                PreparedStatement stmn=cnx.prepareStatement(qry);
                stmn.setString(1,email);
                stmn.setString(2,password);
                ResultSet rs=stmn.executeQuery();
                rs.next();
                u=recuperaUsuario(rs);
                rs.close(); //Liberar recursos!
                stmn.close();
                closeConexion();
            } catch (Exception ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            }
        }
        return u;
    }
}
