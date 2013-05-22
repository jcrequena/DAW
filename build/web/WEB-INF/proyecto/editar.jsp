<%-- 
    Document   : editar
    Created on : 21 mai 2013, 00:49:16
    Author     : Antoine Reneleau
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="DAW.Usuario"%>
<jsp:useBean id="proyecto" type="DAW.Proyecto" scope="request"/>
<%
    List<Usuario> usuarios = (List<Usuario>)request.getAttribute("usuarios");
    List<Usuario> usuariosP = (List<Usuario>)request.getAttribute("usuariosP");
%>
<jsp:include page="../header.jsp?rp=../" />
<h1>Editar Proyecto</h1>
<form method="post">
    Proyecto nº: ${proyecto.id}<input name="id" type="hidden" value="${proyecto.id}"/>
    <label>Nombre: </label><input name="nombre" type="text" value="${proyecto.nombre}"/>${errNombre}<br/>
    <label>Resumen: </label><textarea name="resumen">${proyecto.resumen}</textarea>${errResumen}<br/>
    <label>Enlace: </label><input name="enlace" type="text" value="${proyecto.enlace}"/></label>${errEnlace}<br/>
    <p>Usuario trabajando sobre este proyecto:</p>
    <% for(Usuario u: usuarios) {
        boolean in=false;
        for(Usuario uP: usuariosP) {
            if(u.getId()==uP.getId()){
                in=true;
                usuariosP.remove(uP);
                break;
            }
        }
    %>
        <input id="usuario<%=u.getId()%>" type="checkbox" name="usuarios" value="<%=u.getId()%>" <%= (in)?"checked=\"checked\"":"" %>/>
        <label for="usuario<%=u.getId()%>">
            <%= (u.getNombre()!=null)?u.getNombre()+((u.getApellido()!=null)?"":","):"" %>
            <%= (u.getApellido()!=null)?u.getApellido()+",":"" %>
            <%= u.getEmail() %>
        </label><br/>
    <%}%>
    <input name="enviar" type="Submit" value="Guardar" />
</form>
<jsp:include page="../footer.jsp?rp=../" />