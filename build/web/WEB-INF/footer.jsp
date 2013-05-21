<%-- 
    Document   : footer
    Created on : 20 mai 2013, 18:12:20
    Author     : Antoine Reneleau
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String relativePath=request.getParameter("rp");
%>
      <hr>

      <footer>
        <p>&copy; Programa de Desarollo Profesional en Automatización 2013</p>
      </footer>

    </div> <!-- /container -->
    <script type="application/javascript" src="<%= (relativePath!=null)?relativePath:"" %>js/jquery-1.8.1.min.js"></script>
    <script type="application/javascript" src="<%= (relativePath!=null)?relativePath:"" %>js/bootstrap.min.js"></script>
  </body>
</html>
