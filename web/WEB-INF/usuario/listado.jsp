<%-- 
    Document   : listado
    Created on : 20 mai 2013, 18:19:31
    Author     : Antoine Reneleau
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="DAW.Usuario"%>
<%
    List<Usuario> usuarios= (List<Usuario>)request.getAttribute("usuarios");
    Usuario currentUsuario=(Usuario)request.getAttribute("currentUsuario");
%>
<jsp:include page="../header.jsp" />
<h1>Usuarios</h1>
<% if(currentUsuario.isAdmin()) { %>
<p><a href='${srvUrl}/crear'>Nuevo Usuario</a></p>
<% } %>
<table>
    <tr><th>ID</th><th>Email</th><th>Controls</th></tr>
<% for(Usuario u: usuarios) {
    String qry=String.format("?id=%d", u.getId());
%>
    <tr>
    <td><%=u.getId()%></td><td><%=u.getEmail()%></td>
    <td><a href='${srvUrl}/ver<%=qry%>'>Ver</a>&nbsp;
<% if(currentUsuario.isAdmin() || u.getId()==currentUsuario.getId()){ %>
    <a href='${srvUrl}/editar<%=qry%>'>Editar</a>&nbsp;
<% }
   if(currentUsuario.isAdmin()) { %>
    <a href='${srvUrl}/suprimir<%=qry%>'>Suprimir</a>&nbsp;
<% } %>
    </td>
    </tr>
<%}%>
</table>
<jsp:include page="../footer.jsp" />