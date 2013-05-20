<%-- 
    Document   : crear
    Created on : 20 mai 2013, 18:43:28
    Author     : Antoine Reneleau
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="../header.jsp" />
<h1>Nuevo Proyecto</h1>
<form method="post">
    <label>Nombre: </label><input name="nombre" type="text" value="${proyecto.nombre}">${errNombre}<br/>
    <label>Resumen: </label><textarea name="resumen">${proyecto.resumen}</textarea>${errResumen}<br/>
    <label>Enlace: </label><input name="enlace" type="text" ${proyecto.enlace}></label>${errEnlace}<br/>
    <input name="enviar" type="Submit" value="Guardar" />
</form>
<jsp:include page="../footer.jsp" />