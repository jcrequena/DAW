<%-- 
    Document   : ver
    Created on : 20 mai 2013, 17:54:52
    Author     : Antoine Reneleau
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="DAW.Usuario"%>
<jsp:useBean id="usuario" type="DAW.Usuario" scope="request"/>
<%
    Usuario currentUsuario=(Usuario)request.getAttribute("currentUsuario");
    Usuario usuarioVer=(Usuario)request.getAttribute("usuario");
%>
<jsp:include page="../header.jsp?rp=../" />
<h1>${usuario.nombre} ${usuario.apellido}</h1>
<p>${usuario.email}</p>
<p>${usuario.cv}</p>
<p>${usuario.roles}</p>
<hr/>
<p>
    <a href='${srvUrl}'>Volver</a>
<% if(currentUsuario.isAdmin() || usuarioVer.getId()==currentUsuario.getId()){ %>
    <a href='${srvUrl}/editar?id=${usuario.id}'>Editar</a>&nbsp;
<% }
   if(currentUsuario.isAdmin()) { %>
    <a href='${srvUrl}/suprimir?id=${usuario.id}'>Suprimir</a>&nbsp;
<% } %>
</p>
<jsp:include page="../footer.jsp?rp=../" />
