<%-- 
    Document   : editar
    Created on : 21 mai 2013, 00:49:16
    Author     : Antoine Reneleau
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="DAW.Usuario"%>
<jsp:useBean id="usuario" type="DAW.Usuario" scope="request"/>
<%
    Usuario currentUsuario=(Usuario)request.getAttribute("currentUsuario");
    Usuario usuarioEditar=(Usuario)request.getAttribute("usuario");
    if(!currentUsuario.isAdmin() && usuarioEditar.getId()!=currentUsuario.getId()){
    // si el usuario conectado no es admin y que edita un usuario que no es suyo
        response.sendRedirect("..");
    }
%>
<jsp:include page="../header.jsp?rp=../" />
<h1>Editar Usuario</h1>
<form method="post">
    Usuario nº: ${usuario.id}<input name="id" type="hidden" value="${usuario.id}"/>
    <label>Email: </label><input name="email" type="email" value="${usuario.email}">${errEmail}<br/>
    <!--<label>Contraseña actual: </label><input name="passwordActual" type="password"/>${errPassword}<br/>
    <label>Nueva contraseña: </label><input name="password" type="password"/><br/>
    <label>Verificacion contraseña: </label><input name="passwordVerification" type="password"/><br/>-->
    <label>Nombre: </label><input name="nombre" type="text" value="${usuario.nombre}">${errNombre}<br/>
    <label>Apellido: </label><input name="apellido" type="text" value="${usuario.apellido}">${errApellido}<br/>
    <label>Carrera: </label><input name="carrera" type="text" value="${usuario.carrera}">${errCarrera}<br/>
    <label>Cv: </label><textarea name="cv">${usuario.cv}</textarea>${errCv}<br/>
    <% if(currentUsuario.isAdmin()) { %>
        <label>Role: </label><select name="enlace">
            <option value="user">Usuario</option>
            <option value="admin">Administrador</option>
        </select></label>${errRoles}<br/>
    <% } %>
    <input name="enviar" type="Submit" value="Guardar" />
</form>
<jsp:include page="../footer.jsp?rp=../" />