package DAW;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Antoine Reneleau
 */
@WebServlet("/usuario/*")
public class UsuarioControlador extends HttpServlet {

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
        
        request.setAttribute("srvUrl", srvUrl); // url servlet /daw/usuario
        
        Usuario currentUsuario=getCurrentUsuario(request);
        HttpSession session = request.getSession();
        
        if (action.equals("/login")) {
            String email=Util.getParam(request.getParameter("email"),"");
            String password=Util.getParam(request.getParameter("password"),"");
            Usuario u = UsuarioDAO.login(email,password);
            if(u==null) {// failed to connect
                response.sendRedirect("/DAW?error=Email o contraseña no valido.");
                return;
            }
            session.setAttribute("currentUsuario", u);
            response.sendRedirect("/DAW");
            return;
        }
        else if (action.equals("/logout")) {
            session.removeAttribute("currentUsuario");
            response.sendRedirect(srvUrl);
            return;
        }
        else if (action.equals("/ver")) {
            int id=Integer.parseInt(request.getParameter("id"));
            Usuario u = UsuarioDAO.buscarId(id);
            if(u!=null) {
                request.setAttribute("usuario", u);
                request.setAttribute("proyectos", ProyectoDAO.buscarTodos());
                request.setAttribute("proyectosU", UsuarioDAO.getProyectos(u));
                rd=request.getRequestDispatcher("/WEB-INF/usuario/ver.jsp");
            }
            else {
                response.sendRedirect(srvUrl+"?error=Usuario n°"+id+" no encontrado."); // Volver al listado de usuarios
                return;
            }
        }
        else if (action.equals("/suprimir")) {
            if(currentUsuario.isAdmin()) {
                int id=Integer.parseInt(Util.getParam(request.getParameter("id"),"0"));
                if (id>0)
                    UsuarioDAO.suprimir(id);
                response.sendRedirect(srvUrl+"?error=Usuario n°"+id+" suprimido."); // redirección del navegador!
                return;
            }
            else {
                response.sendRedirect(srvUrl); // redirección del navegador!
                return;
            }
        }
        else if (action.equals("/crear")) {
            if(currentUsuario.isAdmin()) {
                Usuario u=new Usuario(); // creamos el bean inicial
                if (request.getParameter("enviar")!=null) { //¿POST formulario?
                    if (validarUsuarioCrear(request,u)) { // validar y "cargar" bean con parámetros
                        //Crear Usuario en modelo
                        UsuarioDAO.crear(u);
                        response.sendRedirect(srvUrl); // Volver al listado de usuarios
                        return;
                    }
                }
                // Formulario de nuevo usuario
                request.setAttribute("usuario", u); //bean vacío o con info erronea
                rd=request.getRequestDispatcher("/WEB-INF/usuario/crear.jsp");
            }
            else {
                response.sendRedirect(srvUrl); // redirección del navegador!
                return;
            }
        }
        else if (action.equals("/editar")) {
            Usuario u;
            int id=Integer.parseInt(Util.getParam(request.getParameter("id"),"0"));
            if (currentUsuario.isAdmin() || currentUsuario.getId()==id) {
                if (request.getParameter("enviar")!=null) { //¿POST formulario?
                    u=new Usuario();
                    if(currentUsuario.isAdmin() && validarUsuarioAdmin(request,u)) {
                        // validar y "cargar" bean con parámetros
                        UsuarioDAO.guardarAdmin(u); // Guardar cambios en modelo
                        UsuarioDAO.setProyectos(u, getProyectos(request));
                        response.sendRedirect(srvUrl+"/ver?id="+u.getId()); // Volver al listado de usuarios
                        return;
                    }
                    else if(validarUsuario(request,u)) {
                        // validar y "cargar" bean con parámetros
                        UsuarioDAO.guardar(u); // Guardar cambios en modelo
                        UsuarioDAO.setProyectos(u, getProyectos(request));
                        response.sendRedirect(srvUrl+"/ver?id="+u.getId()); // Volver al listado de usuarios
                        return;
                    }
                }
                else {
                    //Cargar Usuario seleccionado
                    u=UsuarioDAO.buscarId(id);
                }
                //Formulario de edición
                if(u!=null) {
                    request.setAttribute("usuario", u);
                    request.setAttribute("proyectos", ProyectoDAO.buscarTodos());
                    request.setAttribute("proyectosU", UsuarioDAO.getProyectos(u));
                    rd=request.getRequestDispatcher("/WEB-INF/usuario/editar.jsp");
                }
                else {
                    response.sendRedirect(srvUrl); // Volver al listado de usuarios
                    return;
                }
            }
            else {
                response.sendRedirect(srvUrl); // Volver al listado de usuarios
                return;
            }
        }
        else { //Listar usuarios 
            List<Usuario> lu = UsuarioDAO.buscarTodos();
            request.setAttribute("usuarios", lu);
            rd=request.getRequestDispatcher("/WEB-INF/usuario/listado.jsp");
        }
        rd.forward(request, response);
    }

    private boolean validarUsuario(HttpServletRequest request, Usuario u) {
        boolean valido=true;
        //Capturamos y convertimos datos
        int id=Integer.parseInt(Util.getParam(request.getParameter("id"),"0"));
        String email=Util.getParam(request.getParameter("email"),"");
        String nombre=Util.getParam(request.getParameter("nombre"),"");
        String apellido=Util.getParam(request.getParameter("apellido"),"");
        String carrera=Util.getParam(request.getParameter("carrera"),"");
        String cv=Util.getParam(request.getParameter("cv"),"");
        //Asignamos datos al bean
        u.setId(id);
        u.setEmail(email);
        u.setNombre(nombre);
        u.setApellido(apellido);
        u.setCarrera(carrera);
        u.setCv(cv);
        //Validamos Datos
        Pattern emailPtr = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        if (!emailPtr.matcher(email).matches()) {
            request.setAttribute("errEmail", "Email no válido");
            valido=false;
        }
        if (nombre.length()>50) {
            request.setAttribute("errNombre", "Nombre no válido");
            valido=false;
        }
        if (apellido.length()>50) {
            request.setAttribute("errApellido", "Apellido no válido");
            valido=false;
        }
        if (carrera.length()>50) {
            request.setAttribute("errCarrera", "Carrera no válido");
            valido=false;
        }
        if (cv.length()>50) {
            request.setAttribute("errCv", "Cv no válido");
            valido=false;
        }
        return valido;
    }

    private boolean validarUsuarioAdmin(HttpServletRequest request, Usuario u) {
        boolean valido=validarUsuario(request, u);
        //Capturamos y convertimos datos
        String roles=Util.getParam(request.getParameter("roles"),"");
        //Asignamos datos al bean
        u.setRoles(roles);
        //Validamos Datos
        if (!roles.equals("admin") && !roles.equals("user")) {
            request.setAttribute("errRoles", "Role no válido");
            valido=false;
        }
        return valido;
    }

    private boolean validarUsuarioCrear(HttpServletRequest request, Usuario u) {
        boolean valido=validarUsuario(request, u)&&validarUsuarioAdmin(request, u);
        //Capturamos y convertimos datos
        String password=Util.getParam(request.getParameter("password"),"");
        String passwordVerif=Util.getParam(request.getParameter("passwordVerif"),"");
        //Asignamos datos al bean
        u.setPassword(password);
        //Validamos Datos
        if (password.length()<4 || password.length()>255 || !password.equals(passwordVerif)) {
            request.setAttribute("errPassword", "Contraseña no válido");
            valido=false;
        }
        return valido;
    }
    
    public static Usuario getCurrentUsuario(HttpServletRequest request){
        HttpSession session = request.getSession();
        Usuario currentUsuario=new Usuario(0,"","","","","","","deconectado");
        if(session.getAttribute("currentUsuario")==null){
            request.setAttribute("currentUsuario", currentUsuario);
        }
        else {
            currentUsuario = (Usuario)session.getAttribute("currentUsuario");
            request.setAttribute("currentUsuario", currentUsuario);
        }
        return currentUsuario;
    }
    private ArrayList<Integer> getProyectos(HttpServletRequest request) {
        ArrayList<Integer> lpu = new ArrayList<Integer>();
        String[] proyectosU = request.getParameterValues("proyectos");
        for(String ps: proyectosU) {
            int pl=Integer.parseInt(Util.getParam(ps,"0"));
            if (pl!=0)
                lpu.add(pl);
        }
        return lpu;
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
