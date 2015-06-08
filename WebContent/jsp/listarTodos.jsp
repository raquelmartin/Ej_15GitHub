<%@page import="es.curso.model.entity.Cliente"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.time.LocalDateTime"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Listado de Cliente</title>
<link rel="stylesheet" href="../css/estilos.css"/>
</head>
<body>
	<h1><%=request.getAttribute("titulo")%></h1>
	<!-- tabla html dinámicamente -->
	<span><%=LocalDateTime.now() %>></span>
	<span>Valor enviado desde el servlet Tienda Iva:</span>
	<span><%=request.getAttribute("iva") %></span>
	<% ArrayList<Cliente> clientes= (ArrayList<Cliente>)request.getAttribute("clientes"); %>
	<table>
		<tr>
		  <th>ID</th>
		  <th>Nombre</th>
		  <th>Apellidos</th>
		  <th>DNI</th>
		</tr>
		<%for(Cliente c: clientes){ %>
		<form action= ${pageContext.request.contextPath}/Tienda/eliminarPorId" method="post">
		<tr id="<%= c.getId()%>">
		    <td><input type="text" name="id" value="<%= c.getId() %>" disabled="disabled"/></td>
		    <td><%= c.getNombres() %></td>
		    <td><%= c.getApellidos() %></td>
		    <td><%= c.getDni() %></td>
		    <td><input class="btnSinBordes" type="submit" value="Eliminar" name="btn<%= c.getId()%>"/></td>
		</tr>
		</form>
		<%} %>
	</table>

</body>
</html>