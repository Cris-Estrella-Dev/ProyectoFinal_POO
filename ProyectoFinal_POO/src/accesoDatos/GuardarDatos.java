package accesoDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;



public class GuardarDatos {

	//insertar medico
    public static void insertarDatos(String campo1, String campo2, String campo3, String campo4) {
        Connection conexion = Conexion.obtenerConexion();
        

        if (conexion != null) {
            try {
                String sql = "INSERT INTO Medicos (nombre, cedula,especialidad,disponibilidad) VALUES (?,?,?,?)";
                PreparedStatement statement = conexion.prepareStatement(sql);

                statement.setString(1, campo1);
                statement.setString(2, campo2);
                statement.setString(3, campo3);
                statement.setString(4, campo4);

                int filasInsertadas = statement.executeUpdate();

                if (filasInsertadas > 0) {
                    System.out.println("Registro insertado correctamente.");
                   
                } else {
                    System.out.println("No se pudo insertar el registro.");
                }

                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    conexion.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("Error al establecer la conexión.");
        }

        
    }
    //insertar datos de las enfermedades
    
    public static void insertarDatosEnfermedad(String nombre, String cronico) {
        Connection conexion = Conexion.obtenerConexion();
        

        if (conexion != null) {
            try {
                String sql = "INSERT INTO Enfermedad (nombre, cronico) VALUES (?,?)";
                PreparedStatement statement = conexion.prepareStatement(sql);

                statement.setString(1, nombre);
                statement.setString(2, cronico);
                

                int filasInsertadas = statement.executeUpdate();

                if (filasInsertadas > 0) {
                    System.out.println("Registro insertado correctamente.");
                   
                } else {
                    System.out.println("No se pudo insertar el registro.");
                }

                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    conexion.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("Error al establecer la conexión.");
        }

        
    }
    
    //insertar vacuna
    
    public static void insertarDatosVacuna(String nombre) {
        Connection conexion = Conexion.obtenerConexion();
        

        if (conexion != null) {
            try {
                String sql = "INSERT INTO Vacuna (nombre) VALUES (?)";
                PreparedStatement statement = conexion.prepareStatement(sql);

                statement.setString(1, nombre);
                
                

                int filasInsertadas = statement.executeUpdate();

                if (filasInsertadas > 0) {
                    System.out.println("Registro insertado correctamente.");
                   
                } else {
                    System.out.println("No se pudo insertar el registro.");
                }

                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    conexion.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("Error al establecer la conexión.");
        }

        
    }
    
    public static void insertarDatosCita(String cedula, String nombre, String telefono, String direccion, String fecha, int idmedico) {
        Connection conexion = Conexion.obtenerConexion();
        

        if (conexion != null) {
            try {
                String sql = "INSERT INTO Citas (id_medico,cedula,nombre,telefono,direccion,fecha_cita) VALUES (?,?,?,?,?,?)";
                PreparedStatement statement = conexion.prepareStatement(sql);
                statement.setInt(1, idmedico);
                statement.setString(2, cedula);
                statement.setString(3, nombre);
                statement.setString(4, telefono);
                statement.setString(5, direccion);
                statement.setString(6, fecha);
                
                

                int filasInsertadas = statement.executeUpdate();

                if (filasInsertadas > 0) {
                    System.out.println("Registro insertado correctamente.");
                   
                } else {
                    System.out.println("No se pudo insertar el registro.");
                }

                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    conexion.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("Error al establecer la conexión.");
        }

        
    }
    
    public static void insertarDatosPaciente(String nombre, String cedula, String direccion, String telefono) {
        Connection conexion = Conexion.obtenerConexion();
        

        if (conexion != null) {
            try {
                String sql = "INSERT INTO Paciente (nombre,cedula,direccion,telefono) VALUES (?,?,?,?)";
                PreparedStatement statement = conexion.prepareStatement(sql);
                statement.setString(1, telefono);
                statement.setString(2, cedula);
                statement.setString(3, direccion);
                statement.setString(4, telefono);
               
                
                

                int filasInsertadas = statement.executeUpdate();

                if (filasInsertadas > 0) {
                    System.out.println("Registro insertado correctamente.");
                   
                } else {
                    System.out.println("No se pudo insertar el registro.");
                }

                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    conexion.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("Error al establecer la conexión.");
        }

        
    }
}
