<%-- 
    Document   : crear
    Created on : 20 mai 2013, 18:43:28
    Author     : Antoine Reneleau
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="DAW.Usuario"%>
<jsp:useBean id="usuario" type="DAW.Usuario" scope="request"/>
<jsp:include page="../header.jsp?rp=../" />
<h1>Nuevo Usuario</h1>
<form method="post">
    <label>Email: </label><input name="email" type="email" value="${usuario.email}">${errEmail}<br/>
    <label>Contraseña: </label><input name="password" type="password"/>${errPassword}<br/>
    <label>Verificacion contraseña: </label><input name="passwordVerif" type="password"/><br/>
    <label>Nombre: </label><input name="nombre" type="text" value="${usuario.nombre}">${errNombre}<br/>
    <label>Apellido: </label><input name="apellido" type="text" value="${usuario.apellido}">${errApellido}<br/>
    <label>Carrera: </label><input name="carrera" type="text" value="${usuario.carrera}">${errCarrera}<br/>
    <label>Cv: </label><textarea name="cv">${usuario.cv}</textarea>${errCv}<br/>
    <label>Role: </label><select name="roles">
        <option value="user">Usuario</option>
        <option value="admin">Administrador</option>
    </select></label>${errRoles}<br/>
    <input name="enviar" type="Submit" value="Guardar" />
</form>
<jsp:include page="../footer.jsp?rp=../" />