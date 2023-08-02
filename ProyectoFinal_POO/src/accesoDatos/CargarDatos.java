package accesoDatos;
import javax.swing.*;
import java.io.File;
import java.io.PrintWriter;
import javax.swing.table.DefaultTableModel;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.*;

public class CargarDatos {
	

	private static String id_medico;
	
	public static void CargarDatosDesdeArchivo(String filepath, JTable tabla) {
        try {
       
            BufferedReader br = new BufferedReader(new FileReader(filepath));
            String line;
            
            DefaultTableModel model = (DefaultTableModel) tabla.getModel();
            model.setRowCount(0);
            while ((line = br.readLine()) != null) {
         
                String[] campos = line.split("    ");

         
                if (campos.length >= 4) { 
                	String cedula = campos[0].trim();
                    String nombre = campos[1].trim();
                    String especialidad = campos[2].trim();
                    String telefono = campos[3].trim();

                    model.addRow(new Object[]{cedula, nombre, especialidad, telefono});
                }
            }

       
            br.close();

  
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public static void GuardarDatosEnArchivo(JTable tabla, String filepath) {
	    try {
	        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
	        FileWriter fileWriter = new FileWriter(filepath);

	        for (int i = 0; i < model.getRowCount(); i++) {
	            String cedula = model.getValueAt(i, 0).toString();
	            String nombre = model.getValueAt(i, 1).toString();
	            String especialidad = model.getValueAt(i, 2).toString();
	            String telefono = model.getValueAt(i, 3).toString();

	            fileWriter.write(cedula + "    " + nombre + "    " + especialidad + "    " + telefono + "\n");
	        }

	        fileWriter.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	
	public static void CargarConsulta(JTable jtable, String tablename) {

		try {
			
            Statement stmt = Conexion.obtenerConexion().createStatement();

            String consulta = "SELECT * FROM " + tablename;
            ResultSet rs = stmt.executeQuery(consulta);

            DefaultTableModel model = new DefaultTableModel();
            jtable.setModel(model);

            ResultSetMetaData metaDatos = rs.getMetaData();
            int numColumnas = metaDatos.getColumnCount();
            for (int i = 1; i <= numColumnas; i++) {
                model.addColumn(metaDatos.getColumnName(i));
            }

            while (rs.next()) {
                Object[] fila = new Object[numColumnas];
                for (int i = 0; i < numColumnas; i++) {
                    fila[i] = rs.getObject(i + 1);
                }
                model.addRow(fila);
            }

            rs.close();
            stmt.close();
            Conexion.obtenerConexion().close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
		
		
	}
	
	public static boolean existeRegistro(String cedula) {
        try {
            Statement stmt = Conexion.obtenerConexion().createStatement();
            String consulta = "SELECT * FROM paciente WHERE cedula = '" + cedula + "'";
            ResultSet rs = stmt.executeQuery(consulta);
            boolean existe = rs.next();
            rs.close();
            stmt.close();
            Conexion.obtenerConexion().close();
            return existe;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


	public static void CargarConsultaEspecifica(JTable jtable, String tablename, String especialidad, String condicion) {

		try {
			
			 String consulta = "SELECT * FROM " + tablename + " WHERE " + especialidad + " = ?";
			PreparedStatement stmt = Conexion.obtenerConexion().prepareStatement(consulta);
			stmt.setString(1, condicion);
            ResultSet rs = stmt.executeQuery();

            DefaultTableModel model = new DefaultTableModel();
            jtable.setModel(model);

            ResultSetMetaData metaDatos = rs.getMetaData();
            int numColumnas = metaDatos.getColumnCount();
            for (int i = 1; i <= numColumnas; i++) {
                model.addColumn(metaDatos.getColumnName(i));
            }

            while (rs.next()) {
                Object[] fila = new Object[numColumnas];
                for (int i = 0; i < numColumnas; i++) {
                    fila[i] = rs.getObject(i + 1);
                }
                model.addRow(fila);
            }

            rs.close();
            stmt.close();
            Conexion.obtenerConexion().close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
		
		
	}
	

	
	public static  void identificarMedico(String cedulamedico) {
		try {
			
            String consulta = "SELECT * FROM medicos WHERE cedula = ?";
			PreparedStatement stmt = Conexion.obtenerConexion().prepareStatement(consulta);
			stmt.setString(1, cedulamedico);
            ResultSet rs = stmt.executeQuery();
            
            DefaultTableModel model = new DefaultTableModel();
            

            ResultSetMetaData metaDatos = rs.getMetaData();
            int numColumnas = metaDatos.getColumnCount();
            for (int i = 1; i <= numColumnas; i++) {
                model.addColumn(metaDatos.getColumnName(i));
            }

            while (rs.next()) {
            	id_medico = rs.getString("id_medico");
            }
           

            rs.close();
            stmt.close();
            Conexion.obtenerConexion().close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
	
	
	public static int EliminarRegistro(String tablename, int id) {
		try {
	        Statement stmt = Conexion.obtenerConexion().createStatement();
            String query = "DELETE FROM " + tablename + " WHERE id_medico=" + id;
            int filasafectadas = stmt.executeUpdate(query);
            stmt.close();
            Conexion.obtenerConexion().close();

            
				if (filasafectadas > 0) {
				
		            return id;
		        }
			
		} catch (Exception e) {
			e.printStackTrace();
		   
		}
		return -1;
       
	}
	
	public static void eliminarMedicoDeArchivo(String filepath, String idMedico) {
	    try {
	        File file = new File(filepath);
	        File newFile = new File("medicos_respaldo.txt");
	        File tempFile = new File(file.getAbsolutePath() + ".tmp");

	        BufferedReader br = new BufferedReader(new FileReader(file));
	        PrintWriter pw = new PrintWriter(new FileWriter(tempFile));

	        String line;
	        while ((line = br.readLine()) != null) {
	            String[] campos = line.split("    ");
	            if (campos.length >= 4) {
	                String id = campos[0].trim();
	                if (!id.equals(idMedico)) {
	                    pw.println(line);
	                }
	            }
	        }

	        br.close();
	        pw.close();

	        // Renombrar el archivo temporal como el archivo original
	        if (!file.delete() || !tempFile.renameTo(newFile)) {
	            throw new IOException("No se pudo eliminar el médico del archivo.");
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	
	public static int obtenerIdMedicoDesdeTabla(JTable tabla, int fila) {
	    DefaultTableModel model = (DefaultTableModel) tabla.getModel();
	    String idMedicoStr = model.getValueAt(fila, 0).toString();
	    return Integer.parseInt(idMedicoStr);
	}


	public static void CargarComboBox(JComboBox combobox){
    	
        
        try {
            String sql = "select especialidad from medicos";
            Statement stmt = Conexion.obtenerConexion().createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            

            while (rs.next()) {
                String campo = rs.getString("especialidad");
                combobox.addItem(campo);
            }


            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                Conexion.obtenerConexion().close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
 
    
	
}

	public static String getId_medico() {
		return id_medico;
	}
	

}
