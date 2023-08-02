package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import accesoDatos.CargarDatos;

import javax.swing.JTable;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ListarEnfermedades extends JDialog {

	public static String getCampo1() {
		return campo1;
	}

	public static void setCampo1(String campo1) {
		ListarEnfermedades.campo1 = campo1;
	}

	public static String getCampo2() {
		return campo2;
	}

	public static void setCampo2(String campo2) {
		ListarEnfermedades.campo2 = campo2;
	}

	private final JPanel contentPanel = new JPanel();
	private final JTable tablaEnfermoDe = new JTable();
	private JButton btnAddEnfermedad;
	private Consultas consultas;
	private static String campo1;
	private static String campo2;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListarEnfermedades dialog = new ListarEnfermedades();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListarEnfermedades() {
		
		setTitle("Listar enfermedades");
		setBounds(100, 100, 829, 573);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(112, 128, 144));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBackground(new Color(112, 128, 144));
			panel.setBounds(6, 6, 817, 494);
			contentPanel.add(panel);
			panel.setLayout(null);
			tablaEnfermoDe.setBounds(6, 6, 805, 464);
			panel.add(tablaEnfermoDe);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				 btnAddEnfermedad = new JButton("AÑADIR");
				btnAddEnfermedad.setActionCommand("OK");
				buttonPane.add(btnAddEnfermedad);
				getRootPane().setDefaultButton(btnAddEnfermedad);
			}
			{
				JButton btnCancelar = new JButton("SALIR");
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
						Consultas consultas = new Consultas();
						consultas.setModal(true);
						consultas.setVisible(true);
					}
				});
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
			
			
			
			btnAddEnfermedad.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int selectedrow = tablaEnfermoDe.getSelectedRow();
					if (selectedrow != -1) {
			            // Obtener la información de la fila seleccionada
			            
			            String id_enfermo = tablaEnfermoDe.getValueAt(selectedrow, 0).toString();
			            String nombre = (String) tablaEnfermoDe.getValueAt(selectedrow, 1);
			          
			            
			            Consultas consultas = new Consultas();
						
						consultas.txtnombrevacuna.setText(getCampo1());
						consultas.txtidvacunas.setText(getCampo2());
						
						consultas.agregardatosenfermedad(nombre, id_enfermo);;
						consultas.setVisible(true);
						dispose();
			        } else {
			            JOptionPane.showMessageDialog(null, "Por favor, seleccione un paciente a consultar de la tabla.");
			        }
					
				
					
				}
			});
		}
		CargarDatos.CargarConsulta(tablaEnfermoDe, "Enfermedad");
	}
	
	public void safedata(String campo1, String campo2) {
		
	}

}
