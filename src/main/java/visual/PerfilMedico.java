package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import accesoDatos.CargarDatos;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import java.awt.Color;

public class PerfilMedico extends JDialog {

	public int getNumerocitas() {
		return numerocitas;
	}

	public void setNumerocitas(int numerocitas) {
		this.numerocitas = numerocitas;
	}

	private final JPanel contentPanel = new JPanel();
	public static JTable TablaCitasDelMedico = new JTable();
	private Consultas consultas;
	private JButton btnConsultar;
	private int numerocitas;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			PerfilMedico dialog = new PerfilMedico();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public PerfilMedico() {
		setTitle("Perfil médico");
		setBounds(100, 100, 1134, 916);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(112, 128, 144));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		TablaCitasDelMedico.setBounds(0, 361, 1141, 416);
		contentPanel.add(TablaCitasDelMedico);
		ImageIcon imageIcon = new ImageIcon("/Users/cristianbenelyon/git/ProyectoFinal_POO/ProyectoFinal_POO/imagenes/PERFIL MEDICO-22-22-22.png");
		{
			JLabel lblNombreMedico = new JLabel("");
			lblNombreMedico.setBounds(314, 6, 154, 16);
			contentPanel.add(lblNombreMedico);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnConsultar = new JButton("CONSULTAR");
				
				btnConsultar.setActionCommand("OK");
				buttonPane.add(btnConsultar);
				getRootPane().setDefaultButton(btnConsultar);
			}
			{
				JButton btnSalir = new JButton("SALIR");
				btnSalir.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				btnSalir.setActionCommand("Cancel");
				buttonPane.add(btnSalir);
			}
			CargarDatos.CargarConsultaEspecifica(TablaCitasDelMedico, "citas", "id_medico" ,CargarDatos.getId_medico());
			{
				JLabel lblNewLabel = new JLabel(imageIcon);
				lblNewLabel.setBounds(0, 0, 1141, 855);
				contentPanel.add(lblNewLabel);
			}

			
			btnConsultar.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			        int selectedRow = TablaCitasDelMedico.getSelectedRow();
			        if (selectedRow != -1) {
			            // Obtener la información de la fila seleccionada
			            String cedula = TablaCitasDelMedico.getValueAt(selectedRow, 2).toString();

			            String nombre = (String) TablaCitasDelMedico.getValueAt(selectedRow, 3);
			            String telefono = (String) TablaCitasDelMedico.getValueAt(selectedRow, 4);
			            String direccion = (String) TablaCitasDelMedico.getValueAt(selectedRow, 5);
			            String fecha = (String) TablaCitasDelMedico.getValueAt(selectedRow, 6);
			            
						consultas = new Consultas();
						
						consultas.setVisible(true);
						consultas.agregardatoscitas(nombre, telefono, direccion, fecha, cedula);
						dispose();
			        } else {
			            JOptionPane.showMessageDialog(null, "Por favor, seleccione un paciente a consultar de la tabla.");
			        }
			    }
			});
		}
	}
	
	
	
}
