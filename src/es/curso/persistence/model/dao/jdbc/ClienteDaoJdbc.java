package es.curso.persistence.model.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import es.curso.model.entity.Cliente;
import es.curso.persistence.model.dao.ClienteDao;

public class ClienteDaoJdbc implements ClienteDao {
	private Connection cx;
	public ClienteDaoJdbc(){
		super();
	}

	@Override
	public void create(Cliente cliente) {
		try {
			// van las instrucciones 
			//para
			//1.conectar con la base de datos
			abrirConexion();
			//2.preparar la sentencia -sql- para agregar
			PreparedStatement ps= 
				cx.prepareStatement("INSERT INTO CLIENTE VALUES(?,?,?,?)");
            //2.1iNSERTAR LOS DATOS DEL CLIENTE EN LAS ????
			ps.setInt(1, 0);//primera ?
			ps.setString(2, cliente.getNombres());//segunda ?
			ps.setString(3, cliente.getApellidos());
			ps.setString(4, cliente.getDni());
			//3.Ejecutar la sentencia -sql-
			ps.executeUpdate();//=que dar al play en heidi
				//nota: se usa executeUpdate() para las instrucciones sql
			    //como: insert, delete, update
			    //esta instrucción devuelve como resultado el número de
			    //registros (o filas) afectadas.
			//3.1 Hacer el commit
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			//4.Cerrar la conexión
			cerrarConexion();
		}
		
	}

	@Override
	public ArrayList<Cliente> findAll() {
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		try {
			//1.abrir la conexión 
			abrirConexion();
			//2. preparar las sentencias
			PreparedStatement ps= cx.prepareStatement("SELECT * FROM CLIENTE");
			//3. ejecutar la sentencia...
			ResultSet consulta=ps.executeQuery();
			//3.1 traspasar los datos de la respuesta al arrayList
			while(consulta.next()){
				Cliente clienteTemporal = new Cliente();
				//código para traspasar de la consulta(ResultSet)
				//hacia clienteTemporal
				clienteTemporal.setId(consulta.getInt("id"));
				//lo que está entrecomillas corresponde al nombre
				//del atributo en la base de datos
				clienteTemporal.setNombres(consulta.getString("nombres"));
				clienteTemporal.setApellidos(consulta.getString("apellidos"));
				clienteTemporal.setDni(consulta.getString("dni"));
				clientes.add(clienteTemporal);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			//4. cerrar la conexión
		   cerrarConexion();	
		}
	return clientes;
	}
	
	private void abrirConexion(){
	 
		try {
		 //1.Determinar y validar si tengo el driver o conector(de mysql)
			Class.forName("com.mysql.jdbc.Driver");
		 //2.Establecer la conexión en sí	
		 cx= DriverManager.getConnection(
				 "jdbc:mysql://localhost:3306/Tienda",
				 "rootTienda",
				 "rootTienda"
				 );
		 //3.Iniciar el autoCommit en false
		 //cx.setAutoCommit(false);
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	private void cerrarConexion(){
		try {
			if(cx!=null)
			cx.close();//si cx distinto a null, se cierra
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<Cliente> searchByName(String name) {
		ArrayList<Cliente> clientes= new ArrayList<Cliente>();
		try {
			//1.Establecer la conexión con la bbdd
			abrirConexion();
			//2.Preparar la sentencia sql...parametrizada(las que tienen interrogante)
			PreparedStatement ps = cx.prepareStatement("SELECT * FROM CLIENTE WHERE NOMBRES LIKE ?");
			//2.1 Especificar lo que va en interrogación
			ps.setString(1, "%"+name+"%");
			//3.Ejecutar la query
			ResultSet resultado = ps.executeQuery();
			//3.1 Pasar los datos que vienen de la bbdd(ResultSet)hacia
			//el ArrayList<cliente>
			while(resultado.next()){
				Cliente c= new Cliente();
				c.setId(resultado.getInt("id"));
				c.setNombres(resultado.getString("nombres"));
				c.setApellidos(resultado.getString("apellidos"));
				c.setDni(resultado.getNString("dni"));
			clientes.add(c);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			//4. Cerrar la conexión(en el finally)
			cerrarConexion();
			
		}
		return null;
	}

	@Override
	public void update(Cliente cliente) {
		
		
	}

	@Override
	public void delete(Integer id) {
		try {
			//1. Establecer conexión
			abrirConexion();
			//2. Preparar las sentencias
			PreparedStatement ps= cx.prepareStatement("DELETE FROM CLIENTE WHERE ID =?");
			//2.1 Especificar lo que va en ?
			ps.setInt(1, id);
			//3. Ejecutar la sentencia
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			//4.Cerrar la conexión
			cerrarConexion();
		}
		
		
	}

	@Override
	public void searchById(Integer id) {
		// TODO Auto-generated method stub
		
	}
}
