<%-- 
    Document   : header
    Created on : 20 mai 2013, 18:10:32
    Author     : Antoine Reneleau
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/bootstrap-responsive.min.css" rel="stylesheet">
    <style type="text/css">
      body {
        padding-top: 60px;
        padding-bottom: 40px;
      }
    </style>
  </head>

  <body>

    <div class="navbar navbar-inverse navbar-fixed-top">
      <div class="navbar-inner">
        <div class="container">
          <a class="brand" href="#">PDPA</a>
          <div class="nav-collapse collapse">
            <ul class="nav">
              <li class="active"><a href="#">Inicio</a></li>
              <li><a href="proyecto">Proyectos</a></li>
              <li><a href="#contact">Usuarios</a></li>
            </ul>
            <form class="navbar-form pull-right">
              <input class="span2" name="email" type="text" placeholder="Email">
              <input class="span2" name="password" type="password" placeholder="Password">
              <button type="submit" class="btn">Conexión</button>
            </form>
          </div><!--/.nav-collapse -->
        </div>
      </div>
    </div>
