/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAW;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Antoine Reneleau
 */
public final class BDD {
    private String driver="org.apache.derby.jdbc.ClientDriver";
    private String dbUser, dbPass, dbJdbcUri;
    private Connection cnx;
    private Statement peticion;

    public BDD(String dbJdbcUri, String dbUser, String dbPass) {
        this.dbJdbcUri=dbJdbcUri;
        this.dbUser=dbUser;
        this.dbPass=dbPass;
        this.connect();
    }
    
    
    
    public void connect(){
        try {
            Class.forName(this.driver);
            this.cnx = DriverManager.getConnection(dbJdbcUri,dbUser,dbPass);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BDD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(BDD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int update(String consulta){
        try {
            Statement instruccion=this.cnx.createStatement();
            int numeroResultados;
            numeroResultados = instruccion.executeUpdate(consulta);
            instruccion.close();
            return numeroResultados;
        } catch (SQLException ex) {
            Logger.getLogger(BDD.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
    public ResultSet select(String consulta){
        try {
            this.peticion = this.cnx.createStatement();
            ResultSet rs;
            rs = this.peticion.executeQuery(consulta);
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(BDD.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    /*public int closePeticion(){
        try {
            this.peticion.close();
            return 1;
        } catch (SQLException ex) {
            Logger.getLogger(BDD.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }*/
    
    public int close(){
        try {
            this.peticion.close();
            this.cnx.close();
            return 1;
        } catch (SQLException ex) {
            Logger.getLogger(BDD.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
}
