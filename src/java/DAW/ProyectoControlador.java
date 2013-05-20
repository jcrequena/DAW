package DAW;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Antoine Reneleau
 */
public class ProyectoControlador extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String action=(request.getPathInfo()!=null?request.getPathInfo():"");
        String srvUrl=request.getContextPath()+request.getServletPath();
        
        RequestDispatcher rd;
        
        request.setAttribute("srvUrl", srvUrl); // url servlet /daw/proyecto
        
        if (action.equals("/ver")) {
            int id=Integer.parseInt(request.getParameter("id"));
            Proyecto p = ProyectoDAO.buscarId(id);
            request.setAttribute("proyecto", p);
            rd=request.getRequestDispatcher("/WEB-INF/proyecto/ver.jsp");
        }
        else if (action.equals("/suprimir")) {
            int id=Integer.parseInt(Util.getParam(request.getParameter("id"),"0"));
            if (id>0)
                ProyectoDAO.suprimir(id);
            response.sendRedirect(srvUrl); // redirección del navegador!
            return;
        }
        else if (action.equals("/crear")) {
            Proyecto p=new Proyecto(); // creamos el bean inicial
            if (request.getParameter("enviar")!=null) { //¿POST formulario?
                if (validarProyecto(request,p)) { // validar y "cargar" bean con parámetros
                    //Crear Proyecto en modelo
                    ProyectoDAO.crear(p);
                    response.sendRedirect(srvUrl); // Volver al listado de proyectos
                    return;
                }
            }
            // Formulario de nuevo proyecto
            request.setAttribute("proyecto", p); //bean vacío o con info erronea
            rd=request.getRequestDispatcher("/WEB-INF/proyecto/crear.jsp");
        }
        else if (action.equals("/editar")) {
            Proyecto p;
            if (request.getParameter("enviar")!=null) { //¿POST formulario?
                p=new Proyecto();
                if (validarProyecto(request,p)) { // validar y "cargar" bean con parámetros
                    ProyectoDAO.guardar(p); // Guardar cambios en modelo
                    response.sendRedirect(srvUrl); // Volver al listado de proyectos
                    return;
                }
            }
            else {
                //Cargar Proyecto seleccionado
                int id=Integer.parseInt(Util.getParam(request.getParameter("id"),"0"));
                p=ProyectoDAO.buscarId(id);
            }
            //Formulario de edición
            request.setAttribute("proyecto", p);
            rd=request.getRequestDispatcher("/WEB-INF/proyecto/editar.jsp");
        }
        else { //Listar proyectos 
            List<Proyecto> lp = ProyectoDAO.buscarTodos();
            request.setAttribute("proyectos", lp);
            rd=request.getRequestDispatcher("/WEB-INF/proyecto/listado.jsp");
        }
        rd.forward(request, response);
    }




    private boolean validarProyecto(HttpServletRequest request, Proyecto p) {
        boolean valido=true;
        //Capturamos y convertimos datos
        int id=Integer.parseInt(Util.getParam(request.getParameter("id"),"0"));
        String nombre=Util.getParam(request.getParameter("nombre"),"");
        String resumen=Util.getParam(request.getParameter("resumen"),"");
        String enlace=Util.getParam(request.getParameter("enlace"),"");
        //Asignamos datos al bean
        p.setId(id);
        p.setNombre(nombre);
        p.setResumen(resumen);
        p.setEnlace(enlace);
        //Validamos Datos
        if (nombre.length()<3 || nombre.length()>50) {
            request.setAttribute("errNombre", "Nombre no válido");
            valido=false;
        }
        if (resumen.length()<3 || resumen.length()>255) {
            request.setAttribute("errResumen", "Resumen no válido");
            valido=false;
        }
        if (!enlace.matches("\\b(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]")) {
            request.setAttribute("errEnlace", "Enlace no válido");
            valido=false;
        }
        return valido;
       }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
