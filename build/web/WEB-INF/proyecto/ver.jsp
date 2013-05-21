<%-- 
    Document   : ver
    Created on : 20 mai 2013, 17:54:52
    Author     : Antoine Reneleau
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="proyecto" type="DAW.Proyecto" scope="request"/>
<jsp:include page="../header.jsp?rp=../" />
<h1>${proyecto.nombre}</h1>
<p>${proyecto.resumen}</p>
<p><a href="${proyecto.enlace}">Mas informacion</a></p>
<hr/>
<p>
    <a href='${srvUrl}'>Volver</a>
    <a href='${srvUrl}/editar?id=${proyecto.id}'>Editar</a>
    <a href='${srvUrl}/suprimir?id=${proyecto.id}'>Suprimir</a>
</p>
<jsp:include page="../footer.jsp?rp=../" />
