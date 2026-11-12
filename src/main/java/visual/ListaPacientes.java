package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import accesoDatos.CargarDatos;

import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class ListaPacientes extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private final JTable tablaPacientes = new JTable();
	private JButton btnSeleccionarPaciente;
	private HistorialPaciente historialPaciente;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListaPacientes dialog = new ListaPacientes();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * Create the dialog.
	 */
	public ListaPacientes() {
		ImageIcon imageIcon = new ImageIcon("/Users/cristianbenelyon/git/ProyectoFinal_POO/ProyectoFinal_POO/imagenes/VENTANA MIS PACIENTES-15.png");
		setTitle("Mis pacientes");
		setBounds(100, 100, 970, 666);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(112, 128, 144));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		tablaPacientes.setBounds(17, 36, 931, 557);
		contentPanel.add(tablaPacientes);
		historialPaciente =  new HistorialPaciente();
		JLabel label = new JLabel(imageIcon);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnSeleccionarPaciente = new JButton("Ver historial clínico del paciente");
				btnSeleccionarPaciente.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int selectedRow = tablaPacientes.getSelectedRow();
				        if (selectedRow != -1) {
				            // Obtener la información de la fila seleccionada
				            String cedula = tablaPacientes.getValueAt(selectedRow, 2).toString();

				            String nombre = (String) tablaPacientes.getValueAt(selectedRow, 3);
				            String telefono = (String) tablaPacientes.getValueAt(selectedRow, 4);
				            String direccion = (String) tablaPacientes.getValueAt(selectedRow, 5);
				            String fecha = (String) tablaPacientes.getValueAt(selectedRow, 6);
				            
				            
				            HistorialPaciente histo = new HistorialPaciente();
						
							historialPaciente.setVisible(true);
							histo.agregardatospaciente(cedula, nombre, direccion, telefono);;
							dispose();
				        } else {
				            JOptionPane.showMessageDialog(null, "Por favor, seleccione un paciente a consultar de la tabla.");
				        }
						
						
						
						
					}
				});
				btnSeleccionarPaciente.setActionCommand("OK");
				buttonPane.add(btnSeleccionarPaciente);
				getRootPane().setDefaultButton(btnSeleccionarPaciente);
			}
			{
				JButton btnSalir = new JButton("Salir");
				btnSalir.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				btnSalir.setActionCommand("Cancel");
				buttonPane.add(btnSalir);
				
				
				CargarDatos.CargarConsultaEspecifica(tablaPacientes, "citas", "id_medico" ,CargarDatos.getId_medico());
			}
		}
		{
			label.setBounds(0, -37, 970, 878);
			contentPanel.add(label);
		}
	}

}
