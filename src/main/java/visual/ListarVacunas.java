package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import accesoDatos.CargarDatos;
import accesoDatos.GuardarDatos;

import javax.swing.JTable;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ListarVacunas extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private final JTable tablaVacunadoDe = new JTable();
	private JButton btnAddVacuna;
	private Consultas consultas;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			 
			ListarVacunas dialog = new ListarVacunas();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListarVacunas() {
		this.consultas = consultas;
		setTitle("Listar vacunas");
		setBounds(100, 100, 955, 703);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(112, 128, 144));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		tablaVacunadoDe.setBounds(17, 17, 920, 599);
		contentPanel.add(tablaVacunadoDe);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnAddVacuna = new JButton("AÑADIR");
				btnAddVacuna.setActionCommand("OK");
				buttonPane.add(btnAddVacuna);
				getRootPane().setDefaultButton(btnAddVacuna);
				
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
			
			btnAddVacuna.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int selectedrow = tablaVacunadoDe.getSelectedRow();
					if (selectedrow != -1) {
			            // Obtener la información de la fila seleccionada
			            
			            String id_vacuna = tablaVacunadoDe.getValueAt(selectedrow, 0).toString();
			            String nombre = (String) tablaVacunadoDe.getValueAt(selectedrow, 1);
			          
			            
			            Consultas consultas = new Consultas();
						
					
						consultas.agregardatosvacuna(nombre, id_vacuna);
						consultas.setVisible(true);
						dispose();
			        } else {
			            JOptionPane.showMessageDialog(null, "Por favor, seleccione un paciente a consultar de la tabla.");
			        }
					
				
					
				}
			});
			
		}
		CargarDatos.CargarConsulta(tablaVacunadoDe, "Vacuna");
		
	}

}
