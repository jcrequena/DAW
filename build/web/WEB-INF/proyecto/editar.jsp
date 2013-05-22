<%-- 
    Document   : editar
    Created on : 21 mai 2013, 00:49:16
    Author     : Antoine Reneleau
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="DAW.Usuario"%>
<jsp:useBean id="proyecto" type="DAW.Proyecto" scope="request"/>
<%
    Usuario currentUsuario=(Usuario)request.getAttribute("currentUsuario");
%>
<jsp:include page="../header.jsp?rp=../" />
<h1>Editar Proyecto</h1>
<form method="post">
    Proyecto nº: ${proyecto.id}<input name="id" type="hidden" value="${proyecto.id}"/>
    <label>Nombre: </label><input name="nombre" type="text" value="${proyecto.nombre}"/>${errNombre}<br/>
    <label>Resumen: </label><textarea name="resumen">${proyecto.resumen}</textarea>${errResumen}<br/>
    <label>Enlace: </label><input name="enlace" type="text" value="${proyecto.enlace}"/></label>${errEnlace}<br/>
    <input name="enviar" type="Submit" value="Guardar" />
</form>
<jsp:include page="../footer.jsp?rp=../" />