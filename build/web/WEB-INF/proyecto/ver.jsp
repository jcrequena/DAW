<%-- 
    Document   : ver
    Created on : 20 mai 2013, 17:54:52
    Author     : Antoine Reneleau
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="DAW.Usuario"%>
<jsp:useBean id="proyecto" type="DAW.Proyecto" scope="request"/>
<%
    Usuario currentUsuario=(Usuario)request.getAttribute("currentUsuario");
    List<Usuario> usuariosP = (List<Usuario>)request.getAttribute("usuariosP");
%>
<jsp:include page="../header.jsp?rp=../" />
<h1>${proyecto.nombre}</h1>
<p>${proyecto.resumen}</p>
<p>Usuario trabajando sobre este proyecto:</p>
<ul>
<% for(Usuario u: usuariosP) { %>
    <li><a href="/DAW/usuario/ver?id=<%= u.getId() %>"><%= (u.getNombre()!=null)?u.getNombre()+((u.getApellido()!=null)?" ":","):"" %><%= (u.getApellido()!=null)?u.getApellido()+",":"" %>
            <%= u.getEmail() %></a></li>
<% } %>
</ul>
<p><a href="${proyecto.enlace}">Mas informacion</a></p>
<hr/>
<p>
    <a href='${srvUrl}'>Volver</a>
<% if(currentUsuario.isConnected()) { %>
    <a href='${srvUrl}/editar?id=${proyecto.id}'>Editar</a>
<% } if(currentUsuario.isAdmin()) { %>
    <a href='${srvUrl}/suprimir?id=${proyecto.id}'>Suprimir</a>
<% } %>
</p>
<jsp:include page="../footer.jsp?rp=../" />
