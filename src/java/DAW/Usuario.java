/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAW;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

/**
 *
 * @author Antoine Reneleau
 */
public class Usuario {
    private int id;
    private String email;
    private String password;
    private String nombre;
    private String apellido;
    private String carrera;
    private String cv;
    private String roles;

    Usuario() {
    }

    public Usuario(int id, String email, String password, String nombre, String apellido, String carrera, String cv, String roles) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.nombre = nombre;
        this.apellido = apellido;
        this.carrera = carrera;
        this.cv = cv;
        this.roles = roles;
    }

    public int getId() {
        return id;
    }

    void setId(int id) {
        this.id=id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public String getCv() {
        return cv;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public boolean isAdmin() {
        return this.roles.equals("admin");
    }

    public boolean isConnected() {
        return this.id!=0;
    }
}