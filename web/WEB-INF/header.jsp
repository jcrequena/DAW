<%-- 
    Document   : header
    Created on : 20 mai 2013, 18:10:32
    Author     : Antoine Reneleau
--%>

<%@page import="DAW.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String relativePath=request.getParameter("rp");
%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <title>Proyecto DAW</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Le styles -->
    <link href="<%= (relativePath!=null)?relativePath:"" %>css/bootstrap.min.css" rel="stylesheet">
    <link href="<%= (relativePath!=null)?relativePath:"" %>css/bootstrap-responsive.min.css" rel="stylesheet">
    <style type="text/css">
      body {
        padding-top: 60px;
        padding-bottom: 40px;
      }
        .alert-top {
          position:absolute;
          display:inline-block;
          border-radius: 0 0 8px 8px;
          left:40%;
          top:0px;
          z-index:99999;
        }
    </style>
  </head>

  <body>
<%
    String error=request.getParameter("error");
    if(error!=null) {
%>
      <div class="alert alert-danger alert-top">
        <button type="button" class="close" data-dismiss="alert">&times;</button>
        <%= error %>
      </div>
<% } %>
    <div class="navbar navbar-inverse navbar-fixed-top">
      <div class="navbar-inner">
        <div class="container">
          <a class="brand" href="/DAW">PDPA</a>
          <div class="nav-collapse collapse">
            <ul class="nav">
              <li class="active"><a href="/DAW">Inicio</a></li>
              <li><a href="/DAW/proyecto">Proyectos</a></li>
              <li><a href="/DAW/usuario">Miembros</a></li>
            </ul>
<%
    Usuario currentUser=(Usuario)request.getAttribute("currentUsuario");
    if(currentUser==null) currentUser=new Usuario(0,"","","","","","","");
    if(currentUser.getId()==0){
%>
            <form method="post" action="/DAW/usuario/login" class="navbar-form pull-right">
              <input class="span2" name="email" type="text" placeholder="Email">
              <input class="span2" name="password" type="password" placeholder="Password">
              <button type="submit" class="btn">Conexión</button>
            </form>
<% } else { %>
            <form method="post" action="/DAW/usuario/logout" class="navbar-form pull-right">
              <button type="submit" class="btn">Deconexión</button>
            </form>
            <ul class="nav pull-right">
                <li><a href="usuario/ver?id=<%= currentUser.getId() %>"><%= currentUser.getEmail() %></a></li>
            </ul>
<% } %>
          </div><!--/.nav-collapse -->
        </div>
      </div>
    </div>

          <div class="container">