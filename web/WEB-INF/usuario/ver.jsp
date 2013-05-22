<%-- 
    Document   : ver
    Created on : 20 mai 2013, 17:54:52
    Author     : Antoine Reneleau
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="DAW.Usuario"%>
<%@page import="DAW.Proyecto"%>
<%@page import="java.util.List"%>
<jsp:useBean id="usuario" type="DAW.Usuario" scope="request"/>
<%
    Usuario currentUsuario=(Usuario)request.getAttribute("currentUsuario");
    Usuario usuarioVer=(Usuario)request.getAttribute("usuario");
    List<Proyecto> proyectosU = (List<Proyecto>)request.getAttribute("proyectosU");
%>
<jsp:include page="../header.jsp?rp=../" />
<h1>${usuario.nombre} ${usuario.apellido}</h1>
<p>${usuario.email}</p>
<p>${usuario.cv}</p>
<p>${usuario.roles}</p>

<p>Proyecto sobre cual(es) trabaja este usuario:</p>
<ul>
<% for(Proyecto p: proyectosU) { %>
    <li><a href="/DAW/proyecto/ver?id=<%= p.getId() %>"><%= p.getNombre()%></a></li>
<% } %>
</ul>
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
