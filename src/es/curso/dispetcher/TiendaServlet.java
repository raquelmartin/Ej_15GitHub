package es.curso.dispetcher;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.curso.controllers.EliminarController;
import es.curso.controllers.ejb.BuscarPorNombreControllerEjb;
import es.curso.controllers.ejb.DarAltaClienteControllerEjb;
import es.curso.controllers.ejb.EliminarControllerEjb;
import es.curso.controllers.ejb.ListarTodosControllerEjb;
import es.curso.model.entity.Cliente;

/**
 * Servlet implementation class TiendaServlet
 *creamos objetos para comunicar las distintas capas
 */
@WebServlet("/Tienda/*")
public class TiendaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public TiendaServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getPathInfo().substring(1);
		request.setCharacterEncoding("UTF-8");
		String titulo = "Sin título";
		RequestDispatcher rd;
		switch(action){
		    case "altaCliente": // se debe redirigir hacia el formulario altaCliente
	    	 
            rd= request.getRequestDispatcher("/archivosHtml/altaClienteView.html");
            rd.forward(request, response);  
            				break;
			case "listarTodos"://se invocará al controlador adecuado
							  //que obtendrá todos los clientes
							   //esta petición redirige a otra página
							  ListarTodosControllerEjb todos= new ListarTodosControllerEjb();
							  ArrayList<Cliente>clientes=todos.listarTodos();
							  request.setAttribute("clientes", clientes);
				              titulo="Listado general de clientes";
				              request.setAttribute("titulo", titulo);
				              rd =request.getRequestDispatcher("/jsp/listarTodos.jsp");
				              rd.forward(request, response);
							  break;
			case "buscarPorNombre"://se invocará al controlador que haga
								  //la consulta por nombre, que obtendrá
				                  //solo los clientes que 
								 //coincidan con el nombre buscado
								  //esta petición redirige a otra página
				              titulo="Resultado de la búsqueda por nombre";
				              request.setAttribute("titulo", titulo);
				              rd =request.getRequestDispatcher("/jsp/listarTodos.jsp");
				              rd.forward(request, response);
							  break; 
			case"eliminarPorId": rd=request.getRequestDispatcher("/jsp/eliminarPorId.jsp");
			rd.forward(request, response);
			                  break;
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getPathInfo().substring(1);
		request.setCharacterEncoding("UTF-8");
		RequestDispatcher rd;
		switch(action){
		case"altaCliente"://recuperar los datos tecleados en el formulario
			             String nombre = request.getParameter("nombres");
			             String apellido = request.getParameter("apellidos");
			             String dni = request.getParameter("dni");
			
			             Cliente cliente = new Cliente(0,nombre, apellido, dni);
			//invocará al controlador adecuado
			            DarAltaClienteControllerEjb controlador= new DarAltaClienteControllerEjb();
			            
			            controlador.agregar(cliente);
			            rd = request.getRequestDispatcher("/jsp/listarTodos.jsp");
			            rd.forward(request, response);
					break;
		case "buscar por nombre": //recuperar la cadena tecleada en el formulario
					String cadenaNombre = request.getParameter("nombre");
					BuscarPorNombreControllerEjb controladorBusqueda = new BuscarPorNombreControllerEjb();
					ArrayList<Cliente> resultado =controladorBusqueda.buscarPorNombre(cadenaNombre);
					request.setAttribute("clientes", resultado);
					request.setAttribute("titulo", "Búsqueda por " + cadenaNombre);
					rd=request.getRequestDispatcher("/jsp/listarTodos.jsp");
					rd.forward(request, response);
					
					
		case"eliminarPorId":
			         //recuperar el id tecleado en el form
					int id= Integer.parseInt(request.getParameter("id"));
					//llamar al controlador
					EliminarController eliminarEjb = new EliminarControllerEjb();
					eliminarEjb.eliminar(id);
					response.sendRedirect("listarTodos");
			        break;
		//case"buscar por Id":
			       //  int id = request. getParameter("id");
			         //BuscarPorIdControllerEjb controladorBusqueda= new BuscarPorIdControllerEjb();
			        //ArrayList<Cliente> resultado=controladorBusqueda.buscarPorId(cadenaNombre);
			         //request.setAttribute("clientes", resultado);
			         //request.setAttribute("titulo", "Búsqueda por" + cadenaNombre);
			         //rd=request.getRequestDispatcher("listarTodos");
			         //rd.forward(request,response);//
			
		}
	}

}
