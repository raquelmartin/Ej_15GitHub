<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Buscar por nombre</title>

</head>
<body>
<form method="get" action="${pageContext.request.contextPath}/Tienda/buscarPorNombre" name="buscarPorNombre">
 <fieldset>
  <legend style="font-size: 1.3em">ALTA TIENDA</legend>
    <div class="texto">
      <label for="nombres">NOMBRE</label>
    </div>
    <div class="cuadro">
      <input type="text" name="nombres" id="nombres"  />
    </div>
    <div class="texto">
     <input type="submit" name="enviar" id="enviar" value="Enviar" /> 
     <input type="reset" name="reiniciar" id="reiniciar" value="Reiniciar" />
    </div>
	
</fieldset>

</form>

</body>
</html>