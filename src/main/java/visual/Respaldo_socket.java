package visual;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Event;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.TextField;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import accesoDatos.CargarDatos;

import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Respaldo_socket extends JDialog implements ActionListener{
	private JPanel contentPane;
	static Socket sfd = null;
	static DataInputStream EntradaSocket;
	static DataOutputStream SalidaSocket; 
	final JPanel contentPanel = new JPanel();
	private final JTable tablaMedicos = new JTable();
	private final String[] columnNames = {"Cedula", "Nombre", "Especialidad", "Disponibilidad"};
	static TextField salida;
	static TextArea entrada;  
	private CargarDatos cargarDatos;
	String texto;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Respaldo_socket dialog = new Respaldo_socket();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Respaldo_socket() {
		
		
		setTitle("Respaldo");
	    setSize(350,200);
	    salida = new TextField(30);
	    salida.addActionListener(this);
		
	    entrada = new TextArea();
	    entrada.setEditable(false);
		
	
	
		try
	    {
	      sfd = new Socket("127.0.0.1",8014);
	      EntradaSocket = new DataInputStream(new BufferedInputStream(sfd.getInputStream()));
	      SalidaSocket = new DataOutputStream(new BufferedOutputStream(sfd.getOutputStream()));
	      String ejemplo = new String("Esto es una prueba nueva");
	      try
	      {
	        SalidaSocket.writeUTF(ejemplo);
	        SalidaSocket.flush();
	      }
	      catch (IOException ioe)
	      {
	        System.out.println("Error: "+ioe);
	      }
	    }
	    catch (UnknownHostException uhe)
	    {
	      System.out.println("No se puede acceder al servidor.");
	      System.exit(1);
	    }
	    catch (IOException ioe)
	    {
	      System.out.println("Comunicaci�n rechazada.");
	      System.exit(1);
	    }
		
		
		
		setBounds(100, 100, 986, 641);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

	    DefaultTableModel model = new DefaultTableModel(columnNames, 0);
	    tablaMedicos.setModel(model);
		contentPanel.setLayout(null);
		tablaMedicos.setBounds(6, 32, 974, 536);
		contentPanel.add(tablaMedicos);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				CargarDatos cargarDatos =  new CargarDatos();
				if (cargarDatos!=null) {
					salida.setText("35");
					texto = salida.getText();
					CargarDatos.CargarDatosDesdeArchivo("medicos.txt", tablaMedicos);
					
					
				}else {
					JOptionPane.showMessageDialog(Respaldo_socket.this, "No existen datos en el fichero", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
				}
			
		
		

			

	            DefaultTableModel model1 = (DefaultTableModel) tablaMedicos.getModel();
	            model1.setRowCount(0);
				JButton btnRespaldarInfo = new JButton("Respaldar información");
				btnRespaldarInfo.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					    
					    try
					    {
					      SalidaSocket.writeUTF(texto);
					      SalidaSocket.flush();
					    }
					    catch (IOException ioe)
					    {
					      System.out.println("Error: "+ioe);
					    }
					    
					

				        if (!texto.isEmpty()) {
				            // Eliminar médico del archivo
				    

				            // Volver a cargar los datos en la tabla
				        	CargarDatos.GuardarDatosEnArchivo(tablaMedicos, "medicos_respaldo.txt");

				            DefaultTableModel model = (DefaultTableModel) tablaMedicos.getModel();
				            model.setRowCount(0);
				            CargarDatos.CargarDatosDesdeArchivo("medicos_respaldo.txt", tablaMedicos);
				            JOptionPane.showMessageDialog(Respaldo_socket.this, "Respaldo realizado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
				        }else {
				        	System.out.println("Problema");
				        }
					    
					}
				});
				btnRespaldarInfo.setActionCommand("Cancel");
				buttonPane.add(btnRespaldarInfo);
			}
		}
		
		JTableHeader header = tablaMedicos.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 12)); 
        header.setEnabled(false);
        header.setBackground(Color.DARK_GRAY); 
        header.setForeground(Color.WHITE); 
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) tablaMedicos.getTableHeader().getDefaultRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);
		 JScrollPane scrollPane = new JScrollPane(tablaMedicos);
		 scrollPane.setEnabled(false);
	        scrollPane.setBounds(31, 6, 907, 606);
	        contentPanel.add(scrollPane);
		 CargarDatos.CargarDatosDesdeArchivo("medicos.txt", tablaMedicos);
	}
	
	public boolean handleEvent(Event e)
	  {
		    if ((e.target == this) && (e.id == Event.WINDOW_DESTROY))
		    {
		      if (sfd != null)
		      {
			try
			{
			  sfd.close();
			}
			catch (IOException ioe)
			{
			  System.out.println("Error: "+ioe);
			}
			this.dispose();
		      }
		    }
		    return true;
		  }

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
		
	
	
}
