package DAW;

/**
 *
 * @author Antoine Reneleau
 */
public class Proyecto {
    private int id;
    private String nombre;
    private String resumen;
    private String enlace;

    public Proyecto() {
    }

    public Proyecto(int id, String nombre, String resumen, String enlace) {
        this.id = id;
        this.nombre = nombre;
        this.resumen = resumen;
        this.enlace = enlace;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    public String getEnlace() {
        return enlace;
    }

    public void setEnlace(String enlace) {
        this.enlace = enlace;
    }
}
