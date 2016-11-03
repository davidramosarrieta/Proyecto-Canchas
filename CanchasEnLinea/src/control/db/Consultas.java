package control.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.mysql.jdbc.ResultSetMetaData;

public class Consultas extends Conexion{
	
	public String [] consultaCanchas(String tipo){
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			String consulta = "select nombreEscenario from escenario where tipoEscenario = ?";
			pst = getConexion().prepareStatement(consulta);
			pst.setString(1, tipo);
			rs = pst.executeQuery();
			
			ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData(); 
			int columnCount = rsmd.getColumnCount();
			System.out.println(columnCount);
			String [] escenariosResultList = new String [columnCount]; 
			if(rs.absolute(1)){
				int i = 1;
				   while(i <= columnCount) {
					   escenariosResultList[i] = rs.getString(i++);
				   }
				
				return escenariosResultList;
			}
			
		} catch (Exception e) {
			System.err.println("Error: "+e);
		}finally{
			try {
				if(getConexion() != null){ getConexion().close();}
				if(pst != null){ pst.close();}
				if(rs != null){ rs.close();}
			} catch (Exception e2) {
				System.err.println("Error: "+e2);
			}
		}
		return null;
	}
	
	public String consultaNombre(String cedula){
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			String consulta = "select nombre from usuario where cedula = ?";
			pst = getConexion().prepareStatement(consulta);
			pst.setString(1, cedula);
			rs = pst.executeQuery();
			
			if(rs.absolute(1)){
				return rs.getString(1);
			}
			
		} catch (Exception e) {
			System.err.println("Error: "+e);
		}finally{
			try {
				if(getConexion() != null){ getConexion().close();}
				if(pst != null){ pst.close();}
				if(rs != null){ rs.close();}
			} catch (Exception e2) {
				System.err.println("Error: "+e2);
			}
		}
		return null;
	}
	
	public boolean autenticacion(String cedula, String contrasenna){
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			String consulta = "select * from usuario where cedula = ? and contrasena = ?";
			pst = getConexion().prepareStatement(consulta);
			pst.setString(1, cedula);
			pst.setString(2, contrasenna);
			rs = pst.executeQuery();
			
			if(rs.absolute(1)){
				return true;
			}
			
		} catch (Exception e) {
			System.err.println("Error: "+e);
		}finally{
			try {
				if(getConexion() != null){ getConexion().close();}
				if(pst != null){ pst.close();}
				if(rs != null){ rs.close();}
			} catch (Exception e2) {
				System.err.println("Error: "+e2);
			}
		}
		return false;
	}
	
	public boolean agregarEscenario(String tipoEscenario, String nombreEscenario, String direccionEscenario, String telefonoEscenario){
		PreparedStatement pst = null;
		
		try {
			String consulta = "insert into escenario(tipoEscenario, nombreEscenario, direccionEscenario, telefonoEscenario ) values(?,?,?,?)";
			pst = getConexion().prepareStatement(consulta);
			pst.setString(1, tipoEscenario);
			pst.setString(2, nombreEscenario);
			pst.setString(3, direccionEscenario);
			pst.setString(4, telefonoEscenario);
			
			if(pst.executeUpdate()==1){
				return true;
			}
			
		} catch (Exception e) {
			System.err.println("Error: "+e);
		}finally{
			try {
				if(getConexion() != null){ getConexion().close();}
				if(pst != null){ pst.close();}
				
			} catch (Exception e2) {
				System.err.println("Error: "+e2);
			}
		}
	
		return false;
	}
	
	public boolean registrar (String cedula, String contrasenna, String nombre, String apellido, 
			String direccionUsuario, String telefonoUsuario, String diaNacimiento, String mesNacimiento,
			String anoNacimiento, String edad, String nivel){
		PreparedStatement pst = null;
		
		try {
			String consulta = "insert into usuario (cedula, contrasena, nombre, apellido, direccionUsuario, telefonoUsuario,  diaNacimiento,  mesNacimiento, anoNacimiento,  edad, nivel ) values(?,?,?,?,?,?,?,?,?,?,?)";
			pst = getConexion().prepareStatement(consulta);
			pst.setString(1, cedula);
			pst.setString(2, contrasenna);
			pst.setString(3, nombre);
			pst.setString(4, apellido);
			pst.setString(5, direccionUsuario);
			pst.setString(6, telefonoUsuario);
			pst.setString(7, diaNacimiento);
			pst.setString(8, mesNacimiento);
			pst.setString(9, anoNacimiento);
			pst.setString(10, edad);
			pst.setString(11, nivel);
			
			if(pst.executeUpdate()==1){
				return true;
			}
			
		} catch (Exception e) {
			System.err.println("Error: "+e);
		}finally{
			try {
				if(getConexion() != null){ getConexion().close();}
				if(pst != null){ pst.close();}
				
			} catch (Exception e2) {
				System.err.println("Error: "+e2);
			}
		}
	
		return false;
	}
	
	//Ejemplo
	public static void main (String[] args){
		Consultas co = new Consultas();
		//System.out.println(co.registrar("92543222", "davidbojeb", "Pedro", "Perez", "Medllin Cr80", "3001234567",  "15",  "7", "1990",  "26", "1" ));
		//System.out.println(co.consultaNombre("92543111"));
		
		System.out.println(co.consultaCanchas("Futbol 5"));
		//String coCanchas[] = co.consultaCanchas("Futbol 5");
		/*for(int i = 0; i<coCanchas.length; i++){
			System.out.println(coCanchas[i]);
		}*/
		//System.out.println(co.agregarEscenario("Futbol 11", "Rosario", "Medellín calle 50", "3111234567"));
	}
}