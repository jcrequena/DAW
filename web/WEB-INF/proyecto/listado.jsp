<%-- 
    Document   : listado
    Created on : 20 mai 2013, 18:19:31
    Author     : Antoine Reneleau
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="DAW.Proyecto"%>
<% List<Proyecto> proyectos= (List<Proyecto>)request.getAttribute("proyectos");%>
<jsp:include page="../header.jsp" />
<h1>Proyectos</h1>
<p><a href='${srvUrl}/crear'>Nuevo Proyecto</a></p>
<table>
    <tr><th>ID</th><th>Nombre</th><th>Controls</th></tr>
<% for(Proyecto p: proyectos) {
    String qry=String.format("?id=%d", p.getId());
%>
    <tr>
    <td><%=p.getId()%></td><td><%=p.getNombre()%></td>
    <td><a href='${srvUrl}/ver<%=qry%>'>Ver</a>&nbsp;
    <a href='${srvUrl}/editar<%=qry%>'>Editar</a>&nbsp;
    <a href='${srvUrl}/suprimir<%=qry%>'>Suprimir</a>&nbsp;
    </td>
    </tr>
<%}%>
</table>
<jsp:include page="../footer.jsp" />